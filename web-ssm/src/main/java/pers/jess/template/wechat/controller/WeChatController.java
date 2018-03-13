package pers.jess.template.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.utils.ServiceUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    @RequestMapping(value = "config")
    @ResponseBody
    public Object getConfig(HttpServletRequest request) {
        String errMsg = request.getParameter("errMsg");
        String url = request.getParameter("url");
        url = url.replaceAll("#", "");
        String configJson = ServiceUtil.createAllConfigInfo(url, errMsg);
     //   System.out.println(configJson);
        return configJson;
    }

}
