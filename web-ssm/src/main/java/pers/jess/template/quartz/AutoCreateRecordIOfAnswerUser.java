package pers.jess.template.quartz;

import org.apache.log4j.Logger;
import pers.jess.template.answer.model.UserInfo;
import pers.jess.template.answer.model.UserWhite;
import pers.jess.template.answer.service.AnswerService;
import pers.jess.template.answer.utils.AnswerUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AutoCreateRecordIOfAnswerUser implements Runnable{

    private Logger log = Logger.getLogger(AutoCreateRecordIOfAnswerUser.class);

    @Resource
    protected AnswerService answerService;

    protected int count = 0;

    protected  List<UserWhite> white;

    public AutoCreateRecordIOfAnswerUser(){ }

    public AutoCreateRecordIOfAnswerUser(int count,List<UserWhite> white, AnswerService answerService){
        this.count = count;
        this.white = white;
        this.answerService = answerService;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for(UserWhite w : white){
                if (w.getId() == null)
                    continue;
                UserInfo userInfo = new UserInfo();
                userInfo.setUserid(w.getUser().getId());
                userInfo.setIssue(AnswerUtils.getIssue());
                userInfo.setAnsZh(rand.nextInt(20) + 110);
                userInfo.setAnsRigZh(rand.nextInt(10) + 100);
                userInfo.setNextques(AnswerUtils.getQuestion("zh"));
                userInfo.setLasttime(new Date());
                userInfo.setCreatetime(new Date());
                answerService.insertUserInfo(userInfo);
                TimeUnit.SECONDS.sleep(5);
                log.info("用户：" + w.getPhone() + "记录插入完成...");
            }
            log.info("微信答题－>第"+AnswerUtils.getIssue()+"期白名单记录自动生成结束...");
        }catch (InterruptedException e){
            log.error("Interrupted,context:" + e);
        }catch (Exception e){
            log.error("微信答题－>白名单记录生成异常，context：" + e);
        }

    }
}
