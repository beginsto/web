package pers.jess.template.fxll.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.fxll.dao.FxllMapper;
import pers.jess.template.fxll.model.Fxll;
import pers.jess.template.fxll.service.FxllService;

@Service("fxllService")
public class FxllServiceImpl implements FxllService {

    @Autowired
    private FxllMapper fxllMapper;

    @Override
    public int insert(Fxll record){
        return fxllMapper.insert(record);
    }

    @Override
    public Fxll selectByMobile(String mobile){
        return fxllMapper.selectByMobile(mobile);
    }
}
