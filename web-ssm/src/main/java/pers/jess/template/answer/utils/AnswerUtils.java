package pers.jess.template.answer.utils;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AnswerUtils {

    /**
     * 每月20日至下月19日为一期
     * @return 返回当前是第几期
     */
    public static String getIssue(){
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        int date = c.get(Calendar.DATE);
        int year = c.get(Calendar.YEAR);
        /*int month = 1;
        int date = 20;
        int year = 2017;*/
        if (date < 20)
            month--;
        if (month == 0){
            year--;
            month = 12;
        }

        return ""+year+"-"+month;
    }

    /**
     * 随意题目
     * @param businessType
     * @return 题目ID
     */
    public static Integer getQuestion(String businessType){
        Random random = new Random();
        if (businessType.equals("zh"))
            return random.nextInt(278) + 2001 ;
        else if (businessType.equals("bus"))
            return random.nextInt(292) + 1 ;
        else
            return random.nextInt(227) + 4001 ;

    }

    /**
     *
     * @param quesId
     * @param businessType
     * @return
     */
    public static boolean isChangeQuestion(Integer quesId, String businessType){
        if (businessType.equals("zh")){
            if (quesId >= 2001 && quesId < 4001)
                return false;
            else
                return true;
        }else if(businessType.equals("bus")){
            if (quesId < 2001)
                return false;
            else
                return true;
        }else{
            if (quesId >= 4001)
                return false;
            else
                return true;

        }
    }

    public static String toShortDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }


    public static void main(String[] args){
        AnswerUtils answerUtils = new AnswerUtils();
        System.out.println(answerUtils.getIssue());
    }
}
