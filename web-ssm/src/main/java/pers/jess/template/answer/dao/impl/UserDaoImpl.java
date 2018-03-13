package pers.jess.template.answer.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.answer.dao.UserMapper;
import pers.jess.template.answer.model.User;
import pers.jess.template.common.BaseDao.BaseDao;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserMapper {

    private static final String NAMESPACE_ANSWER = "pers.jess.template.answer.mapper.UserMapper.";

    @Resource
    private BaseDao baseDao;

    public User queryUserByPhone(String phone){
        return baseDao.selectOne(NAMESPACE_ANSWER + "", phone);
    }
}
