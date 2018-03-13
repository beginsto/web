package pers.jess.template.answer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.answer.dao.*;
import pers.jess.template.answer.model.*;
import pers.jess.template.answer.service.AnswerService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerDetailMapper answerDetailMapper;

    @Autowired
    private UserWhiteMapper userWhiteMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo queryUserInfoByParams(HashMap<String, Object> params) {
        return userInfoMapper.queryUserInfoByParams(params);
    }

    @Override
    public int updateUserInfoById(UserInfo record){
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Question queryById(Integer id){
        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertAnswerDetail(AnswerDetail record){
        return answerDetailMapper.insertSelective(record);
    }

    @Override
    public int queryAnswerDetailCountByDate(HashMap<String, Object> params){
        return answerDetailMapper.queryAnswerDetailCountByDate(params);
    }

    @Override
    public List<UserWhite> queryAllUserWhite(){
        return userWhiteMapper.queryAllUserWhite();
    }

    @Override
    public int insertUserInfo(UserInfo record){
        return userInfoMapper.insertUserInfo(record);
    }

    @Override
    public User queryUserByPhone(String  phone){
        return userMapper.queryUserByPhone(phone);
    }
}
