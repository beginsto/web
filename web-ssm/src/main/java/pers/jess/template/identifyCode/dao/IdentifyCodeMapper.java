package pers.jess.template.identifyCode.dao;

import pers.jess.template.identifyCode.model.IdentifyCode;

import java.util.HashMap;
import java.util.List;

public interface IdentifyCodeMapper {

    int insertSelective(IdentifyCode record);

    List<IdentifyCode> queryByParams(HashMap<String, Object> params);


}