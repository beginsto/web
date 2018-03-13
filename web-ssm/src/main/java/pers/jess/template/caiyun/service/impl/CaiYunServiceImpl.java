package pers.jess.template.caiyun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.caiyun.dao.CaiYunInfoMapper;
import pers.jess.template.caiyun.model.CaiYunInfo;
import pers.jess.template.caiyun.service.CaiYunService;

import java.util.Map;

@Service("caiyunService")
public class CaiYunServiceImpl implements CaiYunService {

    @Autowired
    private CaiYunInfoMapper caiYunInfoMapper;
    @Override
    public int insertData(CaiYunInfo record){
        return caiYunInfoMapper.insertSelective(record);
    }

    @Override
    public CaiYunInfo queryByMobile(Map<String, Object> map){
        return caiYunInfoMapper.queryByMobile(map);
    }

    @Override
    public  int getAmount(String platform){
        return caiYunInfoMapper.getAmount(platform);
    }
}
