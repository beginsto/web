package pers.jess.template.starSign.dao;

import pers.jess.template.starSign.model.ConstellationInfo;

import java.util.List;
import java.util.Map;

public interface ConstellationInfoMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(ConstellationInfo record);

    //int insertSelective(ConstellationInfo record);

    //ConstellationInfo selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(ConstellationInfo record);

    //int updateByPrimaryKey(ConstellationInfo record);

    ConstellationInfo queryByParam(Map<String, Object> param);

    int insertData(ConstellationInfo info);

    List<ConstellationInfo> queryInfoList(String issue);
}