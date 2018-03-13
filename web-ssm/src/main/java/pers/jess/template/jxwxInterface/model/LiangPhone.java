package pers.jess.template.jxwxInterface.model;

import java.util.Date;

public class LiangPhone {
    private Integer id;

    private String phone;

    private Integer isUsed;

    private Date gmtUsed;

    private Integer version;

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

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Date getGmtUsed() {
        return gmtUsed;
    }

    public void setGmtUsed(Date gmtUsed) {
        this.gmtUsed = gmtUsed;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}