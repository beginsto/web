package pers.jess.template.jind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.jind.dao.TimeMachineAwardMapper;
import pers.jess.template.jind.dao.TimeMachineDetailMapper;
import pers.jess.template.jind.model.TimeMachineAward;
import pers.jess.template.jind.model.TimeMachineDetail;
import pers.jess.template.jind.service.TimeMachineService;

import java.util.List;

@Service
public class TimeMachineServiceImpl implements TimeMachineService{

    @Autowired
    private TimeMachineAwardMapper  timeMachineAwardMapper;

    @Autowired
    private TimeMachineDetailMapper timeMachineDetailMapper;

    
    @Override
    public List<TimeMachineAward> query(){
        return timeMachineAwardMapper.query();
    }

    @Override
    public int insert(TimeMachineDetail record){
        return timeMachineDetailMapper.insert(record);
    }

    @Override
    public  int updateByPrimaryKey(TimeMachineAward record){
        return timeMachineAwardMapper.updateByPrimaryKey(record);
    }

    @Override
    public TimeMachineDetail queryDetailByPhone(String phone){
        return timeMachineDetailMapper.queryDetailByPhone(phone);
    }

    @Override
    public int queryAmount(){
        return timeMachineDetailMapper.queryAmount();
    }
}
