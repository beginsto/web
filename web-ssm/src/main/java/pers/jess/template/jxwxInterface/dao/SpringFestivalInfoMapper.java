package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.SpringFestivalInfo;

import java.util.List;

@Repository
public interface SpringFestivalInfoMapper {

    int insert(SpringFestivalInfo record);

    int queryAmount(Integer period);

    List<SpringFestivalInfo> listInfo(String phone);

}