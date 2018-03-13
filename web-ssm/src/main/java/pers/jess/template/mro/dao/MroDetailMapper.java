package pers.jess.template.mro.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.mro.model.MroDetail;

import java.util.List;
import java.util.Map;


@Repository
public interface MroDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MroDetail record);

    int insertSelective(MroDetail record);

    MroDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MroDetail record);

    int updateByPrimaryKey(MroDetail record);

    MroDetail queryByOpenidAndIssue(Map<String, Object> map);

    List<MroDetail> queryList(Map<String, Object> map);
}