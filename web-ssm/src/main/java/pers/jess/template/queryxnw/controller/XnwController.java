package pers.jess.template.queryxnw.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.common.utils.GetWeChatUserInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("xnw")
public class XnwController extends BaseController{


    @RequestMapping("queryInfo")
    @ResponseBody
    public Object queryInfo(HttpServletRequest req,String openid, String shortTel, String areaCode){

        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(shortTel) || StringUtils.isEmpty(areaCode) )
            return packResult(300,"10000",null);

        String result = GetWeChatUserInfo.getUserInfo(openid);
       // String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"13967300777\",\"nick\":\"AAAA\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/EVPtJJ9TsHwy1qhryQF25O8Eh58dVZ4uagjYribwYXL3z1U9Wu7FiasmX12U89k2wZTHojJTHxUMXkkUSTiaTBAFA/0\",\"openId\":\"o252jt8MQGpHT6SzY-4k1qTT1f6A\"}}";
        if (result == null || result.equals(""))
            return packResult(300,"10001",null);

        JSONObject rs = JSONObject.parseObject(result);
        if (String.valueOf(rs.get("resultCode")).equals("300"))
            return packResult(300,"10001",null);

        String content = rs.getString("resultContent");
        JSONObject j  = JSON.parseObject(content);
        String mobile =  j.getString("phone");
        if (mobile == null || mobile.equals("") || mobile.equals("null") || mobile.length() != 11)
            return packResult(300,"10002",null);


        try {

            result = CommonUtil.queryXnwFliter(mobile,(Integer.parseInt(areaCode)==9?11:Integer.parseInt(areaCode))+2);
            if (result == null || !result.contains("shortNum"))
                return packResult(300,"20001",null);

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //  查询原短号对应的长号                                                                                                    //
            //  返回示例{"vpmnId":"5730093286","vpmnName":"嘉兴市嘉善县大云镇人民政府虚拟网","shortNum":"660777","teleNum":"13967300777"}  //
            //                                                                                                                       //
            //                                                                                                                       //
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            result = CommonUtil.queryShortMobile(shortTel,Integer.parseInt(areaCode)+1);

            if (result == null || !result.contains("teleNum"))
                return packResult(300,"20002",null);//未迁入用户

            rs = JSON.parseObject(result);

            mobile = rs.getString("teleNum");

            result = CommonUtil.queryXnw(mobile,Integer.parseInt(areaCode));

            if (result == null || !result.contains("shortNum"))
                return packResult(300,"服务异常--10003",null);

            return packResult(200,result,null);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packResult(300,"服务异常--30000",null);

    }
}
