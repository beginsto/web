package pers.jess.template.jxwxInterface.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.github.binarywang.java.emoji.EmojiConverter;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.httpclient.HttpClient;
import pers.jess.template.common.model.User;
import pers.jess.template.common.utils.*;
import pers.jess.template.jxwxInterface.model.*;
import pers.jess.template.jxwxInterface.service.JxwxService;
import pers.jess.template.jxwxInterface.utils.*;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;
import pers.jess.template.prize.model.Prize;
import pers.jess.template.prize.service.PrizeService;
import pers.jess.template.register.model.RegisterInfo;
import pers.jess.template.register.service.RegisterService;
import pers.jess.template.signin.util.SignInUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Controller
@RequestMapping("interface")
public class JxwxInterfaceController extends BaseController {

    private EmojiConverter emojiConverter = EmojiConverter.getInstance();


    @Resource
    private JxwxService jxwxService;

    @Resource
    private LocalPhoneService localPhoneService;

    @Resource
    private RegisterService registerService;




    /**
     * 问卷答题提交
     * @param req
     * @param callback
     * @param mpi
     * @return
     */
    @RequestMapping("mpicommit")
    @ResponseBody
    public Object mpicommit(HttpServletRequest req, String callback, MPInvestigate mpi){

        if (mpi == null || StringUtils.isEmpty(mpi.getPhone())
                || StringUtils.isEmpty(mpi.getAnswer())
                || StringUtils.isEmpty(mpi.getMpname())
                || StringUtils.isEmpty(mpi.getRemark()))
            return packResult(callback,10000,"",null);
        try {
            MPInvestigate record = jxwxService.queryByPhone(mpi.getPhone());
            if (record != null)
                return packResult(callback,20001,"请勿重复提交",null);


            String an[] = mpi.getAnswer().split(",");
            if (an.length < 9)
                return packResult(callback,10000,"",null);

            String[][] answer={{"15及一下","16-23","24-30","31-40","41及以上"},
                    {"男","女"},
                    {"1年内","1-2年","2-4年","4年以上"},
                    {"还未下载","1年","1-2年","2-4年","4年以上"},
                    {"8点-11点","11点-13点","13点-16点","16点-19点","19点-22点","22点以后"},
                    {"本地民生类","政治新闻类","热门活动类","生活常识类"},
                    {"非常不满意","不满意","没感觉","满意","非常满意"},
                    {"每月一次","每月2-3次","每月4次以上","越多越好"},
                    {"电影票","视频会员","购物优惠券"}};

            StringBuilder sb = new StringBuilder();

            //先处理第五题多选
            String[] an_f = mpi.getRemark()==null?null:mpi.getRemark().split(",");
            String an_f_temp ="";
            for (int j = 0; j< an_f.length;j++){
                an_f_temp += answer[4][MPInvestigateUtil.answerMapping(an_f[j])]+",";
            }

            String remark = mpi.getAnswer()+","+mpi.getRemark();
            int  answer_temp = 0;
            for (int i=0;i < 8;i++){
                answer_temp = MPInvestigateUtil.answerMapping(an[i]);
                if(i == 4){
                    sb.append("(").append(an_f_temp).append("),");
                    continue;
                }
                if (answer_temp == -1)
                    return packResult(callback,10000,"",null);
                else
                    sb.append(answer[i][answer_temp]).append(",");
            }

            if (StringUtils.equalsAny(an[8],"A","B","C")){
                answer_temp = MPInvestigateUtil.answerMapping(an[8]);
                if (answer_temp == -1)
                    return packResult(callback,10000,"",null);
                else
                    sb.append(answer[8][answer_temp]);
            }else{
                sb.append(an[8]);
            }


            mpi.setRemark(remark);
            mpi.setAnswer(new String(sb));
            mpi.setCretime(new Date());

            mpi.setAdvice(emojiConverter.toAlias(mpi.getAdvice()));
            int temp = jxwxService.insert(mpi);
            if (temp == 0)
                return packResult(callback,30000,"",null);
            else
                return packResult(callback,20000,"",null);
        }catch (Exception e){
            e.printStackTrace();
            log.error("问卷答题提交接口异常，提交内容：【"+JSON.toJSONString(mpi)+"】，Exception：【"+ e.toString() +"】");
        }
        return packResult(callback,50000,"",null);
    }

    /**
     * 问卷答题获取用户提交信息
     * @param callback
     * @param phone
     * @return
     */
    @RequestMapping(value = "getData",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getData(String callback, String phone){
        if (StringUtils.isEmpty(phone))
            return packResult(callback,10000,"手机号码不能为空",null);
        try {
            MPInvestigate record = jxwxService.queryByPhone(phone);
            if (record == null)
                return packResult(callback,20000,"",null);
            else{
                String str = record.getRemark();
                String[]  an = str.split(",");
                JSONObject json = new JSONObject();
                String an_5="";
                for (int i = 0; i < an.length;i++){
                    if (i < 9){
                        json.put("an_"+(i+1),an[i]);
                        continue;
                    }
                    an_5 +=an[i]+",";

                }
                json.put("an_5",an_5.substring(0,an_5.length()-1));
                json.put("an_10",record.getMpname());
                json.put("advice",emojiConverter.toUnicode(record.getAdvice()));
                return packResult(callback,20000,"",json.toJSONString());
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("问卷答题获取用户提交信息接口异常，Exception：【"+ e.toString() +"】");
            return packResult(callback,50000,"",null);
        }


    }

    /**
     * 获取订阅号用户基本信息以及关联号码
     * @param req
     * @param callback
     * @param openid
     * @param toUserName
     * @return
     */
    @RequestMapping(value = "getMpUserData") // produces={"text/html;charset=UTF-8;","application/json;"}
    @ResponseBody
    public Object getMpUserData(HttpServletRequest req, String callback, String openid, String toUserName){
        if(StringUtils.isEmpty(openid)
                //|| !StringUtils.equals(URLManagerUtils.TOUSERNAME,toUserName)
                )
            return packResult(callback,10000,"",null);
        try {
            Object u =  getSessionUserInfo(req, "mp_user");
            if (u == null){
                String result = GetWeChatUserInfo.getUserInfo(openid.trim());
                log.info(result);
                if (StringUtils.isEmpty(result))
                    return packResult(callback,30000,"mp接口异常",null);
                JSONObject rs = JSONObject.parseObject(result);
                if (String.valueOf(rs.get("resultCode")).equals("300"))
                    return packResult(callback,20001,"未查询到信息，请确认openid", null);
                String content = rs.getString("resultContent");
                User user = JSONObject.parseObject(content,User.class);
                setSession(req,user,"mp_user");
                return packResult(callback,20000,"", content);
            }else{
                return packResult(callback,20000,"", JSON.toJSONString((User)u));
            }

        }catch (Exception e){
            e.printStackTrace();
            log.error("获取订阅号用户基本信息接口异常，Exception：【"+ e.toString() +"】");
            return packResult(callback,50000,"系统异常",null);
        }


    }

    /**
     *  关联手机号码
     * @param phone
     * @param openid
     * @param toUserName
     * @return
     */
    @RequestMapping(value = "bindPhone")
    @ResponseBody
    public Object bindPhone(HttpServletRequest req, String phone, String openid, String toUserName,String callback){
        if(StringUtils.isEmpty(phone)
                || StringUtils.isEmpty(openid)
                || !StringUtils.equals(URLManagerUtils.TOUSERNAME,toUserName))
            return packResult(callback,10000,"",null);

        try {
            HttpClient client = new HttpClient();
            Map<String, Object> map = new HashMap<>();
            map.put("openId",openid);
            map.put("phone",phone);

            String rs = client.doPost(URLManagerUtils.URL_BINDPHONE,map);
            if(StringUtils.isEmpty(rs))
                return packResult(callback,30000,"接口异常", null);
            JSONObject json = JSONObject.parseObject(rs);
            if(json != null && String.valueOf(json.get("resultCode")).equals("200")) {
                User u =  (User) getSessionUserInfo(req, "mp_user");
                if (u != null)
                    u.setPhone(phone);
                return packResult(callback,20000,"", null);
            }else
                return packResult(callback,20001,"请确认openid", null);
        }catch (Exception e){
            e.printStackTrace();
            log.error("关联号码接口异常，Exception：【"+ e.toString() +"】");
            return packResult(callback,50000,"系统异常",null);
        }
    }

    /**
     * 宽带投诉登记查询
     * @param phone
     * @return
     */
    @RequestMapping(value = "querykdts")
    @ResponseBody
    public Object querykdts(String callback, String phone,String regphone){
        if(StringUtils.isEmpty(phone) || phone.length() != 11 || StringUtils.isEmpty(regphone) || regphone.length() != 11)
            return packResult(callback, 10000,"",null);

        LocalPhone localPhone = localPhoneService.quaryByPhone(regphone.substring(0,7));
        if (localPhone == null)
            return packResult(callback, 20000,"non-target",null);


        Map<String, Object> param = new HashMap<>();
        param.put("mobile",regphone);
        param.put("source","kdts");

        RegisterInfo info = registerService.queryByParam(param);
        if (info == null){
            info = new RegisterInfo();
            info.setMobile(regphone);
            info.setReserveone(phone);
            info.setSource("kdts");
            info.setCreatetime(new Date());
            String rs = CommonUtil.querykdts(phone,regphone);
            registerService.insertData(info);
            return packResult(callback, 20000,"success",null);
        }else{
            Date now = new Date();
            Date regtime = info.getCreatetime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(regtime);
            long t1 = cal.getTimeInMillis();
            cal.setTime(now);
            long t2 = cal.getTimeInMillis();
            int bttime = Integer.parseInt(String.valueOf((t2 - t1) / 86400000L));

           if (bttime > 30){
                info.setCreatetime(now);
                registerService.updateByPrimaryKey(info);
               return packResult(callback, 20000,"success",null);
           }else{
               return packResult(callback, 20000,"repeat",null);
           }
        }
    }


    /**********************************************
     * 春节7天派红包活动
     * 每天通过输入口令随机奖品 每天限量1W份
     * 奖品列表        份数     概率
     * 1元话费        2000份    20%
     * 2元话费        1000份    10%
     * 100M流量券     3000份    30%
     * 200M流量券     2000份    20%
     * 500M流量券     2000份    20%
     **********************************************/


    /**
     * 用户信息（手机号码，昵称，头像，是否抽奖，中奖列表，当期已领奖人数）
     * @param req
     * @param callback
     * @param openid
     * @param toUserName
     * @return
     */
    @RequestMapping(value = "getSFInfo",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getInfo(HttpServletRequest req, String callback, String openid, String toUserName ){
        if (StringUtils.isEmpty(openid) || !StringUtils.equals(URLManagerUtils.TOUSERNAME,toUserName)) {
            return packResult(callback, 10001, "", null);
        }
        try {
            // 获取用户信息（头像，昵称，绑定手机）
            String rs = JxMpUtil.getMpUserInfo(openid);
            if (StringUtils.isEmpty(rs)) {
                return packResult(callback, 30001, "" , null);
            }

            JSONObject result = JSON.parseObject(rs);
            String phone = String.valueOf(result.get("phone"));

            // 本期已抽奖人数
            Date now = new Date();
            result.put("amount",jxwxService.queryAmount(Integer.parseInt(JxMpUtil.DateToString(now))));

            // 未绑定号码
            if (StringUtils.isEmpty(phone) || phone.length() != 11){
                return packResult(callback,20000,"",result.toJSONString());
            }

            // 非嘉兴移动号码
            if (!JxMpUtil.isLocal(phone, localPhoneService)) {
                result.put("isLocal","N");
                return packResult(callback,20000,"",result.toJSONString());
            }else{
                result.put("isLocal","Y");
            }

            // 获取已获奖品
            List<SpringFestivalInfo> list = jxwxService.listInfo(phone);

            result.put("list",JSON.toJSONString(list));

            // 判断当期是否抽奖
            result.put("isRaffle","N");


            for (SpringFestivalInfo info : list){
                if (StringUtils.equals(info.getPeriod().toString(),JxMpUtil.DateToString(now))){
                    result.put("isRaffle","Y");
                }
            }

            return packResult(callback,20000,"",result.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return packResult(callback,30000,"",null);
    }

    /**
     * 春节7天红包
     * 抽奖接口
     *
     * @param req
     * @param callback
     * @param phone 手机号码
     * @param code 口令
     * @return
     */
    @RequestMapping(value = "sfRaffle", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public synchronized Object sfRaffle(HttpServletRequest req, String callback, String phone, String code, String openid){
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)){
            return packResult(callback, 10001,"",null);
        }

        try {
            // 通过口令获得对应的期数
            int period = SpringFestivalUtil.periodMapping(code);

            // 口令有误
            Date now  = new Date();
            if (period < 0 || period !=  Integer.parseInt(JxMpUtil.DateToString(now))){
                return packResult(callback, 20000,"-1",null);
            }

            // 获取已获奖品
            List<SpringFestivalInfo> list = jxwxService.listInfo(phone);

            for (SpringFestivalInfo info : list){
                if (StringUtils.equals(info.getPeriod().toString(),JxMpUtil.DateToString(now))){
                    return packResult(callback, 20000,"-2",null);// 当期重复抽奖
                }
            }
            // 当期对应奖品
            List<SpringFestivalAward> awards = jxwxService.listAwards(period);

            List<SpringFestivalAward> newawards = new ArrayList<>();

            // 剔除已抽完奖品
            for (SpringFestivalAward award : awards){
                if (award.getNum() < award.getAmount()){
                    newawards.add(award);
                }
            }
            // 当期已抽完
            if (newawards.size() < 1){
                return packResult(callback, 20000,"-3",null);
            }


            // 未抽完奖品中随机一个
            Random random = new Random();
            SpringFestivalAward a = newawards.get(random.nextInt(newawards.size()));
            a.setNum(a.getNum()+1);
            a.setVersion(a.getVersion()+1);
            jxwxService.updateAward(a);

            SpringFestivalInfo info = new SpringFestivalInfo();
            info.setPhone(phone);
            info.setAward(a.getAward());
            info.setGmtCreate(now);
            info.setPeriod(period);
            info.setOpenid(openid);

            jxwxService.insert(info);

            log.info("手机号码："+ phone + ", openid："+ openid + ", 奖品："+ a.getAward());

            return packResult(callback, 20000,""+(a.getId() - a.getId()/6 * 6),null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return packResult(callback, 30000,"",null);

    }

    /**
     * 宽带故障登记
     * @param broadbandFault
     * @return
     */
    @RequestMapping(value = "kdf",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object broadbandFault(BroadbandFault broadbandFault,HttpServletRequest req, String callback){
        if (StringUtils.isEmpty(broadbandFault.getPhone()) || broadbandFault.getPhone().length() != 11){
            return packResult(callback,10000,"手机号码有误！",null);
        }

        if (StringUtils.isEmpty(broadbandFault.getAddr())){
            return packResult(callback,10000,"详细地址为空！",null);
        }

        if (StringUtils.isEmpty(broadbandFault.getArea())){
            return packResult(callback,10000,"区域为空！",null);
        }

        if (StringUtils.isEmpty(broadbandFault.getFaultDesc())){
            return packResult(callback,10000,"故障描述为空！",null);
        }

        broadbandFault.setGmtCreate(new Date());
        try {
            int temp = jxwxService.insert(broadbandFault);
            if (temp > 0){
                return packResult(callback,20000,"",null);
            }else {
                return packResult(callback,30000,"",null);
            }

        }catch (Exception e){
            return packResult(callback,50000,"",null);
        }

    }

    @RequestMapping(value = "randomPhone")
    @ResponseBody
    public Object randomPhone(){
        for (int i = 0; i < 800; i++){
            LiangPhone liangPhone = new LiangPhone();
            liangPhone.setPhone(StringRandomUtil.numberRandom(11));
            liangPhone.setIsUsed(0);
            jxwxService.insert(liangPhone);
        }
        return null;
    }

    /**
     * 获取靓号列表
     * @param callback
     * @return
     */
    @RequestMapping(value = "list", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object list(String callback){
        Date now = new Date();
        try {
            JSONObject json = new JSONObject();
            json.put("list",JSON.toJSON(jxwxService.listByVersion(LiangUtil.getVersion(now))));
            json.put("next",JSON.toJSON(jxwxService.listByVersion(LiangUtil.getNext(now))));
            return callback+"("+json.toJSONString()+")";
        }catch (Exception e){
            e.printStackTrace();
            return callback + "(\"msg\":\"服务异常\")";
        }

    }

    /**
     * 靓号登记（身份证 手机号码 靓号id 营业厅）
     * @param liangInfo
     * @param callback
     * @return
     */
    @RequestMapping(value = "lcommit", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public synchronized Object lcommit(LiangInfo liangInfo, String callback){

        if (StringUtils.isEmpty(liangInfo.getOrgName())){
            return packResult(callback, 10000,"营业厅不能为空",null);
        }
        if (StringUtils.isEmpty(liangInfo.getIdCardNo()) || !IdcardValidator.isValidatedAllIdcard(liangInfo.getIdCardNo())){
            return packResult(callback,10000,"身份证号码有误...",null);
        }
        if (liangInfo.getMid() == null){
            return packResult(callback, 10000,"请选择靓号",null);
        }
        if (StringUtils.isEmpty(liangInfo.getPhone()) || liangInfo.getPhone().length() != 11){
            return packResult(callback,10000,"手机号码有误...",null);

        }
        Date now = new Date();
        try {
            int version = Integer.parseInt(LiangUtil.DateToString(now));
            Map<String, Integer> param = new HashMap<>();
            param.put("id",liangInfo.getMid());
            param.put("version",version);
            LiangPhone liangPhone = jxwxService.queryById(param);

            if (liangPhone != null){
                if (liangPhone.getIsUsed() != 0){
                    return packResult(callback, 20001,"此号码已被领取",null);
                }else{
                    LiangInfo info = jxwxService.queryByIdCard(liangInfo.getIdCardNo());

                    if (info != null){

                        return packResult(callback, 20002,"每个身份证只能登记一个号码",null);
                    }else {
                        int temp = jxwxService.updateSetUsed(liangInfo.getMid());

                        if (temp > 0){
                            liangInfo.setGmtCreate(new Date());
                            jxwxService.insert(liangInfo);
                            return packResult(callback,20000,"",null);
                        }else{
                            return packResult(callback,30000,"参数异常",null);
                        }
                    }
                }

            }else{
                return packResult(callback,30000,"非法参数",null);
            }


        }catch (Exception e){
            e.printStackTrace();
            return packResult(callback,50000,"服务异常",null);
        }


    }

    @RequestMapping(value = "liangp", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getLiangPhone(String callback, String phone){
        return callback + "(" + JSON.toJSONString(jxwxService.queryLiangByPhone(phone)) +")";
    }


    /**
     * 奖品服务类
     */
    @Resource
    private PrizeService prizeService;

    /**
     * 答题享好礼--活动页面
     * @param req
     * @param callback
     * @param openid
     * @param phone
     * @return
     */
    @RequestMapping(value = "getQAInfo",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getQAInfo(HttpServletRequest req, String callback, String openid, String phone ){
        // 参数异常
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(phone) || phone.length() != 11) {
            return packResult(callback, 10001, "", null);
        }
        // 日期版本：20180404
        Date now = new Date();
        String version = JxMpUtil.DateToString(now);
        JSONObject json = null;
        JSONArray arr = new JSONArray();
        try {
            // 当天答题记录
            List<QuestionAnswerInfo> infos = jxwxService.queryQAByPhone(phone);
            int index = 0;
            // 当天答题次数
            int current_count = 0;
            if (infos != null){
                while (index < infos.size()){
                    // 组装用户活动期间获奖内容
                    if (infos.get(index).getPrizeid() != null){
                        json = new JSONObject();
                        json.put("prizeid",infos.get(index).getPrizeid());
                        json.put("gmtCreate",infos.get(index).getGmtCreate());
                        arr.add(json);
                    }
                    if (infos.get(index).getPeriod() == Integer.parseInt(version)){
                        current_count++;
                        if (infos.get(index).getPrizeid() != null){
                            current_count = 3;
                        }
                    }
                    index++;
                }
            }
            //  当天还可以答题（从未答题，错误答题一次,0/1）
            if (current_count < 2){

                // 分享成功才能使用的第二次机会
                if (current_count > 0){
                    Map<String, Object> param = new HashMap<>();
                    param.put("mobile",phone);
                    param.put("source","qa"+version);

                    RegisterInfo info = registerService.queryByParam(param);
                    // 未分享
                    if (info == null){
                        return packResult(callback,20003,""+current_count, JSON.toJSONString(arr));
                    }
                }

                // session中获取题目
                Object sess = getSessionUserInfo(req,"user_"+phone);
                String data = "";
                // session中不存在，随机三题并存入session
                if (sess == null){
                    // 题目总量
                    int count = jxwxService.queryQACount();
                    int one_third = count/3;
                    int temp = 1 + (int) (Math.random() * one_third);
                    List<QuestionAnswer> qal = new ArrayList<>();
                    qal.add(jxwxService.queryQAById(temp));
                    temp+= (1 + (int) (Math.random() * one_third));
                    qal.add(jxwxService.queryQAById(temp));
                    temp+= (1 + (int) (Math.random() * one_third));
                    qal.add(jxwxService.queryQAById(temp));
                    data = JSON.toJSONString(qal);
                    setSession(req, data, "user_"+phone);
                }else{
                    data = String.valueOf(sess);
                }
                json = new JSONObject();
                json.put("question",data);
                json.put("prize",JSON.toJSONString(arr));
                return packResult(callback,20000,""+current_count,json.toJSONString());
            }else if (current_count == 2){
                // 当天抽奖次数已用完且都答错
                return packResult(callback,20001,""+current_count,JSON.toJSONString(arr));
            }else {
                // 当天已领奖，不返回实际抽奖次数
                return packResult(callback,20002,null,JSON.toJSONString(arr));
            }




        }catch (Exception e){
            e.printStackTrace();
        }
        return packResult(callback,30000,"",null);
    }

    /**
     * 答题享好礼--答题完毕提交页面
     * @param req
     * @param callback
     * @param openid
     * @param phone
     * @param answer 示例：A,B,C
     * @return
     */
    @RequestMapping(value = "commitQA",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public synchronized Object commitQA(HttpServletRequest req, String callback, String openid, String phone, String answer){
        if (StringUtils.isEmpty(callback) ||
                StringUtils.isEmpty(phone) ||
                StringUtils.isEmpty(answer) ||
                answer.length() != 5 ||
                QuestionAnswerUtils.appearNumber(answer,",") != 2){
            return packResult(callback,10000,null,null);
        }
        Date now = new Date();
        String version = JxMpUtil.DateToString(now);
        String[]  ans = answer.split(",");
        // session获取题目信息
        Object sess = getSessionUserInfo(req,"user_"+phone);
        req.getSession().removeAttribute("user_"+phone);
        if (sess == null){
            return packResult(callback,10001,null,null);
        }
        try {

            // json化
            List<QuestionAnswer> list = JSONArray.parseArray(String.valueOf(sess), QuestionAnswer.class);
            int temp = 0;
            String question= "";
            int rignt_count=0;
            // 题目与答案匹配
            while (temp < list.size()){
                if (list.get(temp).getAnswerRight().equals(ans[temp])){
                    rignt_count++;
                }
                question+="," + list.get(temp).getId();
                temp++;
            }
            //
            QuestionAnswerInfo info = new QuestionAnswerInfo(null,
                    phone,
                    openid,
                    null,
                    new Date(),
                    question.substring(1),
                    answer,
                    Integer.parseInt(version));
            // 3题全对才能抽奖
            if (rignt_count != 3){
                jxwxService.insertQA(info);
                return packResult(callback,20002,null,null);
            }else{
                List<Prize> plist = prizeService.list("qa");
                List<Prize> nlist = new ArrayList<>();
                // 过滤已抽完奖品
                for(Prize p : plist){
                    if (p.getReceivedNumber() < p.getAmount()){
                        nlist.add(p);
                    }
                }
                // 随机一个奖品
                int prizeId = (int) (Math.random() * nlist.size());
                Prize tar =  nlist.get(prizeId);
                // 更新奖品库存
                tar.setReceivedNumber(tar.getReceivedNumber()+1);
                tar.setVersion(tar.getVersion()+1);
                int update = prizeService.updateById(tar);
                if (update < 1){
                    return packResult(callback,20001,null,null);
                }
                // 信息入库
                info.setPrizeid(tar.getPrizeid());
                jxwxService.insertQA(info);
                return packResult(callback,20000,""+tar.getPrizeid(),null);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return packResult(callback,30000,"",null);
    }

    /**
     * 校园优惠活动登记页面--获取用户已登记信息
     * @param req
     * @param phone
     * @param callback
     * @return
     */
    @RequestMapping( value = "schooleSale",produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public String schooleSale(HttpServletRequest req, String phone, String callback){
        if (StringUtils.isEmpty(phone)){
            return packResult(callback,10000,null,null);
        }
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("mobile",phone);
            param.put("source","schooleSale");
            RegisterInfo registerInfo = registerService.queryByParam(param);
            if (registerInfo != null){
                SchoolSaleBean schoolSaleBean = new SchoolSaleBean(phone,
                        registerInfo.getReserveone().substring(0,registerInfo.getReserveone().indexOf(",")),
                        registerInfo.getReserveone().substring(registerInfo.getReserveone().indexOf(",")+1),
                        registerInfo.getReservetwo().substring(registerInfo.getReservetwo().indexOf(",")+1),
                        registerInfo.getReservetwo().substring(0,registerInfo.getReservetwo().indexOf(",")),
                        null,
                        0);
                return packResult(callback,20000,null,JSON.toJSONString(schoolSaleBean));
            }else{
                return packResult(callback,20001,null,null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return packResult(callback,30000,null,null);
        }

    }

}
