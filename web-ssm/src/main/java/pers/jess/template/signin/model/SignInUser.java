package pers.jess.template.signin.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

public class SignInUser {
    private Integer id;

    private String openid;

    private String mobile;

    private String nickname;

    private String headimg;

    private Date createtime;

    private Integer maxcount;

    private String reserve;

    private Integer count;

    private Boolean isSign;

    private String signintime;

    private Integer award;

    private Integer awardtoday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getMaxcount() {
        return maxcount;
    }

    public void setMaxcount(Integer maxcount) {
        this.maxcount = maxcount;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    public Integer getCount(){
        return count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

    public Boolean getIsSign() {
         return isSign;
    }

    public void setIsSign(Boolean isSign){
        this.isSign = isSign;
    }

    public String getSignintime(){
        return signintime;
    }

    public void setSignintime(String signintime){
        this.signintime = signintime;
    }

    public Integer getAward(){
        return award;
    }

    public void setAward(Integer award){
        this.award = award;
    }

    public Integer getAwardtoday(){
        return awardtoday;
    }

    public void setAwardtoday(Integer awardtoday){
        this.awardtoday = awardtoday;
    }
}