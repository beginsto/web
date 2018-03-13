package pers.jess.template.signin.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.signin.model.SignInUser;

import java.util.List;

@Component
public interface SignInUserMapper {

    int insert(SignInUser record);

    SignInUser queryByOpenid(String  openid);

    int updateByPrimaryKey(SignInUser record);

    List<SignInUser> getParticipator();

    int queryJoinCount();
}