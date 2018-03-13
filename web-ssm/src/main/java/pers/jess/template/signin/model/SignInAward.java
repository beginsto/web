package pers.jess.template.signin.model;

import java.util.Date;

public class SignInAward {
    private Integer userid;

    private Integer operate;

    private Integer award;

    private Date createtime;

    private Integer version;

    private String nickname;

    private String headimg;

    private String signintime;

    public SignInAward(){

    }

    public SignInAward(Integer userid,Integer operate, Integer award, Date createtime, Integer version){
        this.userid = userid;
        this.operate = operate;
        this.award = award;
        this.createtime = createtime;
        this.version = version;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getHeadimg(){
        return headimg;
    }

    public void setHeadimg(String headimg){
        this.headimg = headimg;
    }

    public String getSignintime(){
        return signintime;
    }

    public void setSignintime(String signintime){
        this.signintime = signintime;
    }
}