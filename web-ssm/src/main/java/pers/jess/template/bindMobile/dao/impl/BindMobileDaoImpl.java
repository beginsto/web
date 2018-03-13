package pers.jess.template.bindMobile.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pers.jess.template.bindMobile.dao.BindMobileMapper;
import pers.jess.template.bindMobile.model.BindMobile;
import pers.jess.template.common.BaseDao.BaseDao;

@Repository
public class BindMobileDaoImpl implements BindMobileMapper{
    private static final String NAMESPACE_BM = "pers.jess.template.bindMobile.mapper.BindMobileMapper.";

    @Autowired
    private BaseDao baseDao;

    public int insertSelective(BindMobile record){
        return baseDao.insert(NAMESPACE_BM + "insertSelective",record);
    }
}
