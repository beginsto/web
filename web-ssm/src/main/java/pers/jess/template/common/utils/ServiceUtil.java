package pers.jess.template.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import pers.jess.template.common.httpclient.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class ServiceUtil {

    public static final String ENCODE = "UTF-8";
    public static final String APPID = "wx68c88f3dca13e997";
    public static final String APPSECRET = "5f743c5eb202b04307de7a2e71b60334";//"0433804230b8bd009d79d72b2e9c8899";
    public static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    public static final String URL_JSAPI_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";
    public static String ACCESS_TOKEN = null;
    public static String JSAPI_TICKET = null;
    public static final Long EXPIRES_IN = 7200L;
    public static Long SET_ACCESS_TOKEN_TIME = 0L;
    public static Long SET_ACCESS_TOKEN_TIME_LOSE_EFFICACY = 0L;

    private static HttpClient httpClient;


    public static void setACCESS_TOKEN(String aCCESSTOKEN) {
        ACCESS_TOKEN = aCCESSTOKEN;
    }

    public static void setJSAPI_TICKET(String jSAPITICKET) {
        JSAPI_TICKET = jSAPITICKET;
    }

    public static void setSET_ACCESS_TOKEN_TIME(Long sETACCESSTOKENTIME) {
        SET_ACCESS_TOKEN_TIME = sETACCESSTOKENTIME;
    }

    public static void setSET_ACCESS_TOKEN_TIME_LOSE_EFFICACY(Long sETACCESSTOKENTIMELOSEEFFICACY) {
        SET_ACCESS_TOKEN_TIME_LOSE_EFFICACY = sETACCESSTOKENTIMELOSEEFFICACY;
    }


    public static String getAccessToken() {
        Map<String, String> param = new HashMap();
        param.put("grant_type", "client_credential");
        param.put("appid", APPID);
        param.put("secret", APPSECRET);
        String query = UpopPayPage.sortParamForSign(param);
        String url_access_token = "https://api.weixin.qq.com/cgi-bin/token?" + query;
        httpClient = new HttpClient();
        String access_token_json = httpClient.doPost(url_access_token);
        JSONObject jsonObject = JSONObject.parseObject(access_token_json);
        System.out.println("获取access_token:"+access_token_json);
        return jsonObject.getString("access_token") == null ? jsonObject.getString("errcode") : jsonObject.getString("access_token");
    }

    public static String getJsapiTicket() {
        Map<String, String> param = new HashMap();
        param.put("access_token", ACCESS_TOKEN);
        param.put("type", "jsapi");
        String query = UpopPayPage.sortParamForSign(param);
        String url_jsapi_ticket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" + query;
        httpClient = new HttpClient();
        String jsapi_ticket_json = httpClient.doPost(url_jsapi_ticket);
        JSONObject jsonObject = JSONObject.parseObject(jsapi_ticket_json);
        System.out.println("获取jsapi_ticket:"+jsapi_ticket_json);
        return jsonObject.getString("ticket") == null ? jsonObject.getString("errcode") : jsonObject.getString("ticket");
    }

    public static String createAllConfigInfo(String url, String errMsg) {
        String nonceStr = RandomStringUtils.randomAlphabetic(12);
        String timestamp = Long.toString(System.currentTimeMillis() / 1000L);
        isNeedSetStaticValues(errMsg);
        Map<String, String> param = new HashMap();
        param.put("noncestr", nonceStr);
        param.put("jsapi_ticket", JSAPI_TICKET);
        param.put("timestamp", timestamp);
        param.put("url", url);

        String string1 = UpopPayPage.sortParamForSign(param);
        String signature = DigestUtils.shaHex(string1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", "wx68c88f3dca13e997");
        jsonObject.put("timestamp", timestamp);
        jsonObject.put("nonceStr", nonceStr);
        jsonObject.put("signature", signature);
        return jsonObject.toString();
    }

    public static void isNeedSetStaticValues(String errMsg) {
        if (ACCESS_TOKEN == null) {
            setACCESS_TOKEN(getAccessToken());
        } else if (errMsg != null && !"".equals(errMsg) && "config:fail".equals(errMsg)) {
            setACCESS_TOKEN(getAccessToken());
        }

        if (JSAPI_TICKET == null) {
            setJSAPI_TICKET(getJsapiTicket());
        } else if (errMsg != null && !"".equals(errMsg) && "config:fail".equals(errMsg)) {
            setJSAPI_TICKET(getJsapiTicket());
        }

        if (SET_ACCESS_TOKEN_TIME.longValue() == 0L) {
            setSET_ACCESS_TOKEN_TIME(System.currentTimeMillis() / 1000L);
        }

        if (SET_ACCESS_TOKEN_TIME_LOSE_EFFICACY.longValue() == 0L) {
            setSET_ACCESS_TOKEN_TIME_LOSE_EFFICACY(SET_ACCESS_TOKEN_TIME.longValue() + EXPIRES_IN.longValue());
        }

        if (SET_ACCESS_TOKEN_TIME_LOSE_EFFICACY.longValue() <= System.currentTimeMillis() / 1000L) {
            setACCESS_TOKEN(getAccessToken());
            setJSAPI_TICKET(getJsapiTicket());
            setSET_ACCESS_TOKEN_TIME(System.currentTimeMillis() / 1000L);
            setSET_ACCESS_TOKEN_TIME_LOSE_EFFICACY(SET_ACCESS_TOKEN_TIME.longValue() + EXPIRES_IN.longValue());
        }

    }
}
