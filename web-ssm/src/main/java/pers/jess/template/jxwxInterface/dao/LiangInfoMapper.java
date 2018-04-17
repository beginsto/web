package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.LiangInfo;

import java.util.List;


@Repository
public interface LiangInfoMapper {

    int insert(LiangInfo record);

    LiangInfo queryByIdCard(String idCardNo);

    List<LiangInfo> queryByPhone(String phone);


}