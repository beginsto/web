package pers.jess.template.rwk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "rwk")
public class RwkController extends BaseController{

    @Resource
    private RegisterService registerService;

    @RequestMapping(value = "confirm")
    @ResponseBody
    public Object confirm(HttpServletRequest req){
        String mobile = req.getParameter("tel")==null?"":req.getParameter("tel").trim();
        String platform = req.getParameter("platform")==null?"":req.getParameter("platform").trim();
        if (mobile.equals("") || platform.equals(""))
            return packResult(300,"信息不全",null);
        try {

            String result = CommonUtil.isTarget(mobile);
            if (result == null || result.contains("msg"))
                return  packResult(300,"http异常异常",null);
            JSONObject json = JSON.parseObject(result);
            String userid = json.get("userId")==null?"":json.getString("userId");
            String disMth = json.get("reportDate")==null?"":json.getString("reportDate");
            if (disMth.equals(""))
                return  packResult(201,"",null);


            Map<String, Object> map = new HashMap<>();
            map.put("mobile",userid);
            map.put("source","rwk");
            RegisterInfo registerInfo = registerService.queryByParam(map);
            if (registerInfo != null)
                return packResult(202,"重复领取",null);


            map.put("mobile",mobile);
            registerInfo = registerService.queryByParam(map);
            if (registerInfo != null)
                return packResult(202,"重复领取",null);

            result = CommonUtil.pushData(mobile,userid,disMth,switchName(platform));
            if(result.contains("Y")){
                RegisterInfo info = new RegisterInfo();
                info.setMobile(mobile);
                info.setCreatetime(new Date());
                info.setSource("rwk");
                info.setReserveone(disMth);
                info.setReservetwo(switchName(platform));
                registerService.insertData(info);
                return packResult(200,disMth,null);
            }else{
                return  packResult(300,"http异常异常",null);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packResult(500,"",null);
    }

    private String switchName(String str){
        switch (str){
            case "ch1":
                return "yk";
            case "ch2":
                return "aqy";
            case "ch3":
                return "pptv";
            case "ch4":
                return "tx";
            default:
                return "yk";
        }
    }
}
