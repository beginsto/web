package pers.jess.template.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.jess.template.battle.model.Battle;
import pers.jess.template.common.httpclient.HttpClient;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

    public static final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    public static final String[] constellationArr = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    public static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};

    private static final String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";

    static Random rand = new Random();


    /**
     * 根据日期获取生肖
     * @param date
     * @return
     */
    public static String getZodica(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 根据日期获取星座
     * @param date
     * @return
     */
    public static String getConstellation(Date date) {
        if (date == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    /**
     *  获取用户星座
     * @param mobile
     * @return
     */
    public static String getConstellation(String mobile){
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";
        Map param = new HashMap<>();
        param.put("tele",mobile);
        param.put("type","1");
        param.put("token",base64(base64(base64(genTk()))));

        HttpClient httpClient = new HttpClient();
        try {
            String result = httpClient.doPost(url,param);
            if (isBlank(result)){
                return null;
            }else{
                result = result.replaceAll("jsonpCallback\\(\\(", "");
                result = result.replaceAll("\\)", "");
             //   System.out.println("获取用户星座:"+result);
                if (result.contains("tcName")) {
                    Map json = JSON.parseObject(result);
                    return json.get("tcName").toString().equals("天平座")?"天秤座":json.get("tcName").toString();
                }else
                    return "";
            }
        } catch (URISyntaxException e) {
            System.out.println("获取用户星座异常..");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("获取用户星座异常..");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 兑换码领流量
     * @param mobile
     * @param code
     * @return
     */
    public static String pushData(String mobile,String code){
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";
        Map param = new HashMap<>();
        param.put("tele",mobile);
        param.put("type","2");
        param.put("token",base64(base64(base64(genTk()))));
        param.put("code",code);

        HttpClient httpClient = new HttpClient();
        try {
            String result = httpClient.doPost(url,param);
            if (isBlank(result)){
                return null;
            }else{
                result = result.replaceAll("jsonpCallback\\(\\(", "");
                result = result.replaceAll("\\)", "");
                System.out.println("接口返回数据:"+result);
                if (result.contains("Result")) {
                    Map json = JSON.parseObject(result);
                    return json.get("Result").toString();
                }else
                    return "";
            }
        } catch (URISyntaxException e) {
            System.out.println("获取用户星座异常..");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("获取用户星座异常..");
            e.printStackTrace();
        }
        return "";
    }


    public static String getData(String type) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";
        Map param = new HashMap<>();
        param.put("type",type);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("report_date",getReportDate());

        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }
    /***test***/
    public static String getDataTest(String type) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";
        Map param = new HashMap<>();
        param.put("type",type);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("report_date",getReportDate());

        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            result = result.replaceAll("jsonpCallback\\(\\(", "");
            result = result.replaceAll("\\)", "");
            List<Battle> bs = JSONArray.parseArray(result,Battle.class);
            bs.sort(Comparator.comparing(Battle::getRkTeam));
            for (int i = 0;i < bs.size();i++){
                String teamName = bs.get(i).getTeamName();
                String teamId = bs.get(i).getTeamId();
                String r = "队伍名称：" + teamName + ",所属队伍：" + teamId;
                boolean flag = false;
                for(Battle b : bs){
                    if (b.getTeamName().equals(teamName) ){
                        if (!b.getTeamId().equals(teamId)) {
                            r += "," + b.getTeamId();
                            flag = true;
                        }
                    }
                }
                if (flag)
                    System.out.println(r);
            }
            return result;
        }
    }


    public static String isTarget(String mobile) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?";
        Map param = new HashMap<>();
        param.put("type",5);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    public static String pushData(String mobile, String userid, String disMth,String remark) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?";
        Map param = new HashMap<>();
        param.put("type",6);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        param.put("id",userid);
        param.put("disMth",disMth);
        param.put("ktRemark",remark);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    /***
     * 根据手机号码查询虚拟网
     * @param mobile
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String queryXnw(String mobile, Integer type)  throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",type);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    public static String queryShortMobile(String xnm,Integer type) throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",type);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("shortNum",xnm);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    /***
     * 目标用户
     * @param mobile
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String queryXnwFliter(String mobile,Integer type) throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",type);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    public static String pushData(String mobile, String userid,String opType) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface?";
        Map param = new HashMap<>();
        param.put("type",8);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        param.put("id",userid);
        param.put("opType",opType);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }




    public static String sendMsg(String mobile) throws URISyntaxException, IOException{
        String url = "http://jxdxfz.zj.chinamobile.com/interface/DxZhQryHandler/qryInterface";
        Map param = new HashMap<>();
        param.put("type",0);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("teleNum",mobile);
        param.put("sendType",1);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }

    public static String query4GCm(String mobile) throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",11);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }


    public static String getTMData(String mobile) throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",14);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }


    public static String push4GCm(String mobile,String userid) throws URISyntaxException, IOException{
        Map param = new HashMap<>();
        param.put("type",12);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("tele",mobile);
        param.put("id",userid);
        HttpClient httpClient = new HttpClient();

        String result = httpClient.doPost(url,param);
        if (isBlank(result)){
            return null;
        }else{
            return replaceRes(result);
        }
    }


    public static String querykdts(String opTele, String regTele){
        Map param = new HashMap<>();
        param.put("type",34);
        param.put("token",base64(base64(base64(genTk()))));
        param.put("opTele",opTele);
        param.put("regTele",regTele);

        HttpClient httpClient = new HttpClient();
        try {
            String result= httpClient.doPost(url,param);
            if (isBlank(result)){
                return null;
            }else{
                return replaceRes(result);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }


    private static String genTk() {
        String d1 = (new SimpleDateFormat("MMyyyydd")).format(new Date());
        String d2 = (new SimpleDateFormat("ssHHmm")).format(new Date());
        int num = rand.nextInt(199);
        //System.out.println("接口凭证："+ d1 + " " + num + " " + d2);
        return d1 + num + d2;
    }

    private static String getReportDate(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        Calendar cal =Calendar.getInstance();
        cal.setTime(now);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 14){
            cal.add(Calendar.DATE, -1);
            return sf.format(cal.getTime());

        }else{
            cal.add(Calendar.DATE, -2);
            return sf.format(cal.getTime());
        }
    }


    private static String replaceRes(String r){
        r = r.replaceAll("jsonpCallback", "");
        r = r.replaceAll("\\(", "");
        r = r.replaceAll("\\)", "");
        return r;
    }

    private static boolean isBlank(String msg) {
        return msg == null || "".equals(msg.trim());
    }

    private static String base64(String src) {
        if (src == null) {
            return null;
        } else {
            BASE64Encoder encoder = new BASE64Encoder();
            String val = encoder.encode(src.getBytes());
            encoder = null;
            return val;
        }
    }

    public static void main(String[] args) throws Exception{
        //System.out.println(getConstellation("13666763590"));
        //System.out.println(pushData("13867325678","H1GhSf0Tvw"));
        //System.out.println(pushData("18858370914","wX0qXLWSwQ"));
        //System.out.println(pushData("18858370914","SpEzAsyCZ9"));
       // System.out.println(isTarget("18857395086"));
       // System.out.println(pushData("15905836605","7317368612","3个月", "pptv"));
     //   String shortMobile =queryShortMobile("666000");
       // System.out.println(shortMobile);
        //JSONObject j = JSON.parseObject(shortMobile);
        //String xnm = queryXnw(j.getString("teleNum"));
        //System.out.println(xnm);

      //  System.out.println(sendMsg("15905836605"));
       // System.out.println(getData("3"));
        //System.out.println(getData("4"));
        //SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println(sf.format(new Date(1512980357000l)));
       // System.out.println(sf.format(new Date(1512980580000l)));
      //  System.out.println(query4GCm("13666763590"));
      //  System.out.println(queryXnwFliter("13666728953",23));
      //  System.out.println(queryXnw("13967300777"));
       // System.out.println(queryShortMobile("660777"));
       // System.out.println(queryShortMobile("360777"));
        //System.out.println(getTMData("15905836605"));
    //    System.out.println(sendMsg("15905836605"));
        System.out.println(getConstellation("15858364036"));
    }
}
