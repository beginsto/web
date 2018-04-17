package pers.jess.template.jxwxInterface.service;

import pers.jess.template.jxwxInterface.model.*;

import java.util.List;
import java.util.Map;

public interface JxwxService {



    int insert(MPInvestigate record);

    MPInvestigate queryByPhone(String phone);

    // 春节7天红包
    List<SpringFestivalAward> listAwards(Integer period);

    int updateAward(SpringFestivalAward award);

    int insert(SpringFestivalInfo record);

    int queryAmount(Integer period);

    List<SpringFestivalInfo> listInfo(String phone);

    int insert(BroadbandFault record);

    ///
    // 靓号抢购
    ///
    int insert(LiangPhone record);

    List<LiangPeriod> listByVersion(Integer version);

    List<LiangPhone> list();

    int insert(LiangPeriod record);

    int insert(LiangInfo record);

    int updateSetUsed(Integer id);

    LiangPhone queryById(Map<String, Integer> param);

    LiangInfo queryByIdCard(String idCardNo);

    List<LiangInfo> queryLiangByPhone(String phone);


    /////
    // 答题享好礼
    /////
    int insertQA(QuestionAnswerInfo record);

    List<QuestionAnswerInfo> queryQAByParam(Map<String,  Object> param);

    // 活动期间所有答题记录
    List<QuestionAnswerInfo> queryQAByPhone(String phone);

    QuestionAnswer queryQAById(Integer id);

    int queryQACount();

}
