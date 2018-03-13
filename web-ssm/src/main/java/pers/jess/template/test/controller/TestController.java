package pers.jess.template.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@Controller
@RequestMapping("test")
public class TestController {


    @RequestMapping("getDate")
    @ResponseBody
    public Object getData(){
        return null;

    }

    @ModelAttribute("hello")
    public String getModel(){
        System.out.println("------------hello-------------");
        return "world";
    }

    @RequestMapping("sayHello")
    public void sayHello(@ModelAttribute("hello") String hello, Writer writer, HttpSession session) throws IOException{
        writer.write("Hello " + hello);
        writer.write("\r");
        Enumeration enume = session.getAttributeNames();
        while (enume.hasMoreElements())
            writer.write(enume.nextElement()+"\r");
    }

}
