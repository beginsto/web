package pers.jess.template.yaohan.service;

import pers.jess.template.yaohan.model.Yaohan;

public interface YaohanService {

    Yaohan queryByCounpon(String coupon);

    int updateByPrimaryKey(Yaohan record);

    Yaohan queryByPhone(String phone);
}
