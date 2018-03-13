package pers.jess.template.register.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.register.model.RegisterInfo;

import java.util.Map;

@Component
public interface RegisterInfoMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(RegisterInfo record);

    int insertSelective(RegisterInfo record);

    RegisterInfo queryByParam(Map<String, Object> map);

    //RegisterInfo selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(RegisterInfo record);

    //int updateByPrimaryKey(RegisterInfo record);

    int quaryAmount(Map<String, Object> map);

    int updateByPrimaryKey(RegisterInfo record);
}