package pers.jess.template.answer.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.answer.model.*;
import pers.jess.template.answer.service.AnswerService;
import pers.jess.template.answer.utils.AnswerUtils;
import pers.jess.template.common.httpclient.HttpClient;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "answer")
public class AnswerController {

    private static final String userInfoUrl = "http://card.1860.cn/public/zjydwx/service/getWxUserInfo";

    private HttpClient httpClient = new HttpClient();

    @Resource
    private AnswerService answerService;

    private Logger logger = Logger.getLogger(AnswerController.class);

    @RequestMapping (value = "index")
    public String toIndex(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        HashMap<String, Object> params = new HashMap<>();

        //用户绑定手机号码
        String mobile="";
        //用户本期答题总数
        int count = 0;
        //用户本期答题正确数
        int countRig = 0;
        try {
            params.put("openId",openid);
            //根据openid获取用户绑定，头像等信息
            String result = httpClient.doPost(userInfoUrl,params);
            //System.out.println(result);
            JSONObject json = JSONObject.parseObject(result);
            if (json != null && String.valueOf(json.get("resultCode")).equals("200")){
                JSONObject context = JSONObject.parseObject(String.valueOf(json.get("resultContent")));
                mobile = String.valueOf(context.get("phone"));
                if (mobile.length() == 11) {//绑定手机号码用户
                    //根据手机号码查询用户
                    User user = answerService.queryUserByPhone(mobile);
                    //如果存在说明是指定用户
                    if (user != null ){
                        params.put("userid",user.getId());
                        params.put("issue",AnswerUtils.getIssue());
                        UserInfo userInfo = answerService.queryUserInfoByParams(params);
                        model.addAttribute("userId",user.getId());
                        if (userInfo == null) {//本期还未参与过
                            UserInfo record = new UserInfo();
                            record.setUserid(user.getId());
                            record.setIssue(AnswerUtils.getIssue());
                            record.setCreatetime(new Date());
                            record.setNextques(1);
                            answerService.insertUserInfo(record);
                        }else {
                            count = userInfo.getAnsBus() + userInfo.getAnsZh() + userInfo.getAnsGov();
                            countRig = userInfo.getAnsRigGov() + userInfo.getAnsRigZh() + userInfo.getAnsRigBus();
                        }
                    }else
                        model.addAttribute("data","Non-target");

                }
            }
            model.addAttribute("openid",openid);
            model.addAttribute("phone",mobile);
            model.addAttribute("count",count);
            model.addAttribute("countRig",countRig);

        }catch (Exception e){
            logger.error("微信答题－>首页异常...context：" + e);
            e.printStackTrace();
        }
        return "answer/index";
    }

    @RequestMapping(value = "/answer")
    public String toAnswer(HttpServletRequest req, Model model) {
        String userId = req.getParameter("userId")==null?"":req.getParameter("userId").trim();
        String platForm = req.getParameter("platForm")==null?"":req.getParameter("platForm").trim();
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            if (userId.equals("") || platForm.equals("")) {
                logger.info("微信答题－>答题页面异常：参数不全...");
            }

            //查询用户上一次未答题目
            hashMap.put("userid",userId);
            hashMap.put("issue", AnswerUtils.getIssue());
            //hashMap.put("businesstype",platForm);
            UserInfo userInfo = answerService.queryUserInfoByParams(hashMap);
            int quesId = userInfo.getNextques();
            //验证题目是否和用户本次所选的类型一致，如果不一致重新随意该类型题目，并存入t_answer_userinfo
            if (AnswerUtils.isChangeQuestion(quesId,platForm)){
                quesId = AnswerUtils.getQuestion(platForm);
                userInfo.setNextques(quesId);
                answerService.updateUserInfoById(userInfo);
            }
            //根据题目id查询题目信息
            Question question = answerService.queryById(quesId);
            //System.out.println(question.getQuestion());
            model.addAttribute("ques",question);
            model.addAttribute("userid",userId);
        }catch (Exception e){
            logger.error("微信答题－>答题页面异常，context：" + e);
            e.printStackTrace();
        }
        return "answer/answer";
    }

    @RequestMapping(value = "confirm")
    @ResponseBody
    public Object confirm(AnswerDetail answerDetail,HttpServletRequest req){
        HashMap<String, Object> result = new HashMap<>();
        HashMap<String, Object> hashMap = new HashMap<>();
        String businessType = req.getParameter("businesstype")==null?"":req.getParameter("businesstype").trim();
        try {
            Date now = new Date();
            hashMap.put("answertime",now);
            hashMap.put("userid",answerDetail.getUserid());
            if(answerService.queryAnswerDetailCountByDate(hashMap) > 100){
                result.put("code",303);
            }else{
                answerDetail.setAnswertime(now);
                answerService.insertAnswerDetail(answerDetail);
                //查询用户上一次未答题目
                hashMap.put("userid",answerDetail.getUserid());
                hashMap.put("issue", AnswerUtils.getIssue());
                UserInfo userInfo = answerService.queryUserInfoByParams(hashMap);
                if (businessType.equals("zh")){
                    userInfo.setAnsZh(userInfo.getAnsZh() + 1);
                    if (answerDetail.getAnswer().equals(answerDetail.getAnswerright()))
                        userInfo.setAnsRigZh(userInfo.getAnsRigZh() + 1);
                }else if (businessType.equals("bus")){
                    userInfo.setAnsBus(userInfo.getAnsBus() + 1);
                    if (answerDetail.getAnswer().equals(answerDetail.getAnswerright()))
                        userInfo.setAnsRigBus(userInfo.getAnsRigBus() + 1);
                }else{
                    userInfo.setAnsGov(userInfo.getAnsGov() + 1);
                    if (answerDetail.getAnswer().equals(answerDetail.getAnswerright()))
                        userInfo.setAnsRigGov(userInfo.getAnsRigGov() + 1);
                }
                //随机下一题
                userInfo.setNextques(AnswerUtils.getQuestion(businessType));
                userInfo.setLasttime(now);
                //更新用户信息
                int temp = answerService.updateUserInfoById(userInfo);
                logger.info("用户："+userInfo.getUserid() +
                        "-->答题数量："+(userInfo.getAnsZh()+userInfo.getAnsBus()+userInfo.getAnsGov()) +
                        "-->正确数："+(userInfo.getAnsRigBus()+userInfo.getAnsRigGov()+userInfo.getAnsRigZh()));
                if (temp > 0){
                    if (answerDetail.getAnswer().equals(answerDetail.getAnswerright()))
                        result.put("code",200);
                    else{
                        result.put("code",300);
                        result.put("msg","您答错了，本题正确答案是"+answerDetail.getAnswerright()+"（点击“确定”进入下一题）");
                    }
                }else{
                    result.put("code",300);
                    result.put("msg","服务异常...请稍后再试");
                }

            }
        }catch (Exception e){
            logger.error("微信答题－>答题提交异常，context：" + e);
            e.printStackTrace();
            result.put("code",500);
        }
        return result;
    }
}
