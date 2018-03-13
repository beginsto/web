package pers.jess.template.localphone.dao;

import pers.jess.template.localphone.model.LocalPhone;

public interface LocalPhoneMapper {

    LocalPhone quaryByPhone(String phone);
}