package pers.jess.template.battle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.battle.model.Battle;
import pers.jess.template.battle.model.BattleArea;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("battle")
public class BattleController extends BaseController{
    private JSONObject team_json = null;

    private  String[] team = {"一","二","三","四","五","六","七","八","九","十","十一","十二"};
    private int version=0;

    @RequestMapping("getData")
    @ResponseBody
    public Object getData() {
        try {


            String result = CommonUtil.getData("4");
            if (result == null || result.contains("msg"))
                return null;
            //if (team_json != null && version != 0 && version == getVersion() && getHour() >=14 ){
              //  return packResult(200,"",team_json.toJSONString());
            //}else{
                List<BattleArea> bs = JSONArray.parseArray(result,BattleArea.class);
                bs.sort(Comparator.comparing(BattleArea::getTeamId).thenComparing(BattleArea::getRk));

                for (BattleArea ba : bs){
                    ba.setAreaName(getAreaCode(ba.getAreaName()));
                    ba.setCenterName(getAreaCode(ba.getCenterName()));
                }

                JSONArray arr = new JSONArray();
                JSONObject json = new JSONObject();
                for(int i = 0; i < team.length; i++){
                    Iterator<BattleArea> it = bs.iterator();
                    while(it.hasNext()){
                        BattleArea b = it.next();
                      //  if (b.getRkTeam() > 3) {
                          //  it.remove();
                          //  continue;
                       // }
                        if(b.getTeamId().equals(team[i])){
                            arr.add(b);
                            it.remove();
                        }
                    }
                    json.put(team[i],arr);
                    arr = new JSONArray();

                }
                team_json = json;
                version = getVersion();
                return packResult(200,"",json.toJSONString());
           // }




        //    System.out.println(json.toJSONString());

        }catch (Exception e){
            e.printStackTrace();
            return packResult(300,"","[]");
        }

    }

    @RequestMapping("getTeamData")
    @ResponseBody
    public Object getTeamData(HttpServletRequest req) {
        try {
            String[] team = {"一","二","三","四","五","六","七","八","九","十","十一","十二"};
            String teamid = req.getParameter("teamid")==null?"1":req.getParameter("teamid");
            String result = CommonUtil.getData("3");
            if (result == null || result.contains("msg"))
                return null;

            List<Battle> list = JSONArray.parseArray(result,Battle.class);

            Iterator<Battle> it = list.iterator();
            while(it.hasNext()){
                Battle b = it.next();
                if (!b.getTeamId().equals(team[Integer.parseInt(teamid)-1]))
                    it.remove();
            }

            list.sort(Comparator.comparing(Battle::getRkTeam));

            return packResult(200,"",JSON.toJSONString(list));
        }catch (Exception e){
            e.printStackTrace();
            return packResult(300,"","[]");
        }

    }

    @RequestMapping("getAreaData")
    @ResponseBody
    public Object getAreaData(String areaCode) {
        try {
             String result = CommonUtil.getData("4");
                if (result == null || result.contains("msg"))
                return null;

            List<BattleArea> ba = JSONArray.parseArray(result,BattleArea.class);
            Iterator<BattleArea> it = ba.iterator();
            JSONArray arr = new JSONArray();
            while(it.hasNext()){
                BattleArea b = it.next();
                if (!b.getAreaName().equals(getAreaName(areaCode)))
                    it.remove();
            }

            ba.sort(Comparator.comparing(BattleArea::getCenterName).thenComparing(BattleArea::getRk));
            //System.out.println(JSON.toJSONString(ba));

            JSONObject json = new JSONObject();
            List<BattleArea> c = JSONArray.parseArray(result,BattleArea.class);
            List<String> b = getCenterName(c,areaCode);
            for (int i = 0; i < b.size(); i++){
                for(BattleArea a : ba){
                    if (a.getCenterName().equals(b.get(i))){
                        List<Battle> t = getTeamRK(a.getTeamId());
                        for (int j = 0; j <t.size();j++){
                            if(a.getTeamName().equals(t.get(j).getTeamName())) {
                                a.setRk(t.get(j).getRkTeam());
                                break;
                            }
                        }
                        arr.add(a);
                    }
                }
                json.put(getAreaCode(b.get(i)),arr);
                arr = new JSONArray();
            }
            return packResult(200,"",json.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
            return packResult(300,"","[]");
        }

    }

    /**
     *
     * @param teamid
     * @return
     */
    private List<Battle> getTeamRK(String teamid){
        try {
            String result = CommonUtil.getData("3");

            List<Battle> bs = JSONArray.parseArray(result,Battle.class);
            bs.sort(Comparator.comparing(Battle::getTeamId));


            JSONArray arr = new JSONArray();
            JSONObject json = new JSONObject();
            for(int i = 0; i < team.length; i++){
                Iterator<Battle> it = bs.iterator();
                while(it.hasNext()){
                    Battle b = it.next();
                    if(b.getTeamId().equals(team[i])){
                        arr.add(b);
                        it.remove();
                    }
                }
                json.put(team[i],arr);
                arr = new JSONArray();

            }
            team_json = json;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String team = team_json.getString(teamid);
        return JSONArray.parseArray(team,Battle.class);
    }

    /**
     *
     * @param areaCode
     * @return
     */
    private String getAreaName(String areaCode){
        switch (areaCode){
            case "jh" : return "嘉禾";
            case "js" : return "嘉善";
            case "hy" : return "海盐";
            case "hn" : return "海宁";
            case "tx" : return "桐乡";
            case "ph" : return "平湖";
            default : return "嘉禾";
        }
    }

    /**
     *
     * @param areaCode
     * @return
     */
    private String getAreaCode(String areaCode){
        switch (areaCode){
            case "嘉禾" : return "jh";
            case "嘉善" : return "js";
            case "海盐" : return "hy";
            case "海宁" : return "hn";
            case "桐乡" : return "tx";
            case "平湖" : return "ph";
            case "南湖" : return "nh";
            case "余新" : return "yx";
            case "洪合" : return "hh";
            case "秀洲" : return "xz";
            case "王江泾" : return "wjj";
            case "新丰" : return "xf";
            case "魏塘" : return "wt";
            case "经开" : return "jk";
            case "干窑" : return "gy";
            case "西塘" : return "xt";
            case "当湖" : return "dh";
            case "独山港" : return "dsg";
            case "乍浦" : return "zhp";
            case "新埭" : return "xd";
            case "武原" : return "wy";
            case "秦山" : return "qs";
            case "百步" : return "bb";
            case "硖石" : return "xs";
            case "长安" : return "ca";
            case "许村" : return "xc";
            case "盐官" : return "yg";
            case "袁花" : return "yh";
            case "崇福" : return "chf";
            case "梧桐" : return "wt";
            case "洲泉" : return "zq";
            case "乌镇" : return "wz";
            case "濮院" : return "py";


            default : return "jh";
        }
    }


    /****
     *
     * @param l
     * @param areaCode
     * @return
     */
    private List<String>  getCenterName(List<BattleArea> l,String areaCode){
        Iterator<BattleArea> it = l.iterator();
        while(it.hasNext()){
            BattleArea b = it.next();
            if (!b.getAreaName().equals(getAreaName(areaCode)))
                it.remove();
        }
        List<String> str = new ArrayList<>();
        for(int i = 0; i < l.size();i++){
            for(int j = l.size()-1;j > i; j--){
                if (l.get(j).getCenterName().equals(l.get(i).getCenterName()))
                    l.remove(j);
            }
        }
        System.out.println(JSON.toJSONString(l));
        for (int m = 0; m < l.size(); m++){
            str.add(l.get(m).getCenterName());
        }
        return str;

    }

    private Integer getVersion(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return Integer.parseInt(sf.format(new Date()));
    }

    private Integer getHour(){
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }
}
