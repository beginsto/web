package pers.jess.template.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.register.dao.RegisterInfoMapper;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;

import java.util.Map;

@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterInfoMapper registerInfoMapper;

    @Override
    public int insertData(RegisterInfo record){
        return registerInfoMapper.insertSelective(record);
    }

    @Override
    public RegisterInfo queryByParam(Map<String, Object> map){
        return registerInfoMapper.queryByParam(map);
    }

    @Override
    public int quaryAmount(Map<String, Object> map){
        return registerInfoMapper.quaryAmount(map);
    }

    @Override
    public int updateByPrimaryKey(RegisterInfo record){
        return registerInfoMapper.updateByPrimaryKey(record);
    }
}
