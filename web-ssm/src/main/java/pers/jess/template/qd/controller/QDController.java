package pers.jess.template.qd.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.qd.model.QDDetail;
import pers.jess.template.qd.model.QDINFO;
import pers.jess.template.qd.service.QDService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("qd")
public class QDController extends BaseController{

    @Resource
    private QDService service;

    @RequestMapping("getInfo")
    @ResponseBody
    public Object getInfo(String openid){
        if(StringUtils.isEmpty(openid))
            return packResult(10000,"",null);

        QDDetail d = service.queryByOpenid(openid);
        if (d == null)
            return packResult(20000,null,null);
        else
            return packResult(20000, d.getPhone(),null);
    }


    @RequestMapping("validate")
    @ResponseBody
    public Object validate(String openid, String num){

        if (StringUtils.isEmpty(num))
            return packResult(10000,"",null);


        List<QDINFO> list = service.query(num);
        if (list.size() == 0)
            return packResult(20000,"failed",null);

        QDDetail d = new QDDetail();
        d.setOpenid(openid);
        d.setPhone(num);
        d.setCreatetime(new Date());
        service.insert(d);
        return packResult(20000,"success",null);


    }

    @RequestMapping("getData")
    @ResponseBody
    public Object getData(String num){
        if (StringUtils.isEmpty(num))
            return packResult(10000,"",null);
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
            List<QDINFO> list = service.query(num);
            QDINFO info = new QDINFO();
            info.setFd(list.size());
            info.setPhone1(num);
            for (int i = 0;i < list.size();i++){

                info.setCj((info.getCj()==null?0:info.getCj()) + list.get(i).getCj());
                info.setFh((info.getFh()==null?0:info.getFh()) + list.get(i).getFh());
                info.setJf((info.getJf()==null?0:info.getJf()) + list.get(i).getJf());
                info.setKd((info.getKd()==null?0:info.getKd()) + list.get(i).getKd());
                info.setZd((info.getZd()==null?0:info.getZd()) + list.get(i).getZd());
                info.setZf((info.getZf()==null?0:info.getZf()) + list.get(i).getZf());
                if (info.getCretime() == null)
                    info.setCretime(sf.format(sf.parse(list.get(i).getCretime())));
                else{
                    if (sf.parse(list.get(i).getCretime()).before(sf.parse(info.getCretime())))
                        info.setCretime(sf.format(sf.parse(list.get(i).getCretime())));
                }
            }

            return JSON.toJSONString(info);
        }catch (Exception e){
        e.printStackTrace();
        }
        return null;
    }


}
