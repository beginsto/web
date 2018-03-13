package pers.jess.template.ggk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "ggk")
public class GgkController extends BaseController {


    @Resource
    private RegisterService registerService;

    @RequestMapping(value = "validator")
    @ResponseBody
    public Object validator(HttpServletRequest req){
        String mobile =req.getParameter("mobile")==null?"":req.getParameter("mobile").trim();
        String userid = req.getParameter("id")==null?"":req.getParameter("id").trim();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("mobile",mobile);
            map.put("source","ggk");
            RegisterInfo registerInfo = registerService.queryByParam(map);
            if (registerInfo != null)
                return packResult(202,"重复领取",null);
            if (!userid.equals("")){
                String result = CommonUtil.pushData(mobile,userid,"1");

                if(result.contains("Y")){
                    RegisterInfo info = new RegisterInfo();
                    info.setMobile(mobile);
                    info.setCreatetime(new Date());
                    info.setSource("ggk");
                    info.setReserveone("30G");
                    info.setReservetwo(userid);
                    registerService.insertData(info);
                    return packResult(200,"",null);
                }else{
                    return  packResult(300,"http异常异常",null);
                }

            }else{
                RegisterInfo info = new RegisterInfo();
                info.setMobile(mobile);
                info.setCreatetime(new Date());
                info.setSource("ggk");
                info.setReserveone("untarget");
                registerService.insertData(info);
                return packResult(200,"",null);
            }

        }catch (Exception e){
            e.printStackTrace();
            return packResult(500,"",null);
        }
    }
}
