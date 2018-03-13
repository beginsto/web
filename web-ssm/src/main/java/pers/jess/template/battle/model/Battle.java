package pers.jess.template.battle.model;


public class Battle {

    private String teamId;
    private String teamName;
    private Integer rkTeam;
    private String reportDate;


    public Battle(String teamId,String teamName, Integer rkTeam, String reportDate){
        this.reportDate = reportDate;
        this.teamId = teamId;
        this.teamName = teamName;
        this.rkTeam =rkTeam;
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

    public Integer getRkTeam(){
        return rkTeam;
    }

    public void setRkTeam(Integer rkTeam){
        this.rkTeam = rkTeam;
    }

    public String getReportDate(){
        return reportDate;
    }

    public void setReportDate(String reportDate){
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
