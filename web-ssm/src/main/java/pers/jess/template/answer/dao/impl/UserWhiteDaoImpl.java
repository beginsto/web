package pers.jess.template.answer.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.answer.dao.UserWhiteMapper;
import pers.jess.template.answer.model.UserWhite;
import pers.jess.template.common.BaseDao.BaseDao;

import java.util.List;

@Repository
public class UserWhiteDaoImpl implements UserWhiteMapper {

    private static final String NAMESPACE_ANSWER = "pers.jess.template.answer.mapper.UserWhiteMapper.";

    private BaseDao baseDao;

    @Override
    public List<UserWhite> queryAllUserWhite(){
        return this.baseDao.selectList(NAMESPACE_ANSWER + "queryAllUserWhite");
    }
}
