package pers.jess.template.answer.dao;

import pers.jess.template.answer.model.User;
import pers.jess.template.answer.model.UserInfo;

import java.util.HashMap;

public interface UserInfoMapper {
   /* int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);*/

    int updateByPrimaryKey(UserInfo record);

    UserInfo queryUserInfoByParams(HashMap<String, Object> params);

    int insertUserInfo(UserInfo record);

}