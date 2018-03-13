package pers.jess.template.football.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.football.model.Football;
import pers.jess.template.football.service.FootballService;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("football")
public class FootballController extends BaseController{

    private Logger log = Logger.getLogger(FootballController.class);

    @Resource
    private FootballService footballService;

    @Resource
    private LocalPhoneService localPhoneService;


    @RequestMapping("index")
    public Object index(HttpServletRequest req, Model model){
        int count = footballService.queryCount();
        model.addAttribute("amount",count);
        return "football/index";
    }

    @RequestMapping("login")
    @ResponseBody
    public Object login(Football football){
        try {
            Date now = new Date();
            if (!isEnd(now))
                return packResult(304,"",null);

            if (football == null)
                return packResult(404,"",null);

            if(!isLocal(football.getMobile()))
                return packResult(300,"",null);

            if (isRepeat(football.getMobile()))
                return packResult(305,"",null);

            football.setCreatetime(now);
            int temp = footballService.insertData(football);
            if (temp== 1)
                return packResult(200,""+ (footballService.queryCount()+2000),null);
            else
                return packResult(303,"",null);
        }catch (Exception e){
            log.error("助威国足登记异常，Exception：［"+e.toString()+"］");
            e.printStackTrace();
            return packResult(500,"",null);
        }



    }

    @RequestMapping("validated")
    @ResponseBody
    public Object validated(HttpServletRequest req){
        String phone = req.getParameter("tel");
        //是否嘉兴移动号码
        LocalPhone lp = localPhoneService.quaryByPhone(phone.substring(0,7));
        if (lp == null)
            return packResult(304,"",null);
        else
            return packResult(200,"",null);
    }

    private boolean isLocal(String mobile){
        if (mobile == null || mobile.length() != 11)
            return false;
        LocalPhone local = localPhoneService.quaryByPhone(mobile.substring(0,7));
        if(local == null)
            return false;
        else
            return true;
    }

    private boolean isEnd(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String dd = "2017-09-05";
        System.out.println(sf.format(date));
        if (dd.equals(sf.format(date)))
            return true;
        else
            return false;
    }

    private boolean isRepeat(String mobile){

        int temp = footballService.queryByMobile(mobile);
        if (temp > 0)
            return true;
        else
            return false;

    }

}
