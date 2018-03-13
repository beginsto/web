package pers.jess.template.answer.dao.impl;

import org.springframework.stereotype.Repository;
import pers.jess.template.answer.dao.QuestionMapper;
import pers.jess.template.answer.model.Question;
import pers.jess.template.common.BaseDao.BaseDao;

import javax.annotation.Resource;

@Repository
public class QuestionDaoImpl implements QuestionMapper {
    private static final String NAMESPACE_ANSWER = "pers.jess.template.answer.mapper.QuestionMapper.";

    @Resource
    private BaseDao baseDao;

    @Override
    public Question selectByPrimaryKey(Integer id){
        return this.baseDao.selectOne(NAMESPACE_ANSWER + "selectByPrimaryKey",id);
    }
}
