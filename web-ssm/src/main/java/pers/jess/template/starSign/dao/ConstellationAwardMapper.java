package pers.jess.template.starSign.dao;

import pers.jess.template.starSign.model.ConstellationAward;

import java.util.List;

public interface ConstellationAwardMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(ConstellationAward record);

    //int insertSelective(ConstellationAward record);

    //ConstellationAward selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(ConstellationAward record);

    //int updateByPrimaryKey(ConstellationAward record);

    List<ConstellationAward> queryAward(String issue);

    int installAward(ConstellationAward award);

    int insertDataAward(ConstellationAward record);

    int updateData(ConstellationAward record);
}