package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.LiangInfo;


@Repository
public interface LiangInfoMapper {

    int insert(LiangInfo record);

    LiangInfo queryByIdCard(String idCardNo);

    LiangInfo queryByPhone(String phone);


}