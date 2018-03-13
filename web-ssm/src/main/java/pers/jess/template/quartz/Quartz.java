package pers.jess.template.quartz;

import org.apache.log4j.Logger;
import pers.jess.template.answer.model.UserWhite;

import java.util.List;
import java.util.concurrent.*;

public class Quartz extends AutoCreateRecordIOfAnswerUser{

    private Logger log = Logger.getLogger(Quartz.class);


    private ExecutorService execu = Executors.newCachedThreadPool();

    public void work() {
        log.info("微信答题－>白名单记录自动生成开始...");

        //查询所有白名单用户
        List<UserWhite> white = answerService.queryAllUserWhite();
        execu.execute(new AutoCreateRecordIOfAnswerUser(white.size(),white,answerService));
        execu.shutdown();
    }

}
