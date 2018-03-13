package pers.jess.template.starSign.dao;


import org.springframework.stereotype.Repository;
import pers.jess.template.starSign.model.ConstellationInvite;

import java.util.Map;

@Repository
public interface ConstellationInviteMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(ConstellationInvite record);

    int insertSelective(ConstellationInvite record);

    //ConstellationInvite selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(ConstellationInvite record);

    //int updateByPrimaryKey(ConstellationInvite record);

    //查询当期邀请成功人数
    int queryCountByParam(Map<String, Object> map);

    int queryHelpCount(Map<String, Object>  map);

    //int inserData(ConstellationInvite invite);

    int queryHelpRepeat(Map<String, Object> map);

    int queryInfoCount();
}