package pers.jess.template.jxwxInterface.model;

import java.util.Date;

public class BroadbandFault {
    private Integer id;

    private String phone;

    private String area;

    private String addr;

    private String faultDesc;

    private Date gmtCreate;

    private Date gmtModified;

    private String remarkOne;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc == null ? null : faultDesc.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getRemarkOne() {
        return remarkOne;
    }

    public void setRemarkOne(String remarkOne) {
        this.remarkOne = remarkOne == null ? null : remarkOne.trim();
    }
}