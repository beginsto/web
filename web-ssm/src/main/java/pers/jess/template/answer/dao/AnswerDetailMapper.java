package pers.jess.template.answer.dao;

import pers.jess.template.answer.model.AnswerDetail;

import java.util.Date;
import java.util.HashMap;

public interface AnswerDetailMapper {

    int insertSelective(AnswerDetail record);

    int queryAnswerDetailCountByDate(HashMap<String, Object> params);

}