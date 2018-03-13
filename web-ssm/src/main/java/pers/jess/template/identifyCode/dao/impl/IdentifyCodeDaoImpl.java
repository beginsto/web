package pers.jess.template.identifyCode.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.common.BaseDao.BaseDao;
import pers.jess.template.identifyCode.dao.IdentifyCodeMapper;
import pers.jess.template.identifyCode.model.IdentifyCode;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class IdentifyCodeDaoImpl implements IdentifyCodeMapper{

    private static final String NAMESPACE_ANSWER = "pers.jess.template.identifyCode.mapper.identifyCodeMapper.";

    @Resource
    private BaseDao baseDao;

    @Override
    public int insertSelective(IdentifyCode record){
        return baseDao.insert(NAMESPACE_ANSWER + "insertSelective",record);
    }

    @Override
    public List<IdentifyCode> queryByParams(HashMap<String, Object> params){
        return baseDao.selectList(NAMESPACE_ANSWER + "queryByParams", params);
    }
}
