package pers.jess.template.redirect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("redirect")
@Controller
public class RedirectController {

    @RequestMapping("huilife")
    public Object huilife(HttpServletRequest req){
        return "redirect:http://jxyd.1860.cn/main/page/hui-life/index.html";
    }

    @RequestMapping("wutu")
    public Object wutu(HttpServletRequest req){
        return "redirect:http://jxyd.1860.cn/app/page/wutu/index.html";
    }

    @RequestMapping("xhb")
    public Object xhb(HttpServletRequest req){
        return "redirect:http://jxyd.1860.cn/app/page/xhb/index.html";
    }
}
