package pers.jess.template.starSign.model;

import java.util.Date;

public class ConstellationInvite {
    private Integer id;

    private String inviterid;

    private String openidserver;

    private String unionid;

    private Integer issuccess;

    private Date createtime;

    private Date successtime;

    private String issue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInviterid() {
        return inviterid;
    }

    public void setInviterid(String inviterid) {
        this.inviterid = inviterid;
    }

    public String getOpenidserver() {
        return openidserver;
    }

    public void setOpenidserver(String openidserver) {
        this.openidserver = openidserver == null ? null : openidserver.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public Integer getIssuccess() {
        return issuccess;
    }

    public void setIssuccess(Integer issuccess) {
        this.issuccess = issuccess;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(Date successtime) {
        this.successtime = successtime;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue == null ? null : issue.trim();
    }
}