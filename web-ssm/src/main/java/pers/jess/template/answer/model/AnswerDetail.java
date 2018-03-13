package pers.jess.template.answer.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AnswerDetail {
    private Integer id;

    private Integer userid;

    private Integer quesid;

    private String answerright;

    private String answer;

    private Date answertime;

    private String businesstype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getQuesid() {
        return quesid;
    }

    public void setQuesid(Integer quesid) {
        this.quesid = quesid;
    }

    public String getAnswerright() {
        return answerright;
    }

    public void setAnswerright(String answerright) {
        this.answerright = answerright == null ? null : answerright.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Date getAnswertime() {
        return answertime;
    }

    public void setAnswertime(Date answertime) {
        this.answertime = answertime;
    }

    public String getBusinessType(){
        return businesstype;
    }

    public void setBusinessType(String businesstype){
        this.businesstype = businesstype;
    }
}