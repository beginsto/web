package pers.jess.template.signin.model;

public class SignInLikeAmount {
    private Integer userid;

    private Integer version;

    private Integer amount;

    private String nickname;

    private String headimg;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

}