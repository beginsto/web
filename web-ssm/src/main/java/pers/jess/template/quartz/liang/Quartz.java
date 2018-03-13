package pers.jess.template.quartz.liang;

import org.apache.log4j.Logger;
import pers.jess.template.jxwxInterface.model.LiangPeriod;
import pers.jess.template.jxwxInterface.model.LiangPhone;
import pers.jess.template.jxwxInterface.service.JxwxService;
import pers.jess.template.jxwxInterface.utils.LiangUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Quartz {

    private Logger log = Logger.getLogger(pers.jess.template.quartz.Quartz.class);


    @Resource
    private JxwxService jxwxService;

    /**
     * 每隔一个小时执行一次（如果当前时间 8 11 15 19）
     * 则执行
     */
    public void work() {

        log.info("定时任务开始...");

        Date now = new Date();
        int hour = LiangUtil.hour(now);

        if (hour == 8 || hour == 11 || hour == 15 || hour == 19){

            int version = Integer.parseInt(LiangUtil.DateToString(now)) + 1;

            List<LiangPhone> list  = jxwxService.list();
            Vector<LiangPhone> vector = new Vector<>();

            for (LiangPhone p : list){
                if (p.getVersion() == null || p.getVersion() != version){
                    vector.add(p);
                }
            }

            int temp = vector.size();

            Set<Integer> set = LiangUtil.NumberRandom(20, temp);
            int v = 0;
            if (hour == 8){
                v = Integer.parseInt(LiangUtil.DateToString(now)) + 4;
            }
            if (hour == 11 || hour == 15){
                v = Integer.parseInt(LiangUtil.DateToString(now)) + 5;
            }
            if (hour == 19){
                v = Integer.parseInt(LiangUtil.DateToString(LiangUtil.getTime(now, 1))) - 10;
            }
            for(Integer s : set){
                LiangPeriod p = new LiangPeriod();
                p.setMid(vector.get(s).getId());
                p.setVersion(v);
                jxwxService.insert(p);
            }
            log.info("定时任务结束...");
        }else{
            log.info("非指定时间，任务结束...");
        }


    }
}
