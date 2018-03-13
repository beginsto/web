package pers.jess.template.common.utils;

import pers.jess.template.common.httpclient.HttpClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class GetWeChatUserInfo {

    private static HttpClient httpClient = null;

    private static final String userInfoUrl = "http://card.1860.cn/public/zjydwx/service/getWxUserInfo";

    public static String getUserInfo(String openid){
        Map map = new HashMap();
        httpClient = new HttpClient();
        map.put("openId",openid);
        try {
            return httpClient.doPost(userInfoUrl,map);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
