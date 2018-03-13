package pers.jess.template.starSign.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.jess.template.common.BaseDao.BaseDao;
import pers.jess.template.starSign.dao.ConstellationInfoMapper;
import pers.jess.template.starSign.model.ConstellationInfo;

import java.util.List;
import java.util.Map;

@Repository
public class ConstellationInfoMapperImpl implements ConstellationInfoMapper {

    private static final String NAMESPACE_SS = "pers.jess.template.starSign.mapper.ConstellationInfoMapper.";

    @Autowired
    private BaseDao baseDao;

    @Override
    public ConstellationInfo queryByParam(Map<String, Object> param){
        return baseDao.selectOne(NAMESPACE_SS+"queryByParam",param);
    }

    @Override
    public int insertData(ConstellationInfo info){
        return baseDao.insert(NAMESPACE_SS+"insertData",info);
    }

    @Override
    public List<ConstellationInfo> queryInfoList(String issue){
        return baseDao.selectList(NAMESPACE_SS+"",issue);
    }
}
