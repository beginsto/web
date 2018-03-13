package pers.jess.template.answer.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.answer.dao.AnswerDetailMapper;
import pers.jess.template.answer.model.AnswerDetail;
import pers.jess.template.common.BaseDao.BaseDao;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@Repository
public class AnswerDetailDaoImpl implements AnswerDetailMapper{

    private static final String NAMESPACE_ANSWER = "pers.jess.template.answer.mapper.AnswerDetailMapper.";

    @Resource
    private BaseDao baseDao;

    @Override
    public int insertSelective(AnswerDetail record){
        return this.baseDao.insert(NAMESPACE_ANSWER + "insertSelective",record);
    }

    @Override
    public int queryAnswerDetailCountByDate(HashMap<String, Object> params){
        return this.baseDao.selectOne(NAMESPACE_ANSWER + "", params);
    }
}
