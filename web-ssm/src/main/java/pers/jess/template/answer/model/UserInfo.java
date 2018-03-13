package pers.jess.template.answer.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserInfo {
    private Integer id;

    private Integer userid;

    private String issue;

    private Integer ansZh;

    private Integer ansRigZh;

    private Integer ansBus;

    private Integer ansRigBus;

    private Integer ansGov;

    private Integer ansRigGov;

    private Integer nextques;

    private Date lasttime;

    private Date createtime;

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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }

    public Integer getAnsZh() {
        return ansZh;
    }

    public void setAnsZh(Integer ansZh) {
        this.ansZh = ansZh;
    }

    public Integer getAnsRigZh() {
        return ansRigZh;
    }

    public void setAnsRigZh(Integer ansRigZh) {
        this.ansRigZh = ansRigZh;
    }

    public Integer getAnsBus() {
        return ansBus;
    }

    public void setAnsBus(Integer ansBus) {
        this.ansBus = ansBus;
    }

    public Integer getAnsRigBus() {
        return ansRigBus;
    }

    public void setAnsRigBus(Integer ansRigBus) {
        this.ansRigBus = ansRigBus;
    }

    public Integer getAnsGov() {
        return ansGov;
    }

    public void setAnsGov(Integer ansGov) {
        this.ansGov = ansGov;
    }

    public Integer getAnsRigGov() {
        return ansRigGov;
    }

    public void setAnsRigGov(Integer ansRigGov) {
        this.ansRigGov = ansRigGov;
    }

    public Integer getNextques() {
        return nextques;
    }

    public void setNextques(Integer nextques) {
        this.nextques = nextques;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}