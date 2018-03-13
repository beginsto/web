package pers.jess.template.yaohan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.common.model.User;
import pers.jess.template.common.utils.GetWeChatUserInfo;
import pers.jess.template.yaohan.model.Yaohan;
import pers.jess.template.yaohan.service.YaohanService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("yaohan")
public class YaohanController extends BaseController{


    @Resource
    private YaohanService yhService;

    @RequestMapping("getData")
    @ResponseBody
    public Object getData(HttpServletRequest req, String openid){
        if (StringUtils.isEmpty(openid))
            return packResult(10000,"",null);

        String result = GetWeChatUserInfo.getUserInfo(openid);
        //log.info("用户信息："+result);
        //String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"13806702507\",\"nick\":\"\\uD83D\\uDC1FK\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/6vRjrk8z87ib4oleFR2f5PeZ30REWic3MiahcyGNttUPaicldXIQx2U7LPStTKW9cKwF1Reibwiclf18EnEzqUia0Iqzw/0\",\"openId\":\"oS8a0jruqUpJD-VnA0yirLnSCVrE\"}}";
        // String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"\",\"nick\":\"AAAA\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/EVPtJJ9TsHwy1qhryQF25O8Eh58dVZ4uagjYribwYXL3z1U9Wu7FiasmX12U89k2wZTHojJTHxUMXkkUSTiaTBAFA/0\",\"openId\":\"o252jt8MQGpHT6SzY-4k1qTT1f6A\"}}";
        //String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"13758300969\",\"nick\":\"惠\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/5cJ329xUeTwSssNMvsLH1dT4gq6skSGPo2Cv51BnWW02003WPJOyIQyfzWBV5zOD8AQHVHX59dhob2ayI8Y9aA/0\",\"openId\":\"oS8a0jrx8og_nwagf23PYk0TcOks\"}}";
        JSONObject rs = JSONObject.parseObject(result);
        if (result == null || result.equals("") || String.valueOf(rs.get("resultCode")).equals("300"))
            return packResult(10001,"", null);

        String content = rs.getString("resultContent");
        User user = JSONObject.parseObject(content,User.class);
        if (getSessionUserInfo(req,"yaohan") == null)
            setSession(req,user,"yaohan");
        if (user.getPhone() == null || user.getPhone().length() != 11)
            return packResult(10002,"",null);

        Yaohan yhr = yhService.queryByPhone(user.getPhone());
        if (yhr == null)
            return packResult(20000,user.getPhone(),null);

        return packResult(20000,null,JSON.toJSONString(yhr));

    }


    @RequestMapping("commit")
    @ResponseBody
    public Object commi(HttpServletRequest req, String openid, String num,String tic){
        if (StringUtils.isEmpty(num) || StringUtils.isEmpty(tic))
            return packResult(10000,"",null);

        try {
            User u = (User) getSessionUserInfo(req, "yaohan");

            if ( u != null && StringUtils.isEmpty(u.getPhone()) && !StringUtils.isEmpty(openid)){

                String url = "http://card.1860.cn/public/zjydwx/service/boundPhone";
                HttpClient client = new HttpClient();
                Map<String, Object> map = new HashMap<>();
                map.put("openId",openid);
                map.put("phone",num);
                client.doPost(url,map);

            }

            Yaohan yhr = yhService.queryByPhone(num);
            if (yhr != null)//该号码已领取过
                return packResult(20001,"已参与",null);
            Yaohan yhu = yhService.queryByCounpon(tic);
            if (yhu == null)//未查询到此优惠券
                return packResult(10001,"无此券",null);
            if (yhu.getIsused() == 1)//该优惠券已被领取
                return packResult(20002,"此券已用",null);

            yhu.setIsused(1);
            yhu.setUsetime(new Date());
            yhu.setPhone(num);
            int temp = yhService.updateByPrimaryKey(yhu);

            if (temp == 0)
                return packResult(30002,"",null);



            return packResult(20000,yhu.getCouponDesc(),null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return packResult(50000,"异常",null);

    }

}
