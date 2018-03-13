package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.LiangPhone;

import java.util.List;

@Repository
public interface LiangPhoneMapper {

    int insert(LiangPhone record);

    List<LiangPhone> list();

    int updateSetUsed(Integer id);

    LiangPhone queryById(Integer id);


}