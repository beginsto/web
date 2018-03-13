package pers.jess.template.register.service;

import pers.jess.template.register.model.RegisterInfo;

import java.util.Map;

public interface RegisterService {

    int insertData(RegisterInfo record);

    RegisterInfo queryByParam(Map<String, Object> map);

    int quaryAmount(Map<String, Object> map);

    int updateByPrimaryKey(RegisterInfo record);
}
