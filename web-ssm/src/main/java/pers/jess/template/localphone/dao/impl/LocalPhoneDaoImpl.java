package pers.jess.template.localphone.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.common.BaseDao.BaseDao;
import pers.jess.template.localphone.dao.LocalPhoneMapper;
import pers.jess.template.localphone.model.LocalPhone;

@Repository
public class LocalPhoneDaoImpl implements LocalPhoneMapper {

    private static final String NAMESPCAE_LP = "pers.jess.template.localphone.mapper.LocalPhoneMapper";

    private BaseDao baseDao;

    @Override
    public LocalPhone quaryByPhone(String phone){
        return this.baseDao.selectOne(NAMESPCAE_LP + "",phone);
    }
}
