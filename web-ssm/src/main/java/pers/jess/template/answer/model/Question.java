package pers.jess.template.answer.model;

import org.springframework.stereotype.Component;

@Component
public class Question {
    private Integer id;

    private String question;

    private String questype;

    private String refrenceres;

    private String answerright;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String answerE;

    private String answerF;

    private String businesstype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getQuestype() {
        return questype;
    }

    public void setQuestype(String questype) {
        this.questype = questype == null ? null : questype.trim();
    }

    public String getRefrenceres() {
        return refrenceres;
    }

    public void setRefrenceres(String refrenceres) {
        this.refrenceres = refrenceres == null ? null : refrenceres.trim();
    }

    public String getAnswerright() {
        return answerright;
    }

    public void setAnswerright(String answerright) {
        this.answerright = answerright == null ? null : answerright.trim();
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA == null ? null : answerA.trim();
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB == null ? null : answerB.trim();
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC == null ? null : answerC.trim();
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD == null ? null : answerD.trim();
    }

    public String getAnswerE() {
        return answerE;
    }

    public void setAnswerE(String answerE) {
        this.answerE = answerE == null ? null : answerE.trim();
    }

    public String getAnswerF() {
        return answerF;
    }

    public void setAnswerF(String answerF) {
        this.answerF = answerF == null ? null : answerF.trim();
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype == null ? null : businesstype.trim();
    }
}