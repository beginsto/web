package pers.jess.template.mro.service;

import pers.jess.template.mro.model.MroAmount;
import pers.jess.template.mro.model.MroContent;
import pers.jess.template.mro.model.MroDetail;

import java.util.List;
import java.util.Map;

public interface MroService {

    List<MroContent> query();

    MroDetail queryByOpenidAndIssue(Map<String, Object> map);

    MroAmount queryByPid(Integer pid);

    int insert(MroAmount record);

    int updateByPrimaryKey(MroAmount record);

    int insert(MroDetail record);

    int updateByPrimaryKey(MroDetail record);

    MroContent selectByPrimaryKey(Integer id);

    List<MroDetail> queryList(Map<String, Object> map);
}
