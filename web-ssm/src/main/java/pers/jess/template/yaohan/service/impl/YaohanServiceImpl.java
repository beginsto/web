package pers.jess.template.yaohan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.yaohan.dao.YaohanMapper;
import pers.jess.template.yaohan.model.Yaohan;
import pers.jess.template.yaohan.service.YaohanService;

@Service
public class YaohanServiceImpl implements YaohanService {

    @Autowired
    private YaohanMapper yaohanMapper;

    @Override
    public Yaohan queryByCounpon(String coupon){
        return yaohanMapper.queryByCounpon(coupon);
    }

    public int updateByPrimaryKey(Yaohan record){
        return yaohanMapper.updateByPrimaryKey(record);
    }

    @Override
    public Yaohan queryByPhone(String phone){
        return yaohanMapper.queryByPhone(phone);
    }
}
