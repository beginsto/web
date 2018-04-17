package pers.jess.template.jxwxInterface.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.jxwxInterface.dao.*;
import pers.jess.template.jxwxInterface.model.*;
import pers.jess.template.jxwxInterface.service.JxwxService;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Service
public class JxwxServiceImpl implements JxwxService {

    @Autowired
    private MPInvestigateMapper mpInvestigateMapper;

    @Autowired
    private SpringFestivalAwardMapper springFestivalAwardMapper;

    @Autowired
    private SpringFestivalInfoMapper springFestivalInfoMapper;

    @Autowired
    private BroadbandFaultMapper broadbandFaultMapper;

    @Autowired
    private LiangPhoneMapper liangPhoneMapper;

    @Autowired
    private LiangPeriodMapper liangPeriodMapper;

    @Autowired
    private LiangInfoMapper liangInfoMapper;


    @Override
    public int insert(MPInvestigate record){
        return mpInvestigateMapper.insert(record);
    }

    @Override
    public MPInvestigate queryByPhone(String phone){
        return mpInvestigateMapper.queryByPhone(phone);
    }

    //////////////
    // 春节7天红包
    /////////////
    @Override
    public List<SpringFestivalAward> listAwards(Integer period){
        return springFestivalAwardMapper.listAwards(period);
    }

    @Override
    public int updateAward(SpringFestivalAward award){
        return springFestivalAwardMapper.updateAward(award);
    }

    @Override
    public int insert(SpringFestivalInfo record){
        return springFestivalInfoMapper.insert(record);
    }

    @Override
    public int queryAmount(Integer period){
        return springFestivalInfoMapper.queryAmount(period);
    }

    @Override
    public List<SpringFestivalInfo> listInfo(String phone){
        return springFestivalInfoMapper.listInfo(phone);
    }

    //////////////
    // 宽带异常登记
    //////////////

    @Override
    public int insert(BroadbandFault record){
        return broadbandFaultMapper.insert(record);
    }

    ///
    // 靓号抢购
    ///
    @Override
    public int insert(LiangPhone record){
        return liangPhoneMapper.insert(record);
    }

    @Override
    public List<LiangPeriod> listByVersion(Integer version){
        return liangPeriodMapper.listByVersion(version);
    }

    @Override
    public List<LiangPhone> list(){
        return liangPhoneMapper.list();
    }

    @Override
    public int insert(LiangPeriod record){
        return liangPeriodMapper.insert(record);
    }

    @Override
    public int insert(LiangInfo record){
        return liangInfoMapper.insert(record);
    }

    @Override
    public int updateSetUsed(Integer id){
        return liangPhoneMapper.updateSetUsed(id);
    }

    @Override
    public  LiangPhone queryById(Map<String, Integer> param){
        return liangPhoneMapper.queryById(param);
    }

    @Override
    public LiangInfo queryByIdCard(String idCardNo){
        return liangInfoMapper.queryByIdCard(idCardNo);
    }

    @Override
    public List<LiangInfo> queryLiangByPhone(String phone){
        return liangInfoMapper.queryByPhone(phone);
    }

    /////
    // 答题享好礼
    /////

    @Autowired
    private QuestionAnswerInfoMapper questionAnswerInfoMapper;

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;

    @Override
    public int insertQA(QuestionAnswerInfo record){
        return questionAnswerInfoMapper.insert(record);
    }

    @Override
    public List<QuestionAnswerInfo> queryQAByParam(Map<String,  Object> param){
        return questionAnswerInfoMapper.queryByParam(param);
    }

    // 活动期间所有答题记录
    @Override
    public List<QuestionAnswerInfo> queryQAByPhone(String phone){
        return questionAnswerInfoMapper.queryByPhone(phone);
    }

    @Override
    public QuestionAnswer queryQAById(Integer id){
        return questionAnswerMapper.queryById(id);
    }

    @Override
    public int queryQACount(){
        return questionAnswerMapper.queryCount();
    }



}
