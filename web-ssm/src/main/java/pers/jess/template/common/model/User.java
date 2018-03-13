package pers.jess.template.common.model;

public class User {

    private  String openid;

    private Integer status;

    private String phone;

    private String nick;

    private String headimgurl;

    private String unionid;

    public User(){

    }

    public User(String openid,Integer status,String phone,String nick,String headimgurl,String unionid){
        this.openid = openid;
        this.status = status;
        this.phone = phone;
        this.nick = nick;
        this.headimgurl = headimgurl;
        this.unionid = unionid;
    }

    public String getOpenid(){
        return openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getNick(){
        return nick;
    }

    public void setNick(String nick){
        this.nick = nick;
    }

    public String getHeadimgurl(){
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl){
        this.headimgurl = headimgurl;
    }

    public String getUnionid(){
        return unionid;
    }

    public void setUnionid(String unionid){
        this.unionid = unionid;
    }

}
