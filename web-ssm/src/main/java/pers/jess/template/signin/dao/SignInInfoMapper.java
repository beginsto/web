package pers.jess.template.signin.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.signin.model.SignInInfo;

import java.util.Map;

@Component
public interface SignInInfoMapper {

    int insert(SignInInfo record);

    SignInInfo queryByPrimaryKey(Integer userid);

    int updateByPrimaryKey(SignInInfo record);

    SignInInfo queryByParams(Map<String, Object> map);
}