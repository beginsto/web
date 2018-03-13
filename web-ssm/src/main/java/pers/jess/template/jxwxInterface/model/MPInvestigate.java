package pers.jess.template.jxwxInterface.model;

import java.util.Date;

public class MPInvestigate {
    private Integer id;

    private String phone;

    private String answer;

    private String mpname;

    private Date cretime;

    private String remark;

    private String advice;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getMpname() {
        return mpname;
    }

    public void setMpname(String mpname) {
        this.mpname = mpname == null ? null : mpname.trim();
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}