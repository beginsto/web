package pers.jess.template.localphone.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.localphone.dao.LocalPhoneMapper;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

@Service(value = "localPhoneService")
public class LocalPhoneServiceImpl implements LocalPhoneService {

    @Autowired
    private LocalPhoneMapper localPhoneMapper;

    @Override
    public LocalPhone quaryByPhone(String phone){
        return localPhoneMapper.quaryByPhone(phone);
    }
}




