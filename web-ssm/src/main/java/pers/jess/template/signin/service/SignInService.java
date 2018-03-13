package pers.jess.template.signin.service;

import pers.jess.template.signin.model.*;

import java.util.List;
import java.util.Map;

public interface SignInService {

    int insert(SignInUser record);

    SignInUser queryByOpenid(String  openid);

    int updateByPrimaryKey(SignInUser record);

    List<SignInUser> getParticipator();

    int queryJoinCount();

    int insert(SignInInfo record);

    SignInInfo queryByPrimaryKey(Integer userid);

    int updateByPrimaryKey(SignInInfo record);

    SignInInfo queryByParams(Map<String, Object> map);

    int insert(SignInAward record);

    SignInAward selectByParams(Map<String, Object> map);

    List<SignInAward> querySignList(Integer userid);

    List<SignInAward> querySignListByVersion(Integer version);

    List<SignInInvite> queryRankFriendList(Map<String, Object> map);

    List<SignInAward> queryRankWorld(Integer verison);

    List<SignInLikeAmount> queryRankPopularity(Integer version);

    SignInInvite queryData(Map<String, Object> map);

    SignInLikeAmount queryLikeData(Map<String, Object> map);

    int updateLikeData(SignInLikeAmount record);

    int insertLikeData(SignInLikeAmount record);

    int updateByPrimaryKey(SignInInvite record);

    int insertInviteData(SignInInvite record);
}
