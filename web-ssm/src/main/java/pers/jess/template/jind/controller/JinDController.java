package pers.jess.template.jind.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.common.utils.GetWeChatUserInfo;
import pers.jess.template.jind.model.TMData;
import pers.jess.template.jind.model.TimeMachineAward;
import pers.jess.template.jind.model.TimeMachineDetail;
import pers.jess.template.jind.model.TimeMachineUser;
import pers.jess.template.jind.service.TimeMachineService;
import pers.jess.template.jind.util.JinDUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("jind")
public class JinDController extends BaseController{

    private final String STARTTIME = "2017-12-22";

    private final String AUTHURL="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68c88f3dca13e997&redirect_uri=http%3A%2F%2Fcard.1860.cn%2Fpublic%2Fweixin%2FweixinMob%2FunionUrl%3Fflag%3D32%26jumpMid%3Dgh_1cc642719ceb%26platForm%3Dmp&response_type=code&scope=snsapi_base&state=gh_7181b250f86a&connect_redirect=1#wechat_redirect";


    //188元最近一次中奖时间与开始时间相差天数
    private static Integer BETWEENDAYS = 0;

    @Resource
    private TimeMachineService TMService;


    @RequestMapping("index")
    public Object toIndex(HttpServletRequest req,String openid,String num,String platForm, Model model){

        //System.out.println("----------------------------------------------------------------------");
        //System.out.println("openid="+openid);
        //System.out.println("num="+num);
        //System.out.println("platForm="+platForm);
        //System.out.println("----------------------------------------------------------------------");


        //移动手机营业厅入口
        if(StringUtils.equals("app",platForm) && !StringUtils.isEmpty(num) && num.length() == 11){

            if (getSessionUserInfo(req,"jindUser") == null){
                TimeMachineUser u = new TimeMachineUser();
                u.setOpenid("app_"+num);
                u.setPhone(num);
                u.setNick(num.substring(0,3)+"****"+num.substring(7));
                setSession(req,u,"jindUser");
            }

            model.addAttribute("openid","app_"+num);
            return "time-machine/index";
        }


        //微信通道
        if (StringUtils.equals("mp",platForm)){
            if (StringUtils.isEmpty(openid))
                return "redirect:"+AUTHURL;
            //首次进入获取用户信息，存入session
            TimeMachineUser uu = (TimeMachineUser)getSessionUserInfo(req,"jindUser");
            if (uu == null || StringUtils.isEmpty(uu.getPhone())){
                //test
                //String result = "{\"resultCode\":200,\"resultContent\":{\"phone\":\"15705731196\",\"nick\":\"\\uD83D\\uDC1FK\",\"status\":1,\"headimgurl\":\"http://wx.qlogo.cn/mmopen/6vRjrk8z87ib4oleFR2f5PeZ30REWic3MiahcyGNttUPaicldXIQx2U7LPStTKW9cKwF1Reibwiclf18EnEzqUia0Iqzw/0\",\"openId\":\"oS8a0juXrQHRKXbdpCixHO_Jvs70\"}}";
                //test
                long s = System.currentTimeMillis();
                String result = GetWeChatUserInfo.getUserInfo(openid);
                long e = System.currentTimeMillis();
                log.info("用户:"+openid+"---获取状态信息时间："+ (e-s));
                //log.info("获取结果："+result);

                //log.info("openid:"+result);
                JSONObject rs = JSONObject.parseObject(result);
                if (result == null || result.equals("") || String.valueOf(rs.get("resultCode")).equals("300"))
                    return packResult(404,"",null);

                String content = rs.getString("resultContent");
                JSONObject j = JSONObject.parseObject(content.replaceAll("openId","openid"));
                TimeMachineUser u = JSONObject.toJavaObject(j,TimeMachineUser.class);

                setSession(req,u,"jindUser");
            }



            model.addAttribute("openid",openid);
            return "time-machine/index";
        }


        return "time-machine/index";


    }

    @RequestMapping("indexData")
    @ResponseBody
    public Object getIndexData(HttpServletRequest req,String openid){
        if (StringUtils.isEmpty(openid))
            return packResult(10000,"授权已过期，请重新登陆",null);

        TimeMachineUser user = (TimeMachineUser) getSessionUserInfo(req,"jindUser");

        if (user == null )
            return packResult(10001,"授权已过期，请重新登陆",null);
        if(user.getPhone() == null)
            return packResult(10002,"",null);

        try {
            String result = CommonUtil.getTMData(user.getPhone());
            if (!result.contains("billId"))
                return packResult(10003,"感谢您加入嘉兴移动，我们暂时还未获取到您的最新数据",null);
            else{
               // JSON json = JSON.parseObject(result);
               // TMData d = JSON.toJavaObject(json,TMData.class);
                return packResult(200,user.getNick(),result.replace("null" ,"0"));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return packResult(50000,"小伙伴们太热情了,活动被挤爆了",null);

    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////                            时光机抽奖模块                    ////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("raffle")
    @ResponseBody
    public synchronized Object raffle (HttpServletRequest req, String openid){

        //非法进入
        if(StringUtils.isEmpty(openid))
            return packResult(10000,"参数不全--10000",null);

        TimeMachineUser user = (TimeMachineUser) getSessionUserInfo(req,"jindUser");
        //session过期
        if (user == null)
            return packResult(10001,"认证过期，请重新登陆--10001",null);
        //篡改openid
        if (!StringUtils.equals(openid,user.getOpenid()))
            return packResult(10002,"校验失败，请重新登陆--10002",null);

        TimeMachineDetail record = TMService.queryDetailByPhone(user.getPhone());
        if (record != null)
            return packResult(20000,"您已经抽过拉...",null);

        //获取奖品剩余数量
        try {
            List<TimeMachineAward> award = TMService.query();
            Date now = new Date();
            int bd = JinDUtil.daysBetween(STARTTIME,now);
            award = doSomething(award,now,bd);

            //验证是否有剩余
            if (!isHaveAward(award))
                return packResult(30000,"来晚来...奖品被抽完啦...",null);

            TimeMachineAward a = getAward(award);

            if (a == null)
                return packResult(30000,"来晚来...奖品被抽完啦...",null);
        //    System.out.println(JSON.toJSONString(a));

            TimeMachineDetail d = new TimeMachineDetail();
            d.setOpenid(user.getOpenid());
            d.setPhone(user.getPhone());
            d.setAward(a.getAward());
            d.setCreatetime(now);
            if(TMService.insert(d) == 0)
                return packResult(30001,"小伙伴们太热情了，活动被挤爆拉--30001", null);


            a.setVersion(bd);
            a.setReceiveNum(a.getReceiveNum()+1);
            if(TMService.updateByPrimaryKey(a) == 0)
                return packResult(30001,"小伙伴们太热情了，活动被挤爆拉--30001", null);



            if (a == null)
                throw new NullPointerException();
            log.info("用户："+user.getOpenid()+"||"+ user.getPhone()+",获奖信息:"+a.getAward());
            return packResult(200,""+(a.getId()-1), JSON.toJSONString(a));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return packResult(50000,"小伙伴们太热情了，活动被挤爆拉--50000", null);
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping("queryGift")
    @ResponseBody
    public Object queryGift(HttpServletRequest req){



        TimeMachineUser user = (TimeMachineUser) getSessionUserInfo(req,"jindUser");

        int amount = TMService.queryAmount();

        if (user == null || StringUtils.isEmpty(user.getPhone()))
            return packResult(10000,""+amount,null);
        else
            return packResult(20000,""+amount,JSON.toJSONString(TMService.queryDetailByPhone(user.getPhone())));
    }

    /**
     *  验证是否有奖品剩余
     * @return
     */
    private boolean isHaveAward(List<TimeMachineAward> list){
        if (list == null)
            return false;
        else
            return true;

    }

    /**
     * 随机奖品
     * @param list
     * @return
     */
    public TimeMachineAward getAward(List<TimeMachineAward> list){
        Random rand = new Random();
       // int p = rand.nextInt(100)+1;
        int sum = 0;
        for(TimeMachineAward a : list){
            sum+=a.getAmount();
        }
        int p = rand.nextInt(sum)+1;
        int temp = 0;
        for (int i=0;i<list.size();i++){
            temp += list.get(i).getAmount();
            if (p < temp)
                return list.get(i);
        }
        return null;
    }

    /**
     * 奖品处理
     * @param list
     * @param d
     * @return
     * @throws ParseException
     */
    private  List<TimeMachineAward> doSomething (List<TimeMachineAward> list,Date d,int bd) throws ParseException{
        int index = 0;
        for (TimeMachineAward a : list){
            if (a.getId() == 8){

                if(
                        //JinDUtil.hour(d) != 20
                        //|| JinDUtil.minute(d) != 8
                //        ||
                bd - a.getVersion() < 2) { //188档次：每天8点08分,两次中奖时间间隔要大于1
                    list.remove(index);
                    break;
                }

            }else{
                index++;
                continue;
            }

        }
        return list;
    }


}
