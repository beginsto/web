package pers.jess.template.fxll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.common.utils.IdcardValidator;
import pers.jess.template.fxll.model.Fxll;
import pers.jess.template.fxll.service.FxllService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("fxll")
public class FxllController extends BaseController{

    @Resource
    private FxllService fxllService;

    @RequestMapping(value = "reg")
    @ResponseBody
    public Object reg(HttpServletRequest req, Fxll fxll) throws Exception{
        if (fxll == null)
            return packResult(300,"表单为空",null);
        if(!isLocalMob(fxll.getMobile()))
            return packResult(301,"非嘉兴号段",null);
        if(!IdcardValidator.isValidatedAllIdcard(fxll.getReserve()))
            return packResult(302,"身份证号码有误",null);
        Fxll f = fxllService.selectByMobile(fxll.getMobile());
        if (f != null)
            return packResult(303,"请勿重复提交",null);
        int temp = fxllService.insert(fxll);
        if (temp != 1)
            return packResult(305,"异常",null);
        return packResult(200,"",null);

    }


    private String getNum(String str){
        String str2="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>=48 && str.charAt(i)<=57){
                str2+=str.charAt(i);
            }
        }
        return str2;
    }
}
