package pers.jess.template.jind.service;

import pers.jess.template.jind.model.TimeMachineAward;
import pers.jess.template.jind.model.TimeMachineDetail;

import java.util.List;

public interface TimeMachineService {

    List<TimeMachineAward> query();

    int insert(TimeMachineDetail record);

    int updateByPrimaryKey(TimeMachineAward record);

    TimeMachineDetail queryDetailByPhone(String phone);

    int queryAmount();
}
