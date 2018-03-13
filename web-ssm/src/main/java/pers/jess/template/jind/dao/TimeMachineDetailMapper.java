package pers.jess.template.jind.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.jind.model.TimeMachineDetail;

@Repository
public interface TimeMachineDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeMachineDetail record);

    int insertSelective(TimeMachineDetail record);

    TimeMachineDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeMachineDetail record);

    int updateByPrimaryKey(TimeMachineDetail record);

    TimeMachineDetail queryDetailByPhone(String phone);

    int queryAmount();
}