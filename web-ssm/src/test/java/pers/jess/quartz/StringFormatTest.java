package pers.jess.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringFormatTest {

    public static void main(String[] args){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        try {
            System.out.println(sf.parse("2004/8/26"));
            System.out.println(sf.parse("1999/9/1"));
            System.out.println(sf.parse("2006/10/24 14:34"));


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
