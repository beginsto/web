package pers.jess.template.localphone.model;

import org.springframework.stereotype.Component;

@Component
public class LocalPhone {
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}