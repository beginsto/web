package pers.jess.template.jind.model;

import java.util.Date;

public class TimeMachineDetail {
    private Integer id;

    private String phone;

    private String openid;

    private String award;

    private Date createtime;

    private String remarkone;

    private Integer remarktow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemarkone() {
        return remarkone;
    }

    public void setRemarkone(String remarkone) {
        this.remarkone = remarkone == null ? null : remarkone.trim();
    }

    public Integer getRemarktow() {
        return remarktow;
    }

    public void setRemarktow(Integer remarktow) {
        this.remarktow = remarktow;
    }
}