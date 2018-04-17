package pers.jess.template.jxwxInterface.model;

public class QuestionAnswer {
    private Integer id;

    private String question;

    private String answerOne;

    private String answerTwo;

    private String answerThree;

    private String answerRight;

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

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne == null ? null : answerOne.trim();
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo == null ? null : answerTwo.trim();
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree == null ? null : answerThree.trim();
    }

    public String getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(String answerRight) {
        this.answerRight = answerRight == null ? null : answerRight.trim();
    }
}