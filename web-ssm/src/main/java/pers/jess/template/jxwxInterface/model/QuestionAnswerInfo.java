package pers.jess.template.jxwxInterface.model;

import java.util.Date;

public class QuestionAnswerInfo {
    private Integer id;

    private String phone;

    private String openid;

    private Integer prizeid;

    private Date gmtCreate;

    private String question;

    private String answer;

    private Integer period;

    public QuestionAnswerInfo(){}

    public QuestionAnswerInfo(Integer id, String phone, String openid, Integer prizeid, Date gmtCreate,
                              String question, String answer, Integer period){
        this.id = id;
        this.period = period;
        this.phone = phone;
        this.openid = openid;
        this.prizeid = prizeid;
        this.gmtCreate = gmtCreate;
        this.question = question;
        this.answer = answer;
    }

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

    public Integer getPrizeid() {
        return prizeid;
    }

    public void setPrizeid(Integer prizeid) {
        this.prizeid = prizeid;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}