package pers.jess.template.identifyCode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.identifyCode.dao.IdentifyCodeMapper;
import pers.jess.template.identifyCode.model.IdentifyCode;
import pers.jess.template.identifyCode.service.IdentifyCodeService;

import java.util.HashMap;
import java.util.List;

@Service(value = "identifyCodeService")
public class IdentifyCodeServiceImpl implements IdentifyCodeService{

    @Autowired
    private IdentifyCodeMapper identifyCodeMapper;

    public int insertSelective(IdentifyCode record){
        return identifyCodeMapper.insertSelective(record);
    }

    public List<IdentifyCode> queryByParams(HashMap<String, Object> params){
        return identifyCodeMapper.queryByParams(params);
    }

}
