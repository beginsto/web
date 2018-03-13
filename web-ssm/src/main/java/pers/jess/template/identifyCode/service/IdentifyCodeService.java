package pers.jess.template.identifyCode.service;

import pers.jess.template.identifyCode.model.IdentifyCode;

import java.util.HashMap;
import java.util.List;

public interface IdentifyCodeService {

    int insertSelective(IdentifyCode record);

    List<IdentifyCode> queryByParams(HashMap<String, Object> params);
}
