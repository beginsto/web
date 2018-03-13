package pers.jess.template.customer;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {
    private Logger logger = Logger.getLogger(CustomerController.class);

    private static final String userInfoUrl = "http://card.1860.cn/public/zjydwx/service/getWxUserInfo";

    private HttpClient httpClient = new HttpClient();

    @Resource
    private LocalPhoneService localPhoneService;

    @RequestMapping(value = "index")
    public String toIndex(HttpServletRequest req, RedirectAttributes attr){
        HashMap<String, Object> params = new HashMap<>();
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
        try {
            params.put("openId",openid);
            //根据openid获取用户绑定，头像等信息
            String result = httpClient.doPost(userInfoUrl,params);
            logger.info("获取用户信息：context：" + result);
            JSONObject json = JSONObject.parseObject(result);
            if (json != null && String.valueOf(json.get("resultCode")).equals("200")){
                JSONObject context = JSONObject.parseObject(String.valueOf(json.get("resultContent")));
                String mobile = String.valueOf(context.get("phone"));
                if (mobile.length() == 11){
                    LocalPhone local = localPhoneService.quaryByPhone(mobile.substring(0,7));
                    if (local == null)
                        return "redirect:/client-service/no-service";
                    else{
                        String url = "http://kf.cmcc-cs.cn:20980/ngmmgw/channelapi/web/index/";
                        String param = "phone="+mobile+"&source=571573030007&cc=573&pc=571&isshow=0&clientId=00063&fromOrgId=571";
                        String newParams = DesUtil.encode(param);
                        return "redirect:"+url+newParams;
                    }
                }else {
                    attr.addAttribute("openid",openid);
                    return "redirect:/customer/un-bind";
                }
            }else{
                attr.addAttribute("openid",openid);
                return "redirect:/customer/un-bind";
            }

        }catch (Exception e){
            logger.error("微信人工客服异常，context：" + e);
            e.printStackTrace();
        }
        return "redirect:/customer/un-bind";
    }

    @RequestMapping(value = "no-service")
    public Object toNoService(HttpServletRequest req, Model model){
        return "client-service/no-service";
    }

    @RequestMapping(value = "un-bind")
    public Object toUnBind(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid");
        model.addAttribute("openid",openid);
        return "customer/un-bind";
    }
}
