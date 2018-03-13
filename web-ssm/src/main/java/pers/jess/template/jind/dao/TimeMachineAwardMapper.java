package pers.jess.template.jind.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jind.model.TimeMachineAward;

import java.util.List;

@Repository
public interface TimeMachineAwardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeMachineAward record);

    int insertSelective(TimeMachineAward record);

    TimeMachineAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeMachineAward record);

    int updateByPrimaryKey(TimeMachineAward record);

    List<TimeMachineAward> query();
}