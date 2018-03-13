package pers.jess.template.bindMobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.bindMobile.dao.BindMobileMapper;
import pers.jess.template.bindMobile.model.BindMobile;
import pers.jess.template.bindMobile.service.BindMobileService;

@Service(value = "bindMobileService")
public class BindMobileServiceImpl implements BindMobileService{

    @Autowired
    private BindMobileMapper bindMobileMapper;

    public int insertSelective(BindMobile record){
        return bindMobileMapper.insertSelective(record);
    }
}
