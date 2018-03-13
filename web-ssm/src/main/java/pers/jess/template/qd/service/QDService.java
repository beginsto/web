package pers.jess.template.qd.service;

import org.springframework.beans.factory.annotation.Autowired;
import pers.jess.template.qd.dao.QDDetailMapper;
import pers.jess.template.qd.model.QDDetail;
import pers.jess.template.qd.model.QDINFO;

import java.util.List;

public interface QDService {

    int insert(QDDetail record);

    QDDetail queryByOpenid(String openid);

    List<QDINFO> query(String phone);
}
