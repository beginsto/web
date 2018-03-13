package pers.jess.template.gift.model;

import java.util.Date;

public class GiftOnline {
    private Integer id;

    private String codeNum;

    private String codedesc;

    private String codeId;

    private Integer isUsed;

    private Date useTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(String codeNum) {
        this.codeNum = codeNum == null ? null : codeNum.trim();
    }

    public String getCodedesc() {
        return codedesc;
    }

    public void setCodedesc(String codedesc) {
        this.codedesc = codedesc == null ? null : codedesc.trim();
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId == null ? null : codeId.trim();
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}