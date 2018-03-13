package pers.jess.template.football.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.football.dao.FootballMapper;
import pers.jess.template.football.model.Football;
import pers.jess.template.football.service.FootballService;

@Service("footballService")
public class FootballServiceImpl implements FootballService{

    @Autowired
    private FootballMapper footballMapper;

    @Override
    public int insertData(Football record){
        return footballMapper.insertData(record);
    }

    @Override
    public int queryByMobile(String mobile){
        return footballMapper.queryByMobile(mobile);
    }

    @Override
    public int queryCount(){
        return footballMapper.queryCount();
    }
}
