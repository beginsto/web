package pers.jess.template.answer.dao;

import pers.jess.template.answer.model.Question;

public interface QuestionMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(Question record);

    //int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    //int updateByPrimaryKeySelective(Question record);

    //int updateByPrimaryKey(Question record);
}