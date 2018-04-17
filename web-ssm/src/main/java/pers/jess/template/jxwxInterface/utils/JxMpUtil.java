package pers.jess.template.jxwxInterface.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JxMpUtil {


    private static Logger log = Logger.getLogger(JxMpUtil.class);

    private static HttpClient httpClient = null;

    /**
     *
     * @param openid
     * @return
     */
    public static String getMpUserInfo(String openid){
        String result = "";
        Map map = new HashMap();
        httpClient = new HttpClient();
        map.put("openId",openid);
        try {

            result = httpClient.doPost(URLManagerUtils.URL_USERINFO,map);
          //  result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"13758071993\",\"nick\":\"\\uD83D\\uDC1FK\",\"status\":1," +
            //        "\"headimgurl\":\"http://wx.qlogo.cn/mmopen/6vRjrk8z87ib4oleFR2f5PeZ30REWic3MiahcyGNttUPaicldXIQx2U7LPStTKW9cKwF1Reibwiclf18EnEzqUia0Iqzw/0\"," +
              //      "\"openId\":\"oS8a0jruqUpJD-VnA0yirLnSCVrE\"}}";

            if (StringUtils.isEmpty(result))
                return null;

            JSONObject rs = JSONObject.parseObject(result);
            if (String.valueOf(rs.get("resultCode")).equals("300"))
                return null;

            return rs.getString("resultContent");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.error("获取微信用户信息异常，content：["+ result + "]，Exception：【"+ e.toString() +"】");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("获取微信用户信息异常，content：["+ result + "]，Exception：【"+ e.toString() +"】");
        }

        return null;
    }

    /**
     * 验证嘉兴移动号码
     * @param phone 前7位
     * @param service
     * @return
     */
    public static Boolean isLocal(String phone, LocalPhoneService service){
        LocalPhone localPhone = service.quaryByPhone(phone.substring(0,7));
        if (localPhone == null)
            return false;
        else
            return true;
    }

    /**
     * 日期转字符串
     * yyyyMMdd
     * @param d
     * @return
     */
    public static String DateToString(Date d){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d);
    }
}
