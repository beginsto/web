package pers.jess.template.football.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.football.model.Football;

@Component
public interface FootballMapper {
    /*int deleteByPrimaryKey(Integer id);

    int insert(Football record);

    int insertSelective(Football record);

    Football selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Football record);

    int updateByPrimaryKey(Football record);*/

    int insertData(Football record);

    int queryByMobile(String mobile);

    int queryCount();
}