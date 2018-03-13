package pers.jess.template.common.utils;

import com.alibaba.fastjson.JSONObject;
import pers.jess.template.common.httpclient.HttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class SubscribeUtil {

    private static String appID = "wx999266da568d0ddf";
    private static String appSecret = "4854eb985047628552caca0a33ad1ebd";

    private static HttpClient httpClient = new HttpClient();

    public static String getWeixinKey()
            throws Exception
    {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret;

        return JSONObject.parseObject(httpClient.doPost(url)).getString("access_token");
    }


    /**
     * 根据公众号ID获取access_token
     * @param mid
     * @return
     */
    private static String getKey(String mid){
        Map map = new HashMap();
        String url = "http://card.1860.cn/public/zjydwx/service/getTokenByMid";
        map.put("mid",mid);
        try {
            String result = httpClient.doPost(url,map);
            if (result != null && result.contains("success"))
                return JSONObject.parseObject(result).getString("token");
            else
                return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取用户信息
     * @param openid
     * @param mid
     * @return
     */
    public static String getUserInfo(String openid,String mid){
        Map map = new HashMap();
        String url = "https://api.weixin.qq.com/cgi-bin/user/info";
        map.put("access_token",getKey(mid));
        /*try {
            map.put("access_token",getWeixinKey());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        map.put("openid",openid);
        map.put("lang","zh_CN");
        try {
            String result = httpClient.doPost(url,map);
            return result;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(getUserInfo("oS8a0jmQQVcxzZgT92tZ7mEoIH1M", getWeixinKey()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
