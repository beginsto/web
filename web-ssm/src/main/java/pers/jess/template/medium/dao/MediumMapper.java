package pers.jess.template.medium.dao;

import pers.jess.template.medium.model.Medium;

public interface MediumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Medium record);

    int insertSelective(Medium record);

    Medium selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Medium record);

    int updateByPrimaryKey(Medium record);
}