import pers.jess.template.answer.model.UserWhite;
import pers.jess.template.answer.service.AnswerService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class TestAnswerWhite {
    @Resource
    private static AnswerService answerService;



    public static void main(String[] args){
       /* List<UserWhite> list = answerService.queryAllUserWhite();
        for(UserWhite l : list){
            System.out.println(l.getUser().getId());
        }*/
       System.out.println(new Date().getTime());
    }
}
