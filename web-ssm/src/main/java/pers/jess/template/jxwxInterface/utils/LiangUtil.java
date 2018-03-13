package pers.jess.template.jxwxInterface.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class LiangUtil {

    public static String DateToString(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyMMddHH");
        return sf.format(d);
    }

    public static int hour(Date d){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static Set<Integer> NumberRandom(Integer len, Integer max){
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        while (set.size() < len){
            set.add(random.nextInt(max));
        }
        return set;
    }

    public static int hourBetween(int hour){
        switch (hour){
            case 9:
                return 3;
            case 12:
                return 4;
            case 16:
                return 4;
            default : return 0;
        }
    }


    public static Date getTime(Date d,int count){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE,count);
        return cal.getTime();
    }

    public static String DTS(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyMMdd");
        return sf.format(d);
    }

    public static int getVersion(Date d){
        int hour = hour(d);
        if (hour < 10){
            return Integer.parseInt(DTS(d)+"09");
        }

        if (hour >9 && hour < 13){
            return Integer.parseInt(DTS(d)+"12");
        }

        if (hour > 12 && hour < 17){
            return Integer.parseInt(DTS(d)+"16");
        }

        if (hour >16 && hour < 21){
            return Integer.parseInt(DTS(d)+"20");
        }
        if (hour > 20){
            return Integer.parseInt(DTS(getTime(d,1))+"09");
        }

        return  Integer.parseInt(DateToString(d));
    }

    public static int getNext(Date d){
        int hour = hour(d);
        if (hour < 10){
            return Integer.parseInt(DTS(d)+"12");
        }

        if (hour >9 && hour < 13){
            return Integer.parseInt(DTS(d)+"16");
        }

        if (hour > 12 && hour < 17){
            return Integer.parseInt(DTS(d)+"20");
        }

        if (hour >16 && hour < 21){
            return Integer.parseInt(DTS(getTime(d,1)) + "09");
        }

        if (hour > 20){
            return Integer.parseInt(DTS(getTime(d,1)) + "12");
        }

        return  Integer.parseInt(DateToString(d));
    }


}
