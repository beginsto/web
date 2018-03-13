package pers.jess.template.signin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.signin.dao.*;
import pers.jess.template.signin.model.*;
import pers.jess.template.signin.service.SignInService;

import java.util.List;
import java.util.Map;


@Service("signinService")
public class SignInServiceImpl implements SignInService{

    @Autowired
    private SignInUserMapper signInUserMapper;

    @Autowired
    private SignInInfoMapper signInInfoMapper;

    @Autowired
    private SignInAwardMapper   signInAwardMapper;

    @Autowired
    private SignInInviteMapper signInInviteMapper;

    @Autowired
    private SignInLikeAmountMapper signInLikeAmountMapper;

    @Override
    public int insert(SignInUser record){
        return signInUserMapper.insert(record);
    }

    @Override
    public SignInUser queryByOpenid(String  openid){
        return signInUserMapper.queryByOpenid(openid);
    }

    @Override
    public int updateByPrimaryKey(SignInUser record){
        return signInUserMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SignInUser> getParticipator(){
        return signInUserMapper.getParticipator();
    }

    @Override
    public  int queryJoinCount(){
        return signInUserMapper.queryJoinCount();
    }

    @Override
    public int insert(SignInInfo record){
        return signInInfoMapper.insert(record);
    }

    @Override
    public SignInInfo queryByPrimaryKey(Integer userid){
        return signInInfoMapper.queryByPrimaryKey(userid);
    }

    @Override
    public int updateByPrimaryKey(SignInInfo record){
        return signInInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public   SignInInfo queryByParams(Map<String, Object> map){
        return signInInfoMapper.queryByParams(map);
    }

    @Override
    public int insert(SignInAward record){
        return signInAwardMapper.insert(record);
    }

    @Override
    public SignInAward selectByParams(Map<String, Object> map){
        return signInAwardMapper.selectByParams(map);
    }

    @Override
    public  List<SignInAward> querySignList(Integer userid){
        return signInAwardMapper.querySignList(userid);
    }

    @Override
    public List<SignInAward> querySignListByVersion(Integer version){
        return signInAwardMapper.querySignListByVersion(version);
    }

    @Override
    public List<SignInInvite> queryRankFriendList(Map<String, Object> map){
        return signInInviteMapper.queryRankFriendList(map);
    }

    @Override
    public List<SignInAward> queryRankWorld(Integer verison){
        return signInAwardMapper.queryRankWorld(verison);
    }

    @Override
    public List<SignInLikeAmount> queryRankPopularity(Integer version){
        return signInLikeAmountMapper.queryRankPopularity(version);
    }

    @Override
    public SignInInvite queryData(Map<String, Object> map){
        return signInInviteMapper.queryData(map);
    }

    @Override
    public SignInLikeAmount queryLikeData(Map<String, Object> map){
        return signInLikeAmountMapper.queryData(map);
    }

    @Override
    public int updateLikeData(SignInLikeAmount record){
        return signInLikeAmountMapper.updateLikeData(record);
    }

    @Override
    public int insertLikeData(SignInLikeAmount record){
        return signInLikeAmountMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(SignInInvite record){
        return signInInviteMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertInviteData(SignInInvite record){
        return signInInviteMapper.insert(record);
    }


}
