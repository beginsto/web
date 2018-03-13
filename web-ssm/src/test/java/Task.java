import pers.jess.template.manager.model.AccessToken;
import pers.jess.template.manager.utils.WeChatUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by begins on 17/7/15.
 */
public class Task {

    private static String APPID = "wxc3ea0df9892f27dc";
    private static String  SECRET = "d4624c36b6795d1d99dcf0547af5443d";

    public  AccessToken getAccessToken(){
       Map<String, Object> params = new HashMap<>();
        AccessToken accessToken = new AccessToken();
       /* String url = "https://api.weixin.qq.com/cgi-bin/token";
        params.put("grant_type","client_credential");
        params.put("appid",APPID);
        params.put("secret",SECRET);
        try {
            HttpClient httpService = new HttpClient();
            String result = httpService.doPost(url, params);
            JSONObject json = JSONObject.parseObject(result);
            accessToken.setAccessToken(String.valueOf(json.get("access_token")));
        }catch (Exception ex){
            ex.printStackTrace();
        }*/
        return accessToken;
    }


    public static void main (String[] args){
       /* WeChatUtils weChatUtils = new WeChatUtils();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(weChatUtils.getAccessToken(APPID,SECRET));
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,10000);*/
      /*  LiftOff liftOff = new LiftOff();
        Thread t = new Thread(liftOff);
        t.start();
        System.out.println("Waitting for LiftOff");*/
      for(int i = 0; i < 100; i++){
          new Thread(new LiftOff()).start();

      }
       /* System.out.println("Waitting for LiftOff");
        WeChatUtils weChatUtils = new WeChatUtils();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(weChatUtils.getAccessToken(APPID,SECRET));
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,10000);*/
    }

}
