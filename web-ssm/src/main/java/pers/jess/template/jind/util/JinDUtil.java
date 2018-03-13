package pers.jess.template.jind.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JinDUtil {


    /**
     *
     * @param d
     * @return
     */
    public static String DateToShortString(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static Date StringToDate(String d) throws ParseException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(d);
    }

    /**
     *  获取 日期相隔天数
     * @param sd 开始时间
     * @param d2 当前时间
     * @return
     * @throws ParseException
     */
    public static int daysBetween(String sd, Date d2) throws ParseException{
        Date d1 = StringToDate(sd);
        d2 = StringToDate(DateToShortString(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long t2 = cal.getTimeInMillis();
        return Integer.parseInt(String.valueOf((t2 - t1) / 86400000L));

    }

    public static int hour(Date d){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public static int minute(Date d){
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return c.get(Calendar.MINUTE);
    }
}
