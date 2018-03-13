package pers.jess.template.jxwxInterface.utils;

public class MPInvestigateUtil {

    public static Integer answerMapping(String answer){
       switch (answer){
           case "A" :
               return 0;
           case  "B" :
               return 1;
           case  "C" :
               return 2;
           case  "D" :
               return 3;
           case  "E" :
               return 4;
           case  "F" :
               return 5;
           default:
               return -1;
       }

    }
}
