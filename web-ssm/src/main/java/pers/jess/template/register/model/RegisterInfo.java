package pers.jess.template.register.model;

import java.util.Date;

public class RegisterInfo {
    private Integer id;

    private String mobile;

    private String source;

    private Date createtime;

    private String reserveone;

    private String reservetwo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReserveone() {
        return reserveone;
    }

    public void setReserveone(String reserveone) {
        this.reserveone = reserveone == null ? null : reserveone.trim();
    }

    public String getReservetwo() {
        return reservetwo;
    }

    public void setReservetwo(String reservetwo) {
        this.reservetwo = reservetwo == null ? null : reservetwo.trim();
    }
}