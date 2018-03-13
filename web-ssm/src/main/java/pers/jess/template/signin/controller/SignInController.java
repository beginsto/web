package pers.jess.template.signin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sun.javaws.security.SigningInfo;
import com.sun.tools.hat.internal.model.HackJavaValue;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Now;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.model.User;
import pers.jess.template.common.utils.CharacterEncoding;
import pers.jess.template.common.utils.GetWeChatUserInfo;
import pers.jess.template.signin.model.*;
import pers.jess.template.signin.service.SignInService;
import pers.jess.template.signin.util.SignInUtil;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.cert.TrustAnchor;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("sign-in")
public class SignInController extends BaseController{

    private Logger log = Logger.getLogger(SignInController.class);

    private int tt = 1; /****test*/

    private int pagesize = 5;
    private int pagecurrent = 1;

    @Resource
    private SignInService signInService;

    @RequestMapping("index")
    public Object index(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        String mid = req.getParameter("mid")==null?"":req.getParameter("mid").trim();
        String code = req.getParameter("code")==null?"":req.getParameter("code").trim();

        String result = getUser(openid,mid);

       // if (user == null)
         //   return null;
        //if (user.getStatus() == 0)
         //   return null;

        model.addAttribute("openid",openid);
        return "signin/index";
    }

    @RequestMapping("active")
    public Object active(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        model.addAttribute("openid",openid);
        return "signin/active";
    }

    @RequestMapping("rank")
    public Object rank(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        model.addAttribute("openid",openid);
        return "signin/rank";
    }

    @RequestMapping("detail")
    public Object detail(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        model.addAttribute("openid",openid);
        return "signin/detail";
    }

    @RequestMapping("rule")
    public Object rule(HttpServletRequest req, Model model){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        model.addAttribute("openid",openid);
        return "signin/rule";
    }

    @RequestMapping("getData")
    @ResponseBody
    public Object getData(HttpServletRequest req){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        try {
            JSONObject json = new JSONObject();
            List<SignInUser> list = signInService.getParticipator();
            json.put("participator",JSON.toJSON(list));
            System.out.println(JSON.toJSON(list));

            int joinCount = signInService.queryJoinCount();
            json.put("count",joinCount);


            Date now = new Date();
            SignInUser user = signInService.queryByOpenid(openid);
            if (user == null){
                json.put("isLogin",1);
                json.put("starttime",SignInUtil.DateToShortString(now));
            }else{
                json.put("isLogin",0);
                Map<String, Object> map = new HashMap<>();
                map.put("userid",user.getId());
                map.put("createtime",new Date());
                SignInInfo info = signInService.queryByParams(map);

                if(info == null)
                    json.put("starttime",SignInUtil.DateToShortString(now));
                else{
                    int daysBetween = SignInUtil.daysBetween(info.getCreatetime(),now);
                    json.put("starttime",SignInUtil.getRegionStartTime(daysBetween,info.getCreatetime()));
                }
            }

            return json.toString();
        }catch (Exception e){
            log.error("签到，首页获取参与用户信息列表异常：［" + e.toString() + "］");
            e.printStackTrace();
            return "[]";
        }

    }

    @RequestMapping("validate")
    @ResponseBody
    public Object validate(HttpServletRequest req){
        String openid = req.getParameter("openid");
        try {

            SignInUser user  =  signInService.queryByOpenid(openid);

            if (user == null){
                //String result = GetWeChatUserInfo.getUserInfo(openid);
                String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"15905836605\",\"nick\":\"AAAA\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/EVPtJJ9TsHwy1qhryQF25O8Eh58dVZ4uagjYribwYXL3z1U9Wu7FiasmX12U89k2wZTHojJTHxUMXkkUSTiaTBAFA/0\",\"openId\":\"o252jt8MQGpHT6SzY-4k1qTT1f6A\"}}";
                //String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"13758300969\",\"nick\":\"惠\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/5cJ329xUeTwSssNMvsLH1dT4gq6skSGPo2Cv51BnWW02003WPJOyIQyfzWBV5zOD8AQHVHX59dhob2ayI8Y9aA/0\",\"openId\":\"oS8a0jrx8og_nwagf23PYk0TcOks\"}}";
                log.info("获取用户"+openid+"信息："+result);
                JSONObject rs = JSONObject.parseObject(result);
                if (result == null || result.equals("") || String.valueOf(rs.get("resultCode")).equals("300"))
                    return packResult(300,"非法进入...",null);

                JSONObject context = JSONObject.parseObject(rs.getString("resultContent"));
                String mobile =  String.valueOf(context.get("phone"));
                if (mobile.length() != 11)
                    return packResult(303,"号码未关联...",null);

                user = new SignInUser();
                user.setOpenid(openid);
                user.setMobile(mobile);
                user.setNickname(CharacterEncoding.stringToUnicode(String.valueOf(context.get("nick"))));
                user.setHeadimg(String.valueOf(context.get("headimgurl")));
                user.setMaxcount(0);
                user.setCreatetime(new Date());
                signInService.insert(user);

                user = signInService.queryByOpenid(openid);
                user.setNickname(CharacterEncoding.unicodeToString(user.getNickname()));
            }

            setSession(req,user,"signinuser");

            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("friendid",user.getId());
            SignInInvite inInvite = signInService.queryData(map);
            if (inInvite == null){
                inInvite = new SignInInvite();
                inInvite.setUserid(user.getId());
                inInvite.setFriendid(user.getId());
                inInvite.setCreatetime(new Date());
                signInService.insertInviteData(inInvite);
            }


            return packResult(200,"",null);
        }catch (Exception e){
            log.error("签到，首页获取参与用户信息列表异常：［" + e.toString() + "］");
            return packResult(500,"",null);
        }

    }

    @RequestMapping("getActiveData")
    @ResponseBody
    public Object getActiveData(HttpServletRequest req){
        try {
            SignInUser user = getSession(req);
            if (user == null){
                String openid = req.getParameter("openid");
                user = signInService.queryByOpenid(openid);
            }


            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("createtime",new Date());
            SignInInfo info = signInService.queryByParams(map);
            if (info == null) {
                user.setCount(1);
                user.setIsSign(false);
            }else{
                Date now = new Date();
                map.put("version", Integer.parseInt(SignInUtil.DateToString(now)));
                SignInAward award = signInService.selectByParams(map);
                if (award == null) {
                    user.setIsSign(false);

                    if (isContinuous(now,info.getLasttime()))
                        user.setCount(info.getCount()+1);
                    else{
                        info.setCount(1);
                        user.setCount(1);
                        signInService.updateByPrimaryKey(info);
                    }

                }else {
                    user.setCount(info.getCount()+1);
                    user.setIsSign(true);
                }
            }

            //user.setNickname(CharacterEncoding.unicodeToString(user.getNickname()));
            return JSON.toJSON(user);
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }


    }


    @RequestMapping("signin")
    @ResponseBody
    public Object signin(HttpServletRequest req){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        if (openid.equals(""))
            return packResult(400,"打开链接的方式有误...",null);

        try {
            Date now = new Date();

            int h = SignInUtil.getHour(now);
            int type = 1;

            if (h<5 || h>9)
                return packResult(400,"每天5点至10点才能签到...",null);
            if(h>=8 && h <10)
                type = 2;
            SignInUser user = getSession(req);
            if (user == null)
                user = signInService.queryByOpenid(openid);

            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("createtime",new Date());
            SignInInfo info = signInService.queryByParams(map);

            int award = 0;

            /***明细***/
            map.put("version", Integer.parseInt(SignInUtil.DateToString(now)));
            SignInAward aw= signInService.selectByParams(map);
            if (aw != null)
                return packResult(300,"签到异常",null);

            /****用户本期信息更新****/
            if (info == null){
                award = SignInUtil.getAward(1,type);
                insertAwardInfo(new SignInAward(user.getId(),type,award,now,Integer.parseInt(SignInUtil.DateToString(now))));
                //insertAwardInfo(new SignInAward(user.getId(),type,award,now,Integer.parseInt(SignInUtil.DateToString(now))+tt)); /****test*/

                info = new SignInInfo();
                info.setUserid(user.getId());
                info.setCreatetime(now);
                info.setCount(1);
                info.setIssue(SignInUtil.getIssue(now));
                info.setEndtime(SignInUtil.getEndTime(now));
                info.setAward(award);
                info.setLasttime(now);
                signInService.insert(info);
            }else{
                //是否连续，如果不连续重新开始
                if (isContinuous(now,info.getLasttime())){
                    award = SignInUtil.getAward(info.getCount()+1,type);
                    info.setCount(info.getCount()+1);
                }else{
                    award = SignInUtil.getAward(1,type);
                    info.setCount(1);
                }
                info.setAward(info.getAward()+award);
                info.setLasttime(now);

                insertAwardInfo(new SignInAward(user.getId(),type,award,now,Integer.parseInt(SignInUtil.DateToString(now))));
                //insertAwardInfo(new SignInAward(user.getId(),type,award,now,Integer.parseInt(SignInUtil.DateToString(now))+tt)); /****test*/
                signInService.updateByPrimaryKey(info);

                /***用户最长连续签到出现新高时更新用户信息***/
                if(user.getMaxcount() <= info.getCount()){
                    user.setCount(user.getMaxcount()+1);
                    signInService.updateByPrimaryKey(user);
                }
            }

            SignInLikeAmount record = new SignInLikeAmount();
            record.setUserid(user.getId());
            record.setVersion(Integer.parseInt(SignInUtil.DateToString(now)));
            record.setAmount(0);
            signInService.insertLikeData(record);

            /****test*/
                tt++;
            return packResult(200,null,"");
        }catch (Exception e){
            e.printStackTrace();
            log.error("签到，用户签到异常：［" + e.toString() + "］");
            return packResult(500,"",null);
        }

    }

    @RequestMapping("getSignList")
    @ResponseBody
    public Object getSignList(HttpServletRequest req){
        Map<String, Object> result = new HashMap<>();
        try {
            SignInUser user = (SignInUser)getSession(req);

            if (user == null){
                String openid = req.getParameter("openid");
                user = signInService.queryByOpenid(openid);
            }


            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("createtime",new Date());
            SignInInfo info = signInService.queryByParams(map);
            Date now = new Date();

            if(info == null)
                return packResult(300,"",null);
            else{
                int daysBetween = SignInUtil.daysBetween(info.getCreatetime(),now);
                if(daysBetween>=20)
                    result.put("isMall",true);

                String starttime = SignInUtil.getRegionStartTime(daysBetween,info.getCreatetime());
                int version = Integer.parseInt(starttime.replaceAll("-",""));
                Map<String,Object> param = new HashMap<>();
                param.put("version",version);
                param.put("userid",user.getId());

                SignInAward award = signInService.selectByParams(param);

                Date st = SignInUtil.StringToDate(starttime);
                Boolean isHaveRecord = false;
                JSONObject json = null;
                JSONArray arr = new JSONArray();
                for(int i=0; i < 21; i++){
                    json = new JSONObject();
                    Date dd = SignInUtil.getTime(st,i);
                    String d = SignInUtil.DateToString(dd);
                    if (award !=null && Integer.parseInt(d) == award.getVersion()){
                        isHaveRecord = true;
                        dd = award.getCreatetime();
                    }

                    if (SignInUtil.daysBetween(dd,now)>0){
                        if (isHaveRecord) {
                            json.put("day",SignInUtil.DateToStringSC(dd));
                            json.put("state", "打卡成功");
                        }else{
                            json.put("day",SignInUtil.DateToStringSC(dd).substring(0,6));
                            json.put("state","已错过");
                        }

                    }else if(SignInUtil.daysBetween(dd,now) == 0){
                        int h = SignInUtil.getHour(now);

                        if(h<5) {
                            json.put("day", SignInUtil.DateToStringSC(dd).substring(0, 6));
                            json.put("state", "未开始");
                        }else {
                            if (isHaveRecord) {
                                json.put("day",SignInUtil.DateToStringSC(dd));
                                json.put("state", "打卡成功");
                            }else{
                                json.put("day",SignInUtil.DateToStringSC(dd).substring(0,6));
                                if (h>9)
                                    json.put("state","已错过");
                                else
                                    json.put("state","进行中");
                            }
                        }
                    }else if (SignInUtil.daysBetween(dd,now)== -1){
                        json.put("day",SignInUtil.DateToStringSC(dd).substring(0,6));
                        json.put("state",SignInUtil.getHourByNow(now)+"小时后开始");
                    }else{
                        json.put("day",SignInUtil.DateToStringSC(dd).substring(0,6));
                        json.put("state","未开始");
                    }
                    arr.add(json);
                    isHaveRecord = false;
                }

                result.put("json",arr);
                return result;
            }
        }catch (ParseException e){
            log.error("签到详情页，获取用户签到列表日期格式转换异常：［" + e.toString() + "］");
            e.printStackTrace();
            return "[]";
        }catch (Exception e){
            log.error("签到详情页，获取用户签到列表异常：［" + e.toString() + "］");
            e.printStackTrace();
            return "[]";
        }

    }

    @RequestMapping("getRankUserInfo")
    @ResponseBody
    public Object getRankUserInfo(HttpServletRequest req){
        SignInUser user = new SignInUser();
        try {

            user = getSession(req);
            if (user == null)
                return packResult(300,"未能获取用户信息",null);

            Date now = new Date();
            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("createtime",now);
            SignInInfo info = signInService.queryByParams(map);

            if (info == null)
                return packResult(300,"本期未参与",null);
            user.setCount(info.getCount());
            user.setAward(info.getAward());
            String lasttime = SignInUtil.DateToShortString(info.getLasttime());
            String today = SignInUtil.DateToShortString(now);
            if (lasttime.equals(today))
                user.setSignintime(SignInUtil.DateToLongString(info.getLasttime()).substring(11));
            else
                user.setSignintime("今天未打卡");

            map.put("version",SignInUtil.DateToString(now));
            SignInAward sa = signInService.selectByParams(map);
            if (sa == null)
                user.setAwardtoday(0);
            else
                user.setAwardtoday(sa.getAward());
        }catch (Exception e){
            log.error("签到排行页，获取用户信息异常：［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,null,"");
        }

        return packResult(200,"",JSON.toJSON(user).toString());
    }


    @RequestMapping("getRankFriendData")
    @ResponseBody
    public Object getRankFriendData(HttpServletRequest req){
        SignInUser user = getSession(req);
        Map<String, Object> result = new HashMap<>();
        String pageCurrent = req.getParameter("page");
        String pageSize = req.getParameter("size");
        try {
            if (user == null)
                return packResult(300,"未能获取用户信息",null);

            result.put("user",JSON.toJSONString(user));

            Date now = new Date();
            Map<String, Object> map =new HashMap<>();
            map.put("userid",user.getId());
            String version = SignInUtil.DateToString(now);
            map.put("version",version);
            PageHelper.startPage(Integer.parseInt(pageCurrent),Integer.parseInt(pageSize));
            List<SignInInvite> list = signInService.queryRankFriendList(map);

            int index= 1;
            for (SignInInvite i : list){
                if (i.getLasttime()!= null && SignInUtil.DateToString(i.getLasttime()).equals(version))
                    i.setIsLike(1);
                else
                    i.setIsLike(0);
                i.setSignintime(SignInUtil.DateToLongString(i.getCreatetime()).substring(11,16));
                i.setNickname(CharacterEncoding.unicodeToString(i.getNickname()));
                index++;
            }
            String[] rs = getFriendOrderNum(user.getId());
            result.put("rankself",rs[0]);
            result.put("day",rs[1]);
            result.put("time",rs[2]);


            result.put("rank",JSON.toJSONString(list));

            return packResult(200,"",JSON.toJSONString(result));
        }catch (Exception e){
            log.error("签到排行页，获取好友排行异常：［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,null,"");
        }

    }

    @RequestMapping("getRankWorldData")
    @ResponseBody
    public Object getRankWorldData(HttpServletRequest req){
        SignInUser user = getSession(req);
        Map<String, Object> result = new HashMap<>();
        String pageCurrent = req.getParameter("page");
        String pageSize = req.getParameter("size");
        try {
            Date now = new Date();
            int version = Integer.parseInt(SignInUtil.DateToString(now));
            PageHelper.startPage(Integer.parseInt(pageCurrent),Integer.parseInt(pageSize));
            List<SignInAward> list = signInService.queryRankWorld(version);
            for(SignInAward a : list){
                if (a.getCreatetime() != null)
                    a.setSignintime(SignInUtil.DateToLongString(a.getCreatetime()).substring(11));
                if(a.getNickname()!=null && a.getNickname().contains("\\u"))
                    a.setNickname(CharacterEncoding.unicodeToString(a.getNickname()));
            }
            if (pageCurrent.equals("1")){
                String[] rs =  getRankWorldOrderNum(user.getId());
                result.put("rankself", rs[0]);
                user.setSignintime(rs[1]);
            }

            result.put("rank",JSON.toJSONString(list));
            result.put("user",JSON.toJSONString(user));
            return packResult(200,"",JSON.toJSONString(result));
        }catch (Exception e){
            log.error("签到排行页，获取世界排行异常：［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,null,"");
        }
    }

    @RequestMapping("getRankPopularityData")
    @ResponseBody
    public Object getRankPopularityData(HttpServletRequest req){
        SignInUser user = getSession(req);
        Map<String, Object> result = new HashMap<>();
        String pageCurrent = req.getParameter("page");
        String pageSize = req.getParameter("size");
        try {
            Date now = new Date();
            int version = Integer.parseInt(SignInUtil.DateToString(now));
            PageHelper.startPage(Integer.parseInt(pageCurrent),Integer.parseInt(pageSize));
            List<SignInLikeAmount> list = signInService.queryRankPopularity(version);

            for(SignInLikeAmount a : list)
                if (a.getNickname()!=null && a.getNickname().contains("\\u"))
                a.setNickname(CharacterEncoding.unicodeToString(a.getNickname()));

            result.put("amount",getOrderNum(user.getId()));
            result.put("rank",JSON.toJSONString(list));
            result.put("user",JSON.toJSONString(user));
            return packResult(200,"",JSON.toJSONString(result));
        }catch (Exception e){
            log.error("签到排行页，获取人气排行异常：［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,null,"");
        }
    }


    @RequestMapping("like")
    @ResponseBody
    public Object like(HttpServletRequest req){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
        String friendid = req.getParameter("friendid")==null?"":req.getParameter("friendid");
        String islike = req.getParameter("islike")==null?"":req.getParameter("islike");
        if (openid.equals("") || friendid.equals("") || islike.equals(""))
            packResult(300,"",null);
        try {
            Date now = new Date();
            Map<String, Object> result = new HashMap<>();
            SignInUser user = getSession(req);

            if (friendid.equals(""+user.getId()))
               return packResult(300,"不能给自己点赞...",null);

            Map<String, Object> map = new HashMap<>();
            map.put("userid",user.getId());
            map.put("friendid",friendid);
            SignInInvite invite = signInService.queryData(map);
            if (invite == null)
                return packResult(300,"未能查询到好友信息",null);

            if (SignInUtil.DateToString(now).equals(SignInUtil.DateToString(invite.getLasttime())))
                return packResult(303,"",null);

            invite.setLasttime(now);
            signInService.updateByPrimaryKey(invite);


            map.put("userid",friendid);
            map.put("version",SignInUtil.DateToString(now));
            SignInLikeAmount likeList = signInService.queryLikeData(map);
            if (likeList == null){
                likeList = new SignInLikeAmount();
                likeList.setUserid(Integer.parseInt(friendid));
                likeList.setVersion(Integer.parseInt(SignInUtil.DateToString(now)));
                likeList.setAmount(1);
                 signInService.insertLikeData(likeList);
            }else {
                likeList.setAmount(likeList.getAmount()+1);
                signInService.updateLikeData(likeList);
            }

            return packResult(200,"",JSON.toJSONString(result));
        }catch (Exception e){
            log.error("签到排行页，点赞异常：［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,"",null);
        }
    }

    public void insertAwardInfo(SignInAward award){
        signInService.insert(award);
    }


    public String[] getFriendOrderNum(int userid){

        String[] rs={"","",""};
        Date now = new Date();
        Map<String, Object> map =new HashMap<>();
        map.put("userid",userid);
        String version = SignInUtil.DateToString(now);
        map.put("version",version);
        List<SignInInvite> list = signInService.queryRankFriendList(map);
        int index = 1;
        for (SignInInvite i : list){
            if (i.getFriendid() == userid){
                rs[0] =""+index;
                rs[1] =SignInUtil.DateToStringSC(i.getCreatetime()).substring(0,5);
                rs[2] =SignInUtil.DateToLongString(i.getCreatetime()).substring(11,16);
                break;
            }
            index++;
        }


        return rs;
    }

    public int getOrderNum(int userid){
        Date now = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("version",SignInUtil.DateToString(now));
        SignInLikeAmount amount = signInService.queryLikeData(map);
        return amount==null?0:amount.getAmount();
    }

    public String[] getRankWorldOrderNum(int userid){
        String[] arr = {"",""};
        Date now = new Date();
        int version = Integer.parseInt(SignInUtil.DateToString(now));
        List<SignInAward> list = signInService.queryRankWorld(version);
        int index = 1;
        String signintime = "";
        for(SignInAward a : list){
            if (a.getUserid() == userid) {
                signintime = SignInUtil.DateToLongString(a.getCreatetime()).substring(11);
                break;
            }
            index++;
        }
        arr[0] = ""+index;
        arr[1] = signintime;
        return arr;
    }


    /**
     * 从session提取用户信息
     * @param req
     * @return
     */
    private SignInUser getSession(HttpServletRequest req){
        SignInUser user = (SignInUser) req.getSession().getAttribute("signinuser");
        if (user == null){
            String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
            user = signInService.queryByOpenid(openid);
            if (user.getNickname()!= null &&  user.getNickname().contains("\\u")){
                user.setNickname(CharacterEncoding.unicodeToString(user.getNickname()));
            }
        }
        return user;
    }

    public Boolean isContinuous(Date d1, Date d2){
        String yesterday = SignInUtil.DateToString(SignInUtil.getYesterday(d1));
        String lasttime = SignInUtil.DateToString(d2);
        if (yesterday.equals(lasttime))
            return true;
        else
            return false;
    }



    //size:11
    private  String[] nameArr = {"赵","钱","孙","李","周","吴","郑","王","和","秦","古","冯","陈","褚","卫","蒋","沈","韩","杨","朱","杨","朱","尤","许","何"};

    private  Random rand = new Random();

    //size:15
    private  String[] headimg = { "http://wx.qlogo.cn/mmopen/EVPtJJ9TsHwy1qhryQF25O8Eh58dVZ4uagjYribwYXL3z1U9Wu7FiasmX12U89k2wZTHojJTHxUMXkkUSTiaTBAFA/0",
            "http://wx.qlogo.cn/mmopen/bRuicokePB84ibvHFJIOzLXAgzHDM9ZGMqxiaibjBabt0Ox7Ax3tIO7UiamnVib3j0cWlGtJ7KmPF6r5o04MnHAhGZ2ib6VhQHEPElz/0",
            "http://wx.qlogo.cn/mmopen/bRuicokePB84ibvHFJIOzLXAgzHDM9ZGMqxiaibjBabt0Ox7Ax3tIO7UiamnVib3j0cWlGtJ7KmPF6r5o04MnHAhGZ2ib6VhQHEPElz/0",
            "http://wx.qlogo.cn/mmopen/Q3auHgzwzM4aaZkpIzcFL77DeQtnMjeaiacWvMzxoMcUYTCibaSeUGtdwm9mQ8zka188KnPd8iaUiazOAFCicfPdOoQ/0",
            "http://wx.qlogo.cn/mmopen/56ic5nG39Uaw3eHlYG6qarILvcU9oiczkCGvtkMdFklAhzgWwj47lvy3ibU730Lzuib7046U7p0XOz012FzxPia5EjzVqB87a4FrF/0",
            "http://wx.qlogo.cn/mmopen/5cJ329xUeTye6yA7n8EygU5zKbSF4NdxPCqSLuJGiacfevXZBdxkyLHH24H93jrdNq2GD4ZibcA94D5ExhfQCAjxVyn3Q0icRwh/0",
            "http://wx.qlogo.cn/mmopen/56ic5nG39UawgAbZ6zJ0d0hGbNo7eUUmFiaMNIvA23iaia5hHBuGEVExRp8DjdxTz9GCVR2Knynr9FRGLicLOfyGrbhk3d3icDR899/0",
            "http://wx.qlogo.cn/mmopen/Q3auHgzwzM6O0hhD7eSMx6icmJjpUXAIpWXxE5j2ycrqZG67vWAjMJZAsIlGIvldIJoiazfwv4rzeKrnicF6yT43A/0",
            "http://wx.qlogo.cn/mmopen/56ic5nG39Uaw3eHlYG6qarNDRhNKQGIUuEpXRLLXBBjAJNRrK1FPV4mhzbqA0jgNfhlJcDafrTImx6WxcGSw3I2UlP20XywicV/0",
            "http://wx.qlogo.cn/mmopen/PiajxSqBRaEKDTjsojzowVWCztiaYNpc0fvfTDGjL0koT2v39sPqlicKB4CPqkAAd3T7pgljSeJrWG2yXEIwAENiaA/0",
            "http://wx.qlogo.cn/mmopen/56ic5nG39Uaw3eHlYG6qarLUq3XJqEZ1AFVtAp4sob7uem2KjUFI33VwOtxEymX0X6D1Yq5wgxbUvVfCibd3j86FxdqLz4JjCc/0",
            "http://wx.qlogo.cn/mmopen/hEbk85BlbHAGzw7bWw2Rn4Qq1YDzYqMyW8qHcYkLFiaWzaHdia3SoK76syK8qGBO8LpHMHyhY5beVofTBRAqIKTRetbZI5fMkq/0",
            "http://wx.qlogo.cn/mmopen/PiajxSqBRaEI1hDgXxFLR2WEOJ7fuX7am7eFuX3EEH7ibwia23GtLYpvHd4Yv7cvIlTgdcQ4ZiawA0FIb7DibcER2pQ/0",
            "http://wx.qlogo.cn/mmopen/5cJ329xUeTwJy5DWN3CNfic4HQXDznSjG9jxqibNDtS7pghIPQAuHiaJ1wBcUkrWuuYP0GZnAcbtukd5kkolXZ4YA/0",
            "http://wx.qlogo.cn/mmopen/5cJ329xUeTzubEfZTgokwLPIvqnB3kDU5qBk4WSqwp3e7pfibAbFwDG8MzV7vq3BoCM3WNN1IU4JAyEhOlh0euw/0",
    };


    public String getNickName(){
        return nameArr[rand.nextInt(24)]+nameArr[rand.nextInt(24)]+nameArr[rand.nextInt(24)];
    }

    public String getHeadimg(){

        return headimg[rand.nextInt(15)];
    }

    public String getOpenid(){
        String val="";

        for(int i = 0; i < 38; i++) {
            //输出是大写字母还是小写字母
            int temp = rand.nextInt(2) % 2 == 0 ? 65 : 97;
            val += (char)(rand.nextInt(26) + temp);
        }
        return val;
    }

    @RequestMapping("test")
    @ResponseBody
    public Object test(){
        Date now = new Date();
        int mobile = 10000;
        try {
            for(int i=0;i<20000;i++){
                String openid = getOpenid();
                SignInUser user = new SignInUser();
                user.setOpenid(openid);
                user.setNickname(CharacterEncoding.stringToUnicode(getNickName()));
                user.setHeadimg(getHeadimg());
                user.setCreatetime(now);
                user.setMobile("130573"+(mobile+i));
                user.setMaxcount(0);
                signInService.insert(user);
                user = signInService.queryByOpenid(openid);

                SignInInvite invite = new SignInInvite();
                invite.setUserid(user.getId());
                invite.setFriendid(user.getId());
                invite.setCreatetime(now);
                signInService.insertInviteData(invite);

                SignInAward award = new SignInAward();
                award.setUserid(user.getId());
                award.setOperate(1);
                award.setAward(10);
                award.setCreatetime(now);
                award.setVersion(Integer.parseInt(SignInUtil.DateToString(now)));
                signInService.insert(award);

                SignInInfo info = new SignInInfo();
                info.setUserid(user.getId());
                info.setCount(1);
                info.setLasttime(now);
                info.setAward(10);
                info.setIssue(SignInUtil.DateToString(now)+"/"+SignInUtil.DateToString(SignInUtil.getEndTime(now)));
                info.setCreatetime(now);
                info.setEndtime(SignInUtil.getEndTime(now));
                signInService.insert(info);

                SignInLikeAmount record = new SignInLikeAmount();
                record.setUserid(user.getId());
                record.setVersion(Integer.parseInt(SignInUtil.DateToString(now)));
                record.setAmount(0);
                signInService.insertLikeData(record);

                System.out.println("用户："+openid+"数据插入完成....");

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }
}
