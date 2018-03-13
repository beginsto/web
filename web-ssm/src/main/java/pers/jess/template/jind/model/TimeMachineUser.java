package pers.jess.template.jind.model;

public class TimeMachineUser {


    //String result = "{\"resultCode\":200,\"resultContent\":
    // {\"phone\":\"13758300969\",\"nick\":\"惠\",\"status\":1,\
    // "headimgurl\":\"http://wx.qlogo.cn/mmopen/5cJ329xUeTwSssNMvsLH1dT4gq6skSGPo2Cv51BnWW02003WPJOyIQyfzWBV5zOD8AQHVHX59dhob2ayI8Y9aA/0\",\"openId\":\"oS8a0jrx8og_nwagf23PYk0TcOks\"}}";

    private String openid;

    private String phone;

    private String headimgurl;

    private String nick;

    public String getOpenid(){
        return openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getHeadimgurl(){
        return headimgurl;

    }

    public void setHeadimgurl(String headimgurl){
        this.headimgurl = headimgurl;
    }

    public String getNick(){
        return nick;
    }

    public void setNick(String nick){
        this.nick = nick;
    }
}
