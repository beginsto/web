package pers.jess.template.register.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "register")
public class RegisterController extends BaseController{

    @Resource
    private RegisterService registerService;

    @Resource
    private LocalPhoneService localPhoneService;


    /**
     *  通用型登记
     * @param req
     * @param reg
     * @return
     */
    @RequestMapping("commit")
    @ResponseBody
    public Object commit(HttpServletRequest req, RegisterInfo reg){
        String callback = req.getParameter("callback");
        if (reg == null || reg.getMobile() == null || reg.getMobile().length()!=11)
            return packResult(callback,400,"参数异常",null);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("mobile",reg.getMobile());
            map.put("source",reg.getSource());
            RegisterInfo info = registerService.queryByParam(map);
            if (info != null)
                return packResult(callback,201,"请勿重复登记",null);

            reg.setCreatetime(new Date());
            int temp = registerService.insertData(reg);
            if (temp > 0)
                return packResult(callback,200,"",null);
            else
                return packResult(callback,300,"小嘉身体不适，暂时不能为您服务了...",null);

        }catch (Exception e){
            e.printStackTrace();
            return packResult(callback,500,"小嘉身体不适，暂时不能为您服务了...",null);
        }

    }


    /**
     * 验证是否是嘉兴号段
     * @param req
     * @return
     */
    @RequestMapping("validated")
    @ResponseBody
    public Object validated(HttpServletRequest req){
        String callback = req.getParameter("callback");
        String phone = req.getParameter("tel");
        //是否嘉兴移动号码
        LocalPhone lp = localPhoneService.quaryByPhone(phone.substring(0,7));
        if (lp == null)
            return packResult(callback,304,"",null);
        else
            return packResult(callback,200,"",null);
    }
}
