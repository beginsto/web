package pers.jess.template.mro.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.mro.model.MroContent;

import java.util.List;

@Repository
public interface MroContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MroContent record);

    int insertSelective(MroContent record);

    MroContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MroContent record);

    int updateByPrimaryKeyWithBLOBs(MroContent record);

    int updateByPrimaryKey(MroContent record);

    List<MroContent> query();
}