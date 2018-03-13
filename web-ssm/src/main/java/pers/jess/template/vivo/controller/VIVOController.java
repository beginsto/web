package pers.jess.template.vivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("vivo")
public class VIVOController extends BaseController{

    @Resource
    private LocalPhoneService localPhoneService;

    @Resource
    private RegisterService registerService;

    @RequestMapping("index")
    public Object index(Model model){
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            if (now.after(sf.parse("2017-09-29 23:59:59"))){
                return "redirect:../page/common/timeout.html";
            }
            Map<String, Object> map = new HashMap<>();
            map.put("source","vivox20");
            model.addAttribute("amount",registerService.quaryAmount(map));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "vivo-x20/index";
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

    @RequestMapping("commit")
    @ResponseBody
    public Object commit(HttpServletRequest req){
        String source = req.getParameter("source");
        String tel = req.getParameter("tel")==null?"":req.getParameter("tel").trim();
        if (tel.equals("") )
            return packResult(404,"",null);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("mobile",tel);
            map.put("source",source);
            RegisterInfo record = registerService.queryByParam(map);
            if (record != null)
                return packResult(303,"",null);

            RegisterInfo info = new RegisterInfo();
            info.setMobile(tel);
            info.setSource(source);
            info.setCreatetime(new Date());
            if (registerService.insertData(info) == 1)
                return packResult(200,"",null);
            else
                return packResult(300,"",null);
        }catch (Exception e){
            e.printStackTrace();
            return packResult(500,"",null);
        }
    }
}
