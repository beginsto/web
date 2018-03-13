package pers.jess.template.semdMsg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping(value = "sms")
public class SendMsgController extends BaseController{

    @RequestMapping("sendMsg")
    @ResponseBody
    public Object sms(HttpServletRequest req){
        String mobile = req.getParameter("mobile")==null?"":req.getParameter("mobile").trim();
        String callback = req.getParameter("callback");
        if (mobile.equals("") || mobile.length() != 11)
            return packResult(callback,300,"手机号码有误",null);
        if (!isLocalMob(mobile))
            return packResult(callback,300,"非本地号码",null);
        try {
           String code = CommonUtil.sendMsg(mobile);
            // code = "987896";
            if (code == null || code.equals(""))
                return packResult(callback,300,"http异常",null);

            return packResult(callback,200,getNum(code),null);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packResult(callback,500,"短信服务异常",null);
    }

    /**
     *
     * @param str
     * @return
     */
    private String getNum(String str){
        String str2="";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)>=48 && str.charAt(i)<=57){
                str2+=str.charAt(i);
            }
        }
        return str2;
    }


    @RequestMapping("sendMsg1")
    @ResponseBody
    public Object sendMsg(HttpServletRequest req){

        String mobile = req.getParameter("mobile")==null?"":req.getParameter("mobile").trim();
        String callback = req.getParameter("callback");
        log.info(callback);
        return packResult(callback,200,"888888",null);

    }
}
