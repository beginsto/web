package pers.jess.template.jind.model;

import org.apache.ibatis.annotations.Param;

public class TMData {


    /**
     * 手机号码
     */
    private String billId;

    private Integer cntSjyytLogin;

    private Integer cntSjyytYw;

    private Integer cntJyz;

    private Integer moneyJyz;

    private Integer mtdQqwShortCallDurationM;

    private Integer mtdCallDurationM;

    private Integer rkCall;

    private Integer kdUseDays;

    private Double gprsVolume;

    private Double gprsVolumeAvg;

    private Integer rkGprs;

    private Integer cntQqw;

    public String getBillId(){
        return billId;
    }

    public void setBillId(String billId){
        this.billId = billId;
    }

    public Integer getCntSjyytLogin(){
        return cntSjyytLogin;
    }

    public void setCntSjyytLogin(Integer cntSjyytLogin){
        this.cntSjyytLogin = cntSjyytLogin;
    }

    public Integer getCntSjyytYw(){
        return cntSjyytYw;
    }

    public void setCntSjyytYw(Integer cntSjyytYw){
        this.cntSjyytYw =cntSjyytYw;
    }

    public Integer getCntJyz(){
        return cntJyz;
    }

    public void setCntJyz(Integer cntJyz){
        this.cntJyz = cntJyz;
    }

    public Integer getMoneyJyz(){
        return moneyJyz;
    }

    public void setMoneyJyz(Integer moneyJyz){
        this.moneyJyz = moneyJyz;
    }

    public Integer getMtdQqwShortCallDurationM(){
        return mtdQqwShortCallDurationM;
    }

    public void setMtdQqwShortCallDurationM(Integer mtdQqwShortCallDurationM){
        this.mtdQqwShortCallDurationM = mtdQqwShortCallDurationM;
    }

    public Integer getMtdCallDurationM(){
        return mtdCallDurationM;
    }

    public void setMtdCallDurationM(Integer mtdCallDurationM){
        this.mtdCallDurationM = mtdCallDurationM;
    }

    public Integer getRkCall(){
        return rkCall;
    }

    public void setRkCall(Integer rkCall){
        this.rkCall = rkCall;
    }

    public Integer getKdUseDays(){
        return kdUseDays;
    }

    public void setKdUseDays(Integer kdUseDays){
        this.kdUseDays = kdUseDays;
    }

    public Double getGprsVolume(){
        return gprsVolume;
    }

    public void setGprsVolume(Double gprsVolume){
        this.gprsVolume = gprsVolume;
    }

    public Double getGprsVolumeAvg(){
        return gprsVolumeAvg;
    }

    public void setGprsVolumeAvg(Double gprsVolumeAvg) {
        this.gprsVolumeAvg = gprsVolumeAvg;
    }

    public Integer getCntQqw() {
        return cntQqw;
    }

    public void setCntQqw(Integer cntQqw) {
        this.cntQqw = cntQqw;
    }

    public Integer getRkGprs() {
        return rkGprs;
    }

    public void setRkGprs(Integer rkGprs) {
        this.rkGprs = rkGprs;
    }
}
