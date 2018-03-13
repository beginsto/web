package pers.jess.template.starSign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.starSign.dao.ConstellationAwardMapper;
import pers.jess.template.starSign.dao.ConstellationInfoMapper;
import pers.jess.template.starSign.dao.ConstellationInviteMapper;
import pers.jess.template.starSign.model.ConstellationAward;
import pers.jess.template.starSign.model.ConstellationInfo;
import pers.jess.template.starSign.model.ConstellationInvite;
import pers.jess.template.starSign.service.StarSignService;

import java.util.List;
import java.util.Map;

@Service
public class StarSignServiceImpl implements StarSignService{
    @Autowired
    private ConstellationAwardMapper constellationAwardMapper;

    @Autowired
    private ConstellationInfoMapper constellationInfoMapper;

    @Autowired
    private ConstellationInviteMapper constellationInviteMapper;

    @Override
    public List<ConstellationAward> queryAward(String issue){
        return constellationAwardMapper.queryAward(issue);
    }

    @Override
    public ConstellationInfo queryByParam(Map<String, Object> param){
        return constellationInfoMapper.queryByParam(param);
    }

    @Override
    public int queryCountByParam(Map<String, Object> map){
        return constellationInviteMapper.queryCountByParam(map);
    }

    @Override
    public int installAward(ConstellationAward award){
        return constellationAwardMapper.installAward(award);
    }

    @Override
    public int insertDataAward(ConstellationAward record){
        return constellationAwardMapper.insertDataAward(record);
    }

    @Override
    public  int updateData(ConstellationAward record){
        return constellationAwardMapper.updateData(record);
    }

    @Override
    public  int insertData(ConstellationInfo info){
        return constellationInfoMapper.insertData(info);
    }

    @Override
    public int queryHelpCount(Map<String, Object>  map){
        return constellationInviteMapper.queryHelpCount(map);
    }

    @Override
    public int insertSelective(ConstellationInvite record){
        return constellationInviteMapper.insertSelective(record);
    }

    @Override
    public int queryHelpRepeat(Map<String, Object> map){
        return constellationInviteMapper.queryHelpRepeat(map);
    }

    @Override
    public List<ConstellationInfo> queryInfoList(String issue){
        return constellationInfoMapper.queryInfoList(issue);
    }

    @Override
    public int queryInfoCount(){
        return constellationInviteMapper.queryInfoCount();
    }
}
