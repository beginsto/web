package pers.jess.template.jxwxInterface.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jxwxInterface.model.LiangPeriod;

import java.util.List;

@Repository
public interface LiangPeriodMapper {

    int insert(LiangPeriod record);

    List<LiangPeriod> listByVersion(Integer version);
}