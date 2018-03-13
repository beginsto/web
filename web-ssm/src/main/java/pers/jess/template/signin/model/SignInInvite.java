package pers.jess.template.signin.model;

import sun.jvm.hotspot.HelloWorld;

import java.util.Date;

public class SignInInvite {
    private Integer userid;

    private Integer friendid;

    private Date createtime;

    private Date lasttime;

    private Integer isLike;

    private String headimg;
    private String nickname;
    private String signintime;
    private Integer amount;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Integer getIsLike(){
        return isLike;
    }

    public void setIsLike(Integer isLike){
        this.isLike = isLike;
    }

    public String getHeadimg(){
        return headimg;
    }

    public void setHeadimg(String headimg){
        this.headimg = headimg;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getSignintime(){
        return signintime;
    }

    public  void setSignintime(String signintime){
        this.signintime = signintime;
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount){
        this.amount =  amount;
    }
}