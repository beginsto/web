package pers.jess.template.answer.dao;

import pers.jess.template.answer.model.UserWhite;

import java.util.List;

public interface UserWhiteMapper {
    /*int deleteByPrimaryKey(Integer id);

    int insert(UserWhite record);

    int insertSelective(UserWhite record);

    UserWhite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWhite record);

    int updateByPrimaryKey(UserWhite record);*/

    List<UserWhite> queryAllUserWhite();
}