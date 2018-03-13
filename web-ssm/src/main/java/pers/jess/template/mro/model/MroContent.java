package pers.jess.template.mro.model;

public class MroContent {
    private Integer id;

    private String area;

    private String title;

    private String imgBanner;

    private String imgTop;

    private String imgBottom;

    private Integer state;

    private Integer issue;

    private String bottomTitle;

    private String introduce;

    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImgBanner() {
        return imgBanner;
    }

    public void setImgBanner(String imgBanner) {
        this.imgBanner = imgBanner == null ? null : imgBanner.trim();
    }

    public String getImgTop() {
        return imgTop;
    }

    public void setImgTop(String imgTop) {
        this.imgTop = imgTop == null ? null : imgTop.trim();
    }

    public String getImgBottom() {
        return imgBottom;
    }

    public void setImgBottom(String imgBottom) {
        this.imgBottom = imgBottom == null ? null : imgBottom.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public String getBottomTitle() {
        return bottomTitle;
    }

    public void setBottomTitle(String bottomTitle) {
        this.bottomTitle = bottomTitle == null ? null : bottomTitle.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getAmount(){
        return amount;
    }

    public void setAmount(Integer amount){
        this.amount = amount;
    }
}