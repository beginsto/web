package pers.jess.template.Cm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("cm")
public class CmController extends BaseController{


    @Resource
    private RegisterService registerService;



    @RequestMapping("validate")
    @ResponseBody
    public Object validate(String mobile){
        if (StringUtils.isEmpty(mobile))
            return packResult(300,"10000",null);
        String result = null;
        try {
            result = CommonUtil.query4GCm(mobile);
            //////////////////////////////
            /// 接口返回示例
            /// 可办理用户：{"userId":"1050059381","teleNum":"13967388286","rk":"3.锁网用户"}
            /// 不可办理用户：{"msg":"未查询到相关数据，请确认！"}
            //////////////////////////////
            if (result.contains("msg"))
                return packResult(300,"10001",null);


            Map<String,Object> map = new HashMap<>();
            map.put("source","4Gcm");
            map.put("mobile",mobile);
            RegisterInfo record = registerService.queryByParam(map);
            if (record != null)
                return packResult(300,"20000",null);

            JSONObject obj = JSON.parseObject(result);

            return packResult(200,String.valueOf(obj.get("rk")),String.valueOf(obj.get("userId")));

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packResult(500,"30000",null);

    }


    @RequestMapping("commit")
    @ResponseBody
    public Object commit(String mobile,String userId, String rk){
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(userId))
            return packResult(300,"10000",null);


        try {
            String result = CommonUtil.push4GCm(mobile,userId);
            if (result.contains("Y")){
                RegisterInfo record = new RegisterInfo();
                record.setMobile(mobile);
                record.setCreatetime(new Date());
                record.setSource("4Gcm");
                record.setReserveone(rk);
                record.setReservetwo(userId);
                if (registerService.insertData(record) > 0)
                    return packResult(200,null,null);
                else
                    return packResult(300,"10002",null);
            }else{
                return packResult(300,"20000",null);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packResult(500,"30000",null);

    }
}
