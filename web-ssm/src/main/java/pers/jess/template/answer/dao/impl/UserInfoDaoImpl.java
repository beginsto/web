package pers.jess.template.answer.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.answer.dao.UserInfoMapper;
import pers.jess.template.answer.model.User;
import pers.jess.template.answer.model.UserInfo;
import pers.jess.template.common.BaseDao.BaseDao;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository
public class UserInfoDaoImpl implements UserInfoMapper {

    private static final String NAMESPACE_ANSWER = "pers.jess.template.answer.mapper.UserInfoMapper.";

    @Resource
    private BaseDao baseDao;

    @Override
    public UserInfo queryUserInfoByParams(HashMap<String, Object> params){
        return this.baseDao.selectOne(NAMESPACE_ANSWER + "queryUserInfoByParams",params);

    }

    @Override
    public int updateByPrimaryKey(UserInfo record){
        return this.baseDao.update(NAMESPACE_ANSWER + "updateByPrimaryKey",record);
    }

    @Override
    public int insertUserInfo(UserInfo record){
        return this.baseDao.insert(NAMESPACE_ANSWER + "insertUserInfo", record);
    }
}
