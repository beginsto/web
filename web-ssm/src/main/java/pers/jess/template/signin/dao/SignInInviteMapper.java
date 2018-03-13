package pers.jess.template.signin.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.signin.model.SignInInvite;

import java.util.List;
import java.util.Map;

@Component
public interface SignInInviteMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(SignInInvite record);

    int insertSelective(SignInInvite record);

    SignInInvite selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(SignInInvite record);

    int updateByPrimaryKey(SignInInvite record);

    List<SignInInvite> queryRankFriendList(Map<String, Object> map);

    SignInInvite queryData(Map<String, Object> map);
}