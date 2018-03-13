package pers.jess.template.jxwxInterface.service;

import pers.jess.template.jxwxInterface.model.*;

import java.util.List;

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

    LiangPhone queryById(Integer id);

    LiangInfo queryByIdCard(String idCardNo);

    LiangInfo queryLiangByPhone(String phone);

}
