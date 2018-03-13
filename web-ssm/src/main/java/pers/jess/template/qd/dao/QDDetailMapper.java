package pers.jess.template.qd.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.qd.model.QDDetail;

@Repository
public interface QDDetailMapper {

    int insert(QDDetail record);

    QDDetail queryByOpenid(String openid);

}