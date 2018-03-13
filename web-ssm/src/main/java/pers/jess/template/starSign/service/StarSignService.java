package pers.jess.template.starSign.service;

import pers.jess.template.starSign.model.ConstellationAward;
import pers.jess.template.starSign.model.ConstellationInfo;
import pers.jess.template.starSign.model.ConstellationInvite;

import java.util.List;
import java.util.Map;

public interface StarSignService {

    List<ConstellationAward> queryAward(String issue);

    ConstellationInfo queryByParam(Map<String, Object> param);

    int queryCountByParam(Map<String, Object> map);

    int installAward(ConstellationAward award);

    int insertDataAward(ConstellationAward record);

    int updateData(ConstellationAward record);

    int insertData(ConstellationInfo info);

    int queryHelpCount(Map<String, Object>  map);

    int insertSelective(ConstellationInvite record);

    int queryHelpRepeat(Map<String, Object> map);

    List<ConstellationInfo> queryInfoList(String issue);

    int queryInfoCount();
}
