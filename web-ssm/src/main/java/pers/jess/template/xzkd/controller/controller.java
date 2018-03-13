package pers.jess.template.xzkd.controller;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "xzkd")
public class controller extends BaseController{



    @Resource
    private RegisterService registerService;

    @RequestMapping("raffle")
    @ResponseBody
    public Object raffle(String tel){
        Map<String, Object> map = new HashMap<>();
        tel = tel==null?"":tel.trim();
        if (tel.equals("") || tel.length() != 11)
            return packResult(404,"",null);

        map.put("mobile",tel);
        map.put("source","xzkd");
        try {
            RegisterInfo info = registerService.queryByParam(map);
            if (info != null)
                return packResult(303,"",null);

            int awardCode = doRaffle();

            RegisterInfo record = new RegisterInfo();
            record.setMobile(tel);
            record.setSource("xzkd");
            record.setReserveone(awards[awardCode]);
            record.setCreatetime(new Date());
            if (registerService.insertData(record) == 1)
                return packResult(200,""+(awardCode-1),null);
            else
                return packResult(300,"",null);
        }catch (Exception e){
            e.printStackTrace();
            return packResult(500,"",null);
        }

    }
    private String[] awards = {"","3元话费券","6元话费券","3元流量闪充包","15元话费券","6元流量闪充包","30元话费券","60元话费券","150元话费券"};
    private int doRaffle(){

        Random rand = new Random();
        int temp = rand.nextInt(100000);
        int rs;
        if (temp>=0 && temp<35000)
            rs= 1;
        else if (temp>=35001 && temp<70000)
            rs= 3;
        else if (temp>=70001 && temp<80000)
            rs= 2;
        else if (temp>=80001 && temp<90000)
            rs= 5;
        else
            rs = 4;


        if (temp % 1000 == 0 )
            rs= 6;

        if (temp == 88 || temp ==888 || temp ==8888 || temp ==8888)
            rs=  7;

        if(temp == 99999)
            rs= 8;
        return rs;

    }
}
