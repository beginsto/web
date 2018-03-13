package pers.jess.template.mro.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.mro.model.MroAmount;

@Repository
public interface MroAmountMapper {
    int insert(MroAmount record);

    int insertSelective(MroAmount record);

    MroAmount queryByPid(Integer pid);

    int updateByPrimaryKey(MroAmount record);
}