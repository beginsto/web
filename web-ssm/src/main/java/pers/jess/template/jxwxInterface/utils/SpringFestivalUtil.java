package pers.jess.template.jxwxInterface.utils;

public class SpringFestivalUtil {
    public static Integer periodMapping(String code){
        switch (code){
            case "测试1" :
                return 20180212;
            case  "测试2" :
                return 20180213;
            case  "测试3" :
                return 20180214;
            case "咪咕爱看" :
                return 20180215;
            case  "不限量" :
                return 20180216;
            case  "和包" :
                return 20180217;
            case  "光宽带" :
                return 20180218;
            case  "任我看" :
                return 20180219;
            case  "亲情网" :
                return 20180220;
            case  "手机营业厅" :
                return 20180221;
            case "元宵测试" :
                return 20180301;
            case "不限量选移动":
                return 20180302;
            default:
                return -1;
        }

    }
}
