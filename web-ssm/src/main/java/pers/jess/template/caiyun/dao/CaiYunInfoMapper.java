package pers.jess.template.caiyun.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pers.jess.template.caiyun.model.CaiYunInfo;

import java.util.Map;

@Repository
public interface CaiYunInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaiYunInfo record);

    int insertSelective(CaiYunInfo record);

    CaiYunInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaiYunInfo record);

    int updateByPrimaryKey(CaiYunInfo record);

    CaiYunInfo queryByMobile(Map<String, Object> map);

    int getAmount(String platform);
}