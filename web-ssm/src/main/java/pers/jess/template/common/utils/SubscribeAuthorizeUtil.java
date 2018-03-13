package pers.jess.template.common.utils;

import com.alibaba.fastjson.JSON;
import pers.jess.template.common.httpclient.HttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class SubscribeAuthorizeUtil {

    private static String ACCESS_TOKEN = null;
    private static String REFRESH_TOKEN = null;

    private static final String APPID = "wx999266da568d0ddf";
    private static final String SECRET = "4854eb985047628552caca0a33ad1ebd";

    private static String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static String URL_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";


    public static String getOpenid(String code){
        Map<String, Object> map = new HashMap<>();
        map.put("appid",APPID);
        map.put("secret",SECRET);
        map.put("code",code);
        map.put("grant_type","authorization_code");

        HttpClient httpClient = new HttpClient();
        try {
            String result = httpClient.doPost(URL_ACCESS_TOKEN,map);
            if (result == null || result.contains("errcode"))
                return packResult(300,"code获取access_token异常");
            return null;

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return packResult(500,"httpClient请求URISyntaxException［"+e.toString()+"］");
        } catch (IOException e) {
            e.printStackTrace();
            return packResult(500,"httpClient请求IOException［"+e.toString()+"］");
        }
    }

    /**
     * 组装返回结果
     * @param status
     * @param data
     * @return String
     */
    private static String packResult(int status, String data){
        Map<String, Object> map = new HashMap<>();
        map.put("resultCode",status);
        map.put("data",data);
        return JSON.toJSONString(map);
    }
}
