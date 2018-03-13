package pers.jess.template.starSign.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.java.emoji.EmojiConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.model.User;
import pers.jess.template.common.utils.CharacterEncoding;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.common.utils.GetWeChatUserInfo;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;
import pers.jess.template.starSign.model.ConstellationAward;
import pers.jess.template.starSign.model.ConstellationInfo;
import pers.jess.template.starSign.model.ConstellationInvite;
import pers.jess.template.starSign.service.StarSignService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "star-sign")
public class StarSignController extends BaseController{

    //private  int[] award_count = {5000,2500,1500,800,400};
    //private  int[] award_count = {5000,5000};
    private  int[] award_count = {4000,2000,400,200,5000,4000,3000,1000};

    //private String[] award_arr = {"100M流量券","200M流量券","300M流量券","500M流量券","1GB流量券"};
    //private String[] award_arr = {"500M流量券","600M流量券"};//
    private String[] award_arr = {"1元话费券","2元话费券","3元话费券","5元话费券","100M流量券","200M流量券","300M流量券","500M流量券"};


    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Resource
    private StarSignService starSignService;

    @Resource
    private LocalPhoneService localPhoneService;


    @RequestMapping(value = "index")
    public Object index(HttpServletRequest req, Model model) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String date = sf.format(now);

        if (now.after(sf.parse("2018-01-19 23:59:59"))){
            return "star-sign/timeout";
        }else{
            String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
            String mid = req.getParameter("toUser")==null?"":req.getParameter("toUser").trim();
            String constellation_issue = CommonUtil.getConstellation(now);//当前星座
            List<ConstellationInfo> infoList = starSignService.queryInfoList(constellation_issue);
            for(ConstellationInfo info : infoList){
                String nick = info.getNickname();
                if (nick!= null &&  nick.contains("\\u")){
                    info.setNickname(CharacterEncoding.unicodeToString(nick));
                }
            }

            int count = starSignService.queryInfoCount();
            model.addAttribute("openid",openid);
            model.addAttribute("mid",mid);
            model.addAttribute("info",infoList);
            model.addAttribute("amount",count);
            model.addAttribute("amount",count);
            return "star-sign/index";
        }

    }

    @RequestMapping(value = "test")
    public Object test(HttpServletRequest req, Model model) throws ParseException{
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String date = sf.format(now);

        if (now.after(sf.parse("2017-11-21 23:59:59"))){
            return "star-sign/timeout";
        }else {
            String openid = req.getParameter("openid") == null ? "" : req.getParameter("openid").trim();
            String mid = req.getParameter("toUser") == null ? "" : req.getParameter("toUser").trim();
            String constellation_issue = CommonUtil.getConstellation(now);//当前星座
            List<ConstellationInfo> infoList = starSignService.queryInfoList(constellation_issue);
            for (ConstellationInfo info : infoList) {
                String nick = info.getNickname();
                if (nick != null && nick.contains("\\u")) {
                    info.setNickname(CharacterEncoding.unicodeToString(nick));
                }
            }

            int count = starSignService.queryInfoCount();
            model.addAttribute("openid", openid);
            model.addAttribute("mid", mid);
            model.addAttribute("info", infoList);
            model.addAttribute("amount", count);
            return "/WEB-INF/jsp/views/star-sign/index.jsp_Sagittarius";
        }


    }


    @RequestMapping(value = "friend")
    public Object friend(String openid,Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("inviterid",openid);
        map.put("issue",CommonUtil.getConstellation(new Date()));
        map.put("issuccess",1);
        int count = starSignService.queryCountByParam(map);
        if (count == 0)
            model.addAttribute("isHelp","N");
        else
            model.addAttribute("isHelp","Y");
        map.put("openid",openid);
        ConstellationInfo info = starSignService.queryByParam(map);

        model.addAttribute("openid",openid);
        model.addAttribute("user",info);
        return "star-sign/friend";
    }

    @RequestMapping(value = "forward")
    public Object forward(String openid,String nickName, Model model){
        model.addAttribute("nickName",nickName);
        model.addAttribute("openid",openid);
        return "star-sign/forward";
    }

    /**
     * 404：openid为空或无效；405：未绑手机号码；406：当期奖品已领完；407:当期已抽过奖；408:符合当前日期星座；409:本期未邀请；410:默认状态；
      * @param req
     * @return
     */
    @RequestMapping(value = "getInfo", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getInfo(HttpServletRequest req, String callback){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid").trim();
        Map map = new HashMap();
        map.put("isLogin","N");
        map.put("isBind","N");
        map.put("hadAward","N");
        map.put("isRaffle","");
        map.put("isIssue","N");
        map.put("isInvited","N");
        map.put("isLocal","N");

        //
        try {
            if (openid.equals(""))
                return packResult(callback,10000,"",JSON.toJSONString(map));


            //奖品没有剩余
            Date now = new Date();
            String constellation_issue = CommonUtil.getConstellation(now);//当前星座
            List<ConstellationAward> award = starSignService.queryAward(constellation_issue);
            //int hadAward = ;
            if (!hadAward(award))
                return packResult(callback,20000,"",JSON.toJSONString(map));
            else
                map.put("hadAward","Y");
            User user = (User) getSessionUserInfo(req,"user");
            if (user == null || user.getPhone() == null || !openid.equals(user.getOpenid())){
               String result = GetWeChatUserInfo.getUserInfo(openid);
             JSONObject rs = JSONObject.parseObject(result);
                if (result == null || result.equals("") || String.valueOf(rs.get("resultCode")).equals("300"))
                    return packResult(callback,20000,"",JSON.toJSONString(map));
                else
                    map.put("isLogin","Y");
                String content = rs.getString("resultContent");
                user = JSONObject.parseObject(content,User.class);
                setSession(req,user,"user");
            }


            String mobile =  user.getPhone();
            map.put("nick",user.getNick());
            if (mobile == null || mobile.length() != 11 )
                return packResult(callback,20000,"",JSON.toJSONString(map));
            else
                map.put("isBind","Y");
            map.put("mobile",mobile);

            //已经抽过奖
            String isRaffle = isRaffle(openid,now);

            if (isRaffle!=null  ){
                String[] raffles = isRaffle.split(",");
                map.put("isRaffle",raffles[0]);
                map.put("raffleTime",raffles[1]);
                return packResult(callback,20000,"",JSON.toJSONString(map));
            }


            //绑定号码为非嘉兴号码
            if (!isLocalMobile(mobile))
                return packResult(callback,20000,"",JSON.toJSONString(map));
            else
                map.put("isLocal","Y");


            //当前星座用户
            String zodiac = CommonUtil.getConstellation(mobile).replace("摩","魔");//用户星座

            //String zodiac = "射手座";
            map.put("zodiac",zodiac);
            //map.put("issue",constellation_issue);
            if(isConstellation(zodiac,now))
                map.put("isIssue","Y");
            else
                return packResult(callback,20000,"",JSON.toJSONString(map));


            //非当前星座用户没有邀请成功
            if (isInvited(openid,1,constellation_issue))
                map.put("isInvited", "Y");
            else
                return packResult(callback,20000,"",JSON.toJSONString(map));


            //log.info(packResult(410,"",JSON.toJSONString(map)));
            return packResult(callback,20000,"", JSON.toJSONString(map));
        }catch (Exception e){
            log.error("星座首页验证异常，Exception：［" + e.toString() + "");
            e.printStackTrace();
            return packResult(callback,30000,"",null);
        }

    }


    /**
     * 抽奖
     * @param req
     * @return
     */
    @RequestMapping(value = "raffle")
    @ResponseBody
    public synchronized Object raffle(HttpServletRequest req, ConstellationInfo info, String callback){
        String openid = req.getParameter("openid")==null?"":req.getParameter("openid");
      //  String raffleType = req.getParameter("raffleType")==null?"":req.getParameter("raffleType");
        try {
            Date now = new Date();
            if (StringUtils.isEmpty(openid) || !StringUtils.equals(info.getZodiac(),CommonUtil.getConstellation(now)))
                return packResult(callback, 10000,"",null);

            if (isRaffle(openid,now) != null)
                return packResult(callback, 20000,"repeat",null);


            int temp = doRaffle(now);
            if (temp == -1){
                return packResult(callback, 30000,"",null);
            }
            String nick = info.getNickname();
            if (nick != null )
                info.setNickname(emojiConverter.toAlias(nick));
            info.setAward(award_arr[temp]);
            info.setCreatetime(now);

            Map<String, Object> a = new HashMap<>();
            a.put("award",temp);
            a.put("raffleTime",now.getTime());
            log.info("用户："+info.getOpenid()+"，号码："+info.getUnionid()+"，奖品："+info.getAward());
            if (starSignService.insertData(info) == 0)
                return packResult(callback, 30000,"",null);
            else
                return packResult(callback, 20000,"",JSON.toJSONString(a));
        }catch (Exception e){
            e.printStackTrace();
            log.error("error："+JSON.toJSONString(info));
            return packResult(callback, 50000,"",null);
        }

    }

    @RequestMapping(value = "validate")
    @ResponseBody
    public Object validate(String openid, String mobile, String callback){
        if (openid == null || mobile == null || openid.equals("") || mobile.equals("")){
            return packResult(callback,10000,"",null);
        }

        Date now  = new Date();
        String zodiac = CommonUtil.getConstellation(mobile);//用户星座
       // String zodiac = "魔羯座";
        String constellation_issue = CommonUtil.getConstellation(now);//当前星座
        if (zodiac == null || !zodiac.replace("摩","魔").equals(constellation_issue))
            return packResult(callback,20000,"failed",null);

        Map<String, Object> map = new HashMap<>();
        map.put("openidserver",mobile);
        map.put("issuccess",1);
        map.put("issue",constellation_issue);
        int temp = starSignService.queryHelpCount(map);
        if (temp >= 5)
            return packResult(callback,20000,"overrun",null);


        map.put("inviterid",openid);

        if (starSignService.queryHelpRepeat(map) > 0)
            return packResult(callback,20000,"repeat",null);



        ConstellationInvite invite = new ConstellationInvite();
        invite.setInviterid(openid);
        invite.setCreatetime(now);
        invite.setIssuccess(1);
        invite.setSuccesstime(now);
        invite.setIssue(constellation_issue);
        invite.setOpenidserver(mobile);
        if (starSignService.insertSelective(invite) > 0)
            return packResult(callback,20000,"success",null);
        else
            return packResult(callback,30000,"error",null);
    }

    /**
     *
     * @param openid
     * @param callback
     * @return
     */
    @RequestMapping(value = "getHelpInfo")
    @ResponseBody
    public Object getHelpInfo(String openid, String callback){
        Map<String, Object> map = new HashMap<>();
        map.put("inviterid",openid);
        map.put("issue",CommonUtil.getConstellation(new Date()));
        map.put("issuccess",1);
        int count = starSignService.queryCountByParam(map);
        Map<String ,Object> result = new HashMap<>();
        if (count == 0)
            result.put("isHelp","N");
        else
            result.put("isHelp","Y");
       // map.put("openid",openid);
        //ConstellationInfo info = starSignService.queryByParam(map);
        //result.put("user",JSON.toJSONString(info));
        return JSON.toJSONString(result);
    }


    /**
     *
     * @return
     */
    private synchronized int doRaffle(Date now){
        try {

            String constellation_issue = CommonUtil.getConstellation(now);//当前星座
            List<ConstellationAward> award = starSignService.queryAward(constellation_issue);

            List<Integer> list = getAwardList(award);
            if (list == null || list.size() == 0)
                return -1;

            int temp = list.size();
            Random rand = new Random();
            int m = rand.nextInt(temp);
            ConstellationAward a = award.get(list.get(m));
            a.setVersion(a.getVersion() + 1);
            a.setAmount(a.getAmount() + 1);

            if (starSignService.updateData(a) == 0)
                return -2;

            return list.get(m);
        }catch (Exception e){
            log.error("抽奖异常，Exception：［"+ e.toString() + "］");
            e.printStackTrace();
            return 300;
        }

    }

    /**
     * 奖品是否有剩余
     * @param
     * @return
     */
    private boolean hadAward(List<ConstellationAward> list){
        try {
            List<Integer> aw = getAwardList(list);
            if (aw == null || aw.size() == 0)
                return false;
            else
                return true;
        }catch (Exception e){
            log.error("判断奖品数量是否剩余异常，Exception：［"+ e.toString() + "］");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 获取剩余奖品列表
     * @param list
     * @return
     */
    public List<Integer> getAwardList(List<ConstellationAward> list){
        try {
            List<Integer> arr = new ArrayList<>();
            for(int i = 0; i < list.size(); i++){
                if (list.get(i).getAmount() < award_count[i]){
                    arr.add(i);
                }
            }
            return arr;
        }catch (Exception e){
            log.error("获取剩余奖品列表异常，Exception：［" + e.toString() + "］");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 本期是否抽过奖
     * @param openid
     * @param date
     * @return
     */
    private String isRaffle(String openid,Date date){
        try {
            String constellation_issue = CommonUtil.getConstellation(date);
            Map<String, Object> param = new HashMap<>();
            param.put("openid",openid);
            param.put("issue",constellation_issue);
            ConstellationInfo info = starSignService.queryByParam(param);
            if (info !=null && info.getAward() !=null)
                return info.getAward()+","+info.getCreatetime().getTime();
            else
                return null;
        }catch (Exception e){
            log.error("判断是否抽奖异常，exception：［" + e.toString() + "］");
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 用户星座与当前日期所属星座是否匹配
     * @param zodiac
     * @param date
     * @return
     */
    private boolean isConstellation(String zodiac, Date date){
        try {
            String constellation_issue = CommonUtil.getConstellation(date);
            if (zodiac != null && !zodiac.equals("") && zodiac.equals(constellation_issue))
                return true;
            else
                return false;
        }catch (Exception e){
            log.error("判断星座接口异常，exception：［" + e.toString() + "］");
            e.printStackTrace();
            return false;
        }


    }

    /**
     * 是否邀请成功
     * @param openid
     * @param isSuccess
     * @return
     */
    public boolean isInvited(String openid, int isSuccess,String issue){
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("inviterid",openid);
            param.put("issuccess",isSuccess);
            param.put("issue",issue);
            int invite_amount = starSignService.queryCountByParam(param);
            if (invite_amount == 0)
                return false;
            else
                return true;
        }catch (Exception e){
            log.error("判断是否邀请成功异常，exception：［" + e.toString() + "］");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 是否嘉兴号码
     * @param mobile
     * @return
     */
    private boolean isLocalMobile(String mobile){
        try {
            LocalPhone localPhone = localPhoneService.quaryByPhone(mobile.substring(0,7));
            if (localPhone == null)
                return false;
            else
                return true;
        }catch (Exception e){
            log.error("判断是否嘉兴号码异常，exception：［" + e.toString() + "］");
            e.printStackTrace();
            return false;
        }

    }

}
