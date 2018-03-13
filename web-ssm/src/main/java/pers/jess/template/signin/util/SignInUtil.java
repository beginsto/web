package pers.jess.template.signin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignInUtil {

    //奖励等级
    public static final int[] award = {10,110,150};

    public static final int[] region={21,42,63};

    /**
     *
     * @param d
     * @return
     */
    public static String DateToShortString(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public static String DateToLongString(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }


    public static String DateToStringSC(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("MM月dd日 HH:mm");
        return sf.format(d);
    }

    public static Date StringToDateSC(String d) throws ParseException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.parse(d);
    }

    public static int getHourByNow(Date d) throws ParseException{
        String d1 = DateToLongString(d).substring(0,16);
        String d2 = DateToLongString(getTime(d,1)).substring(0,10) + " 05:00";
        long t1 = StringToDateSC(d1).getTime();
        long t2 = StringToDateSC(d2).getTime();
        return  (int) ((t2 - t1)/(1000 * 60 * 60));

    }

    public static String DateToString(Date d){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(d);
    }

    public static Date StringToDate(String d) throws ParseException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.parse(d);
    }

    /**
     *
     * @param d
     * @return
     */
    public  static Date getYesterday(Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE,-1);
        return cal.getTime();
    }


    /**
     * 用户签到领取奖励
     * @param count
     * @return
     */
    public static int getAward(int count,int type){
        if(count <=21 )
            return count*award[0] / type;
        else if(count > 21 && count <= 42 )
            return award[1] / type;
        else
            return award[2] / type;
    }

    /**
     * 本期结束时间
     * @param d
     * @return
     */
    public static Date getEndTime(Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE,62);
        return cal.getTime();
    }

    public static Date getTime(Date d,int count){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE,count);
        return cal.getTime();
    }

    /**
     *
     * @param d
     * @return
     */
    public static String getIssue(Date d){
        return DateToString(d) + "/" + DateToString(getEndTime(d));
    }

    /**
     *
     * @param d
     * @return
     */
    public static Integer getHour(Date d){
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     *
     * @param d1
     * @param d2
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date d1,Date d2) throws ParseException{
        d1 = StringToDate(DateToShortString(d1));
        d2 = StringToDate(DateToShortString(d2));
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);
        long t1 = cal.getTimeInMillis();
        cal.setTime(d2);
        long t2 = cal.getTimeInMillis();
        return Integer.parseInt(String.valueOf((t2 - t1) / 86400000L));

    }

    /**
     *
     * @param count
     * @param d
     * @return
     */
    public static String getRegionStartTime(int count,Date d){
        if(count>21 && count <=42)
            return DateToShortString(getTime(d,21));
        else if(count>42 && count <=63)
            return DateToShortString(getTime(d,42));
        else
            return DateToShortString(d);

    }



    public static void main(String[] args){
        //System.out.println(DateToShortString(new Date()));
        //System.out.println(DateToLongString(new Date()));
        //System.out.println(DateToLongString(getYesterday(new Date())));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        //System.out.println(getHour(new Date()));
        try {
            System.out.println(getHourByNow(new Date()));
            System.out.println(DateToLongString(getEndTime(sf.parse("2017-09-20"))));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-11-7")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-11-8")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-11-15")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-11-22")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-11-29")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-12-6")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-12-13")));
            System.out.println(daysBetween(StringToDate("2017-10-17"),StringToDate("2017-12-18")));
        }catch (ParseException e){
            e.printStackTrace();
        }

    }
}
