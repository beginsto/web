package pers.jess.template.qd.model;

public class QDINFO {
    private Integer id;

    private String phone1;

    private String phone2;

    private String orgname;

    private String cretime;

    private Integer fh;

    private Integer zd;

    private Integer kd;

    private Integer zf;

    private Integer jf;

    private Integer cj;

    private Integer fd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1 == null ? null : phone1.trim();
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2 == null ? null : phone2.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getCretime() {
        return cretime;
    }

    public void setCretime(String cretime) {
        this.cretime = cretime == null ? null : cretime.trim();
    }

    public Integer getFh() {
        return fh;
    }

    public void setFh(Integer fh) {
        this.fh = fh;
    }

    public Integer getZd() {
        return zd;
    }

    public void setZd(Integer zd) {
        this.zd = zd;
    }

    public Integer getKd() {
        return kd;
    }

    public void setKd(Integer kd) {
        this.kd = kd;
    }

    public Integer getZf() {
        return zf;
    }

    public void setZf(Integer zf) {
        this.zf = zf;
    }

    public Integer getJf() {
        return jf;
    }

    public void setJf(Integer jf) {
        this.jf = jf;
    }

    public Integer getCj() {
        return cj;
    }

    public void setCj(Integer cj) {
        this.cj = cj;
    }

    public Integer getFd() {
        return fd;
    }

    public void setFd(Integer fd) {
        this.fd = fd;
    }
}