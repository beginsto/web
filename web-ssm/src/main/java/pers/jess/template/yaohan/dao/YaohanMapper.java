package pers.jess.template.yaohan.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.yaohan.model.Yaohan;

@Repository
public interface YaohanMapper {

    Yaohan queryByCounpon(String coupon);

    Yaohan queryByPhone(String phone);

    int updateByPrimaryKey(Yaohan record);
}