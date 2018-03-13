package pers.jess.template.battle.model;

public class BattleArea {

    private String areaName;
    private String centerName;
    private String teamId;
    private String teamName;
    private String reportDate;
    private Integer rk;

    public String getAreaName(){
        return areaName;
    }

    public void setAreaName(String areaName){
        this.areaName = areaName;
    }

    public String getCenterName(){
        return centerName;
    }

    public void setCenterName(String centerName){
        this.centerName = centerName;
    }

    public String getTeamId(){
        return teamId;
    }

    public void setTeamId(String teamId){
        this.teamId = teamId;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public String getReportDate(){
        return reportDate;
    }

    public void setReportDate(String reportDate){
        this.reportDate = reportDate;
    }

    public Integer getRk(){
        return rk;
    }

    public void setRk(Integer rk){
        this.rk = rk;
    }
}
