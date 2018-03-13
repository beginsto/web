package pers.jess.template.starSign.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.jess.template.common.BaseDao.BaseDao;
import pers.jess.template.starSign.dao.ConstellationAwardMapper;
import pers.jess.template.starSign.model.ConstellationAward;

import java.util.List;

@Repository
public class ConstellationAwardMapperImpl implements ConstellationAwardMapper {

    private static final String NAMESPACE_SS = "pers.jess.template.starSign.mapper.ConstellationAwardMapper.";

    @Autowired
    private BaseDao baseDao;

    @Override
    public List<ConstellationAward> queryAward(String  issue){
        return baseDao.selectList(NAMESPACE_SS+"queryAward",issue);
    }

    @Override
    public int installAward(ConstellationAward award){
        return baseDao.update(NAMESPACE_SS+"installAward",award);
    }

    @Override
    public int insertDataAward(ConstellationAward record){
        return baseDao.insert(NAMESPACE_SS+"insertDataAward",record);
    }

    @Override
    public int updateData(ConstellationAward record){
        return baseDao.update(NAMESPACE_SS+"updateData",record);
    }
}
