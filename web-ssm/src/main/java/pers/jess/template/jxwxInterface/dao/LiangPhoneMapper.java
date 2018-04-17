package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.LiangPhone;

import java.util.List;
import java.util.Map;

@Repository
public interface LiangPhoneMapper {

    int insert(LiangPhone record);

    List<LiangPhone> list();

    int updateSetUsed(Integer id);

    LiangPhone queryById(Map<String, Integer> param);

}