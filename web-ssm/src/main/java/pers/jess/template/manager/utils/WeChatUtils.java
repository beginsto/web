package pers.jess.template.manager.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.manager.model.AccessToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by begins on 17/7/16.
 */
public class WeChatUtils {
    private Logger log = Logger.getLogger(WeChatUtils.class);

    private final String appId = "wxc3ea0df9892f27dc";

    private final String appSecret = "d4624c36b6795d1d99dcf0547af5443d";

    // 获取access_token的接口地址（GET） 限200（次/天）
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token";

    // 菜单创建（POST） 限100（次/天）
    public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";


    /**
     *
     * @param appId 凭证
     *
     *
     * @param appSecret 密钥
     * @return AccessToken
     */
    public AccessToken getAccessToken(String appId, String appSecret){
        log.info("获取token...");
        Map<String, Object> params = new HashMap<>();
        AccessToken accessToken = new AccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        params.put("grant_type","client_credential");
        params.put("appid",appId);
        params.put("secret",appSecret);
        try {
            HttpClient httpService = new HttpClient();
            String result = httpService.doPost(url, params);
            JSONObject json = JSONObject.parseObject(result);
            accessToken.setAccessToken(String.valueOf(json.get("access_token")));
        }catch (Exception ex){
            log.error("获取token失败,err:"+ex);
            ex.printStackTrace();
        }
        return accessToken;
    }

}
