package pers.jess.template.common.BaseController;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.log4j.Logger;
import pers.jess.template.common.model.User;
import pers.jess.template.common.utils.SubscribeUtil;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    protected Logger log = Logger.getLogger(this.getClass());

    @Resource
    private LocalPhoneService service;


    /**
     * 获取用户信息（unionid）
     * @param openid
     * @param mid
     * @return User
     */
    protected String getUser(String openid, String mid){
        String result = SubscribeUtil.getUserInfo(openid,mid);

        if (result == null || result.contains("errcode"))
            return packResult(300,"",result);

        JSONObject obj = JSONObject.parseObject(result);

        if (obj.getInteger("subscribe") == 0)
            return packResult(200,"",JSON.toJSONString(new User(openid,0,null,null,null,null)));

        return packResult(200,"",JSON.toJSONString(new User(openid,obj.getInteger("subscribe"),null,obj.getString("nickname"),obj.getString("headimgurl"),obj.getString("unionid")))) ;
    }

    protected boolean isLocalMob(String mobile){
        LocalPhone l = service.quaryByPhone(mobile.substring(0,7));
        if (l == null)
            return false;
        else
            return true;
    }


    /**
     * 组装返回结果
     * @param status
     * @param message
     * @param data
     * @return String
     */
    protected String packResult(int status, String message, String data){
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode",status);
        map.put("message",message);
        map.put("data",data);
        return JSON.toJSONString(map);
    }

    /**
     * jsonp组装返回结果
     * @param callback
     * @param status
     * @param message
     * @param data
     * @return String
     */
    protected String packResult(String callback, int status, String message, String data){
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode",status);
        map.put("message",message);
        map.put("data",data);
        if (callback == null)
            return JSON.toJSONString(map);
        else
            return callback + "(" + JSONObject.toJSONString(map) + ")";
    }

    /***
     *
     * @param req
     * @param t
     * @param arrtibutename
     * @param <T>
     */
    protected  <T> void setSession(HttpServletRequest req, T t,String arrtibutename){
        req.getSession().removeAttribute(arrtibutename);
        req.getSession().setAttribute(arrtibutename,t);
    }

    /***
     *
     * @param req
     * @param arrtibutename
     * @return Object
     */
    protected Object getSessionUserInfo(HttpServletRequest req,String arrtibutename){
        return req.getSession().getAttribute(arrtibutename);
    }
}
