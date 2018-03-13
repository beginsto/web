package pers.jess.template.answer.model;

import org.springframework.stereotype.Component;

@Component
public class UserWhite {
    private Integer id;

    private String phone;

    private String username;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}