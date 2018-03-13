package pers.jess.template.caiyun.service;

import pers.jess.template.caiyun.model.CaiYunInfo;

import java.util.Map;

public interface CaiYunService {

    int insertData(CaiYunInfo record);

    CaiYunInfo queryByMobile(Map<String, Object> map);

    int getAmount(String platform);
}
