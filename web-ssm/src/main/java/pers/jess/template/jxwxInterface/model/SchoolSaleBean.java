package pers.jess.template.jxwxInterface.model;

public class SchoolSaleBean {

    private String phone;

    private String schoolname;

    private String classes;

    private String bussiness;

    private String img;

    private String gmtCreate;

    private Integer pass;

    public SchoolSaleBean(){}

    public SchoolSaleBean(String phone,String schoolname,String classes,String bussiness,String img,String gmtCreate, Integer pass){
        this.phone=phone;
        this.classes = classes;
        this.schoolname=schoolname;
        this.bussiness=bussiness;
        this.img=img;
        this.gmtCreate=gmtCreate;
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBussiness() {
        return bussiness;
    }

    public void setBussiness(String bussiness) {
        this.bussiness = bussiness;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }
}
