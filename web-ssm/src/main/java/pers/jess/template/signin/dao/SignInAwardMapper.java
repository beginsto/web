package pers.jess.template.signin.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.signin.model.SignInAward;

import java.util.List;
import java.util.Map;

@Component
public interface SignInAwardMapper {

    int insert(SignInAward record);

    SignInAward selectByParams(Map<String, Object> map);

    List<SignInAward> querySignList(Integer userid);

    List<SignInAward> querySignListByVersion(Integer version);

    List<SignInAward> queryRankWorld(Integer verison);
}