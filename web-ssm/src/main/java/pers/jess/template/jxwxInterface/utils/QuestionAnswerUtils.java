package pers.jess.template.jxwxInterface.utils;

public class QuestionAnswerUtils {

    /**
     * 字符出现次数
     * @param str
     * @param find
     * @return
     * */
    public static int appearNumber(String str, String find){
        int count = 0;
        int index = 0;
        while ((index = str.indexOf(find,index)) != -1){
            index = index + find.length();
            count++;
        }
        return count;
    }

}
