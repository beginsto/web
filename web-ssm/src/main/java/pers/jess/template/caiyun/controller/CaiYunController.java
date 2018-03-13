package pers.jess.template.caiyun.controller;

import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.caiyun.model.CaiYunInfo;
import pers.jess.template.caiyun.service.CaiYunService;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("caiyun")
public class CaiYunController extends BaseController{

    @Resource
    private LocalPhoneService localPhoneService;

    @Resource
    private CaiYunService caiyunService;

    @RequestMapping("index")
    public Object index(HttpServletRequest req,Model model){
        String platform = req.getParameter("platform");
        model.addAttribute("platform",platform);

        return "caiyun/index";
    }

    @RequestMapping("commit")
    @ResponseBody
    public Object commit(HttpServletRequest req){
        String platform = req.getParameter("platform");
        String tel = req.getParameter("tel")==null?"":req.getParameter("tel").trim();
        if (tel.equals("") )
            return packResult(404,"",null);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("mobile",tel);
            map.put("platform",platform);
            CaiYunInfo record = caiyunService.queryByMobile(map);
            if (record != null)
                return packResult(303,"",null);

            CaiYunInfo info = new CaiYunInfo();
            info.setMobile(tel);
            info.setPlatform(platform);
            info.setCreatetime(new Date());
            if (caiyunService.insertData(info) == 1)
                return packResult(200,"",null);
            else
                return packResult(300,"",null);
        }catch (Exception e){
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

    @RequestMapping("getAmount")
    @ResponseBody
    public Object getAmount(HttpServletRequest req){
        String platform  = req.getParameter("platform");
        Map<String, Object> map = new HashMap<>();
        map.put("amount",caiyunService.getAmount(platform));
        return map;
    }
}
