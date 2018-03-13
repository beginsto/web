package pers.jess.template.bindMobile.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pers.jess.template.bindMobile.model.BindMobile;
import pers.jess.template.bindMobile.service.BindMobileService;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.identifyCode.model.IdentifyCode;
import pers.jess.template.identifyCode.service.IdentifyCodeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "bindMobile")
public class BindMobileController {

    private Logger log = Logger.getLogger(BindMobileController.class);

    private static final String userInfoUrl = "http://card.1860.cn/public/zjydwx/service/getWxUserInfo";

    @Resource
    private IdentifyCodeService identifyCodeService;

    @Resource
    private BindMobileService bindMobileService;

    @RequestMapping(value = "index")
    public String toIndex(HttpServletRequest req, Model model, RedirectAttributes attr){
        String openid =req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        HashMap<String, Object> params = new HashMap<>();
        try{
            HttpClient httpClient = new HttpClient();
            params.put("openId",openid);
            //根据openid获取用户绑定，头像等信息
            String result = httpClient.doPost(userInfoUrl,params);
            log.info("openid："+openid+"||"+result);
            JSONObject json = JSONObject.parseObject(result);
            if (json != null && String.valueOf(json.get("resultCode")).equals("200")){
                JSONObject context = JSONObject.parseObject(String.valueOf(json.get("resultContent")));
                String mobile = String.valueOf(context.get("phone"));
                if (mobile.length() == 11){
                    attr.addAttribute("openid",openid);
                    attr.addAttribute("mobile",mobile);
                    //attr.addAttribute("mobile","15905836605");
                    return "redirect:/bindMobile/update";
                }

            }
        }catch (Exception e){
            log.error("");
            e.printStackTrace();
        }


        model.addAttribute("openid",openid);
        return "bindMobile/index";
    }
     @RequestMapping(value = "update")
    public String toUpdate(HttpServletRequest req, Model model){
         String openid =req.getParameter("openid")==null?"":req.getParameter("openid").trim();
         String mobile = req.getParameter("mobile")==null?"":req.getParameter("mobile").trim();
         model.addAttribute("openid",openid);
         model.addAttribute("mobile",mobile);
        return "bindMobile/update";
     }

    @RequestMapping(value = "success")
    public String toSuccess(HttpServletRequest req, Model model){
        String mobile = req.getParameter("mobile");
        model.addAttribute("mobile",mobile);
        return "bindMobile/success";
    }

    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    @ResponseBody
    public Object SendMsg(IdentifyCode record){
         HashMap<String, Object> result = new HashMap<>();
         try {
            record.setIsused("N");
            record.setCreatetime(new Date());
            if (identifyCodeService.insertSelective(record) > 0)
                result.put("result","success");
         }catch (Exception e){
             result.put("result","error");
             log.error("微信绑定号码－>验证码入库异常...context：" + e);
             e.printStackTrace();
         }
         return result;
     }

    @RequestMapping(value = "validate")
    @ResponseBody
    public Object validate(String tel , String context){
        HashMap<String, Object> hashMap = new HashMap<>();
         HashMap<String, Object> result = new HashMap<>();
        try {
            hashMap.put("context",context);
            hashMap.put("mobile",tel);
            List<IdentifyCode> list = identifyCodeService.queryByParams(hashMap);
            if (list.size()>0)
                result.put("result","success");
            else
                result.put("result","failed");
        }catch (Exception e){
            result.put("result","error");
            log.error("微信绑定号码－>验证码校验异常...context：" + e);
            e.printStackTrace();
        }
         return result;
     }


    /**
     * 请求url绑定入库，并存入本地库
     * @param record
     * @return
     */
    @RequestMapping(value = "bind", method = RequestMethod.POST)
    @ResponseBody
    public Object toBind(BindMobile record){
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        String url = "http://card.1860.cn/public/zjydwx/service/boundPhone";
        try {
            if (record == null)
                result.put("result","null-param");
            else{
                record.setBindtime(new Date());
                HttpClient httpClient = new HttpClient();
                hashMap.put("openId",record.getOpenid());
                hashMap.put("phone",record.getPhone());
                String rs = httpClient.doPost(url,hashMap);
                JSONObject json = JSONObject.parseObject(rs);
                if(json != null && String.valueOf(json.get("resultCode")).equals("200")) {
                    bindMobileService.insertSelective(record);
                    result.put("result","success");
                }else
                    result.put("result","null-openid");

            }
        }catch (Exception e){
            log.error("微信绑定号码－>绑定入库异常：" + e);
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
     }
}
