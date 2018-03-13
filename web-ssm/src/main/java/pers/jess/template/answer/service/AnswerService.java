package pers.jess.template.answer.service;

import org.springframework.stereotype.Service;
import pers.jess.template.answer.model.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AnswerService {

    UserInfo queryUserInfoByParams(HashMap<String, Object> params);

    int updateUserInfoById(UserInfo record);

    Question queryById(Integer id);

    int insertAnswerDetail(AnswerDetail record);

    int queryAnswerDetailCountByDate(HashMap<String, Object> params);

    List<UserWhite> queryAllUserWhite();

    int insertUserInfo(UserInfo record);

    User queryUserByPhone(String  phone);
}
