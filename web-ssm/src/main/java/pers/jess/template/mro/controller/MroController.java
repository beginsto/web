package pers.jess.template.mro.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.java.emoji.EmojiConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.jind.util.JinDUtil;
import pers.jess.template.mro.model.MroAmount;
import pers.jess.template.mro.model.MroContent;
import pers.jess.template.mro.model.MroDetail;
import pers.jess.template.mro.service.MroService;
import pers.jess.template.signin.util.SignInUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("mro")
public class MroController extends BaseController{

    //private final String STARTTIME="20-12-26";

    private final String[] STARTTIME={"2018-01-08 17:00:01","2018-01-10 14:00:01","2018-01-12 11:00:01","2018-01-12 16:00:01","2018-01-16 17:00:01"};

    //private final String STARTTIME_TEST="2017-12-22";
    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Resource
    private MroService mroService;

    @RequestMapping("index")
    public Object toIndex(HttpServletRequest req, String openid, String token, Model model){
        //未登录用户，去授权
        /*if (StringUtils.isEmpty(openid)
                || getSessionUserInfo(req,"mrotk") == null){
            token = getToken();
            setSession(req,token,"mrotk");
            String url ="http://url.cn/5VpCp0o";
            return "redirect:" + url ;
        }*/

        ////////////////////////////////////////
        // 授权后首次进入页面将openid存入session //
        // 供后面投票时进行验证                 //
        ///////////////////////////////////////
        if (getSessionUserInfo(req,"openid") == null)
            setSession(req,openid,"openid");
        setSession(req,openid,"openid");
        model.addAttribute("tk",token);
        model.addAttribute("openid",openid);
        return "mro/index";
    }

    @RequestMapping("advice")
    public Object advice(HttpServletRequest req,String pid,String openid, String issue ,Model model){
        model.addAttribute("pid",pid);
        model.addAttribute("openid",openid);
        model.addAttribute("issue",issue);
        return "mro/advice";
    }

    @RequestMapping("vote")
    @ResponseBody
    public synchronized Object vote(HttpServletRequest req,String openid,String tk,String areaId,String issue){


        if (!StringUtils.isEmpty(openid)
                && !StringUtils.equals(openid,getSessionUserInfo(req,"openid")==null?"":(String)getSessionUserInfo(req,"openid")))
            return packResult(300,"认证过期，请重新登陆--10000",null);



        if (StringUtils.isEmpty(openid)
                || StringUtils.isEmpty(areaId)
                || StringUtils.isEmpty(issue))
            return packResult(300,"参数异常，请尝试重新登陆--10001",null);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("openid",openid);
            map.put("issue",issue);
            map.put("pid",areaId);
            MroDetail d = mroService.queryByOpenidAndIssue(map);
            int temp=0;
            if (d == null){
                d = new MroDetail();
                d.setOpenid(openid);
                d.setIssue(Integer.parseInt(issue));
                d.setPid(Integer.parseInt(areaId));
                d.setCreatetime(new Date());

                temp = mroService.insert(d);
            }else{
                if (d.getEvaluate() == null || d.getEvaluate() != 3)
                    return packResult(300,"20000",null);
                else{//先吐槽用户
                    d.setEvaluate(4);
                    temp = mroService.updateByPrimaryKey(d);
                }
            }


            if (temp == 0)
                return packResult(300,"小伙伴们太热情，服务器被挤爆了--30001",null);

            MroAmount a = mroService.queryByPid(Integer.parseInt(areaId));
            if (a == null){
                a = new MroAmount();
                a.setAmount(1);
                a.setPid(Integer.parseInt(areaId));
                temp = mroService.insert(a);
                if (temp == 0)
                    return packResult(300,"小伙伴们太热情，服务器被挤爆了--30001",null);
            }else{
                a.setAmount(a.getAmount()+1);
                temp=mroService.updateByPrimaryKey(a);
                if (temp == 0)
                    return packResult(300,"小伙伴们太热情，服务器被挤爆了--30001",null);
            }

           // req.getSession().removeAttribute("mrotk");
            return packResult(200,"",null);
        }catch (Exception e){
            e.printStackTrace();
            //setSession(req,tk,"mrotk");
        }

        return packResult(500,"小伙伴们太热情，服务器被挤爆了--50000",null);
    }

    @RequestMapping("evaluate")
    @ResponseBody
    public Object evaluate(HttpServletRequest req, String openid, String areaId, String issue, String ev){

        if (!StringUtils.isEmpty(openid)
                && !StringUtils.equals(openid,getSessionUserInfo(req,"openid")==null?"":(String)getSessionUserInfo(req,"openid")))
            return packResult(300,"认证过期，请重新登陆--10000",null);


        if (StringUtils.isEmpty(openid)
                || StringUtils.isEmpty(areaId)
                || StringUtils.isEmpty(issue)
                || StringUtils.isEmpty(ev))
            return packResult(300,"参数异常，请尝试重新登陆--10001",null);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("openid",openid);
            map.put("issue",issue);
            map.put("pid",areaId);
            MroDetail d = mroService.queryByOpenidAndIssue(map);
            if (d == null || (d.getEvaluate() != null && d.getEvaluate() == 3))
                return packResult(300,"请先帮我们投票哦~",null);
            if (d.getPid() != Integer.parseInt(areaId))
                return packResult(300,"只可在当前投票的单位进行满意度评价哦~",null);

            if (d.getEvaluate() != null && d.getEvaluate() != 4)// 重复
                return packResult(300,"20000",null);

            if (d.getEvaluate() == null || d.getEvaluate() != 4)
                d.setEvaluate(Integer.parseInt(ev));
            else
                d.setEvaluate(4+1+Integer.parseInt(ev));


            int temp = mroService.updateByPrimaryKey(d);

            if (temp >0 ){
                return packResult(200,"",null);
            }else{
                return packResult(300,"小伙伴们太热情，服务器被挤爆了--30001",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return packResult(500,"小伙伴们太热情，服务器被挤爆了--50000",null);
        }
    }

    @RequestMapping("commit")
    @ResponseBody
    public Object commit(HttpServletRequest req, String openid, String issue, String advice,String pid){
        if(StringUtils.isEmpty(openid)
                || !StringUtils.equals(openid,getSessionUserInfo(req,"openid")==null?"":(String)getSessionUserInfo(req,"openid")))
            return packResult(300,"认证过期，请重新登陆--10000",null);

        if ( StringUtils.isEmpty(issue))
            return packResult(300,"参数异常，请尝试重新登陆--10001",null);
        if (StringUtils.isEmpty(advice))
            return packResult(300,"意见或者建议不能为空",null);

        try {
         //   EmojiConverter emojiConverter = EmojiConverter.getInstance();
            Map<String, Object> map = new HashMap<>();
            map.put("openid",openid);
            map.put("issue",issue);
            map.put("pid",pid);
            MroDetail d = mroService.queryByOpenidAndIssue(map);

            if (d == null){
                d = new MroDetail();
                d.setOpenid(openid);
                d.setIssue(Integer.parseInt(issue));
                d.setPid(Integer.parseInt(pid));
                d.setAdvice(emojiConverter.toAlias(advice));
                d.setCreatetime(new Date());
                d.setEvaluate(3);
                mroService.insert(d);
               // return packResult(300,"请先投票....",null);
            }else{
                if (d.getAdvice()!= null)
                    return packResult(300,"20000",null);
                else {
                    d.setAdvice(this.emojiConverter.toAlias(advice));
                    mroService.updateByPrimaryKey(d);
                }
            }



            return packResult(200,"",null);
        }catch (Exception e){
            e.printStackTrace();
            return packResult(500,"小伙伴们太热情，服务器被挤爆了--50000",null);
        }

    }


    @RequestMapping("getData")
    @ResponseBody
    public Object getData(String openid) throws ParseException {
        Date now = new Date();
        int issue = 0;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i =0; i < STARTTIME.length;i++){
            if (now.before(sf.parse(STARTTIME[i]))){
                issue = 3 + i;
                break;
            }
        }
        issue= issue==3?0:issue;
        issue = 8;
        //Date stime = Constellation
       // =  JinDUtil.daysBetween(STARTTIME, new Date())/4 + 1;
        List<MroContent> mroc = mroService.query();
        List<MroContent> nl = new ArrayList<>();
        JSONObject j = new JSONObject();
        if (mroc != null){
            for(MroContent m : mroc){
                if (m.getIssue() == issue){
                    nl.add(m);
                }
            }
        }
        if (nl.size()>0)
            j.put("list",JSON.toJSONString(nl));


        if (mroc != null && !StringUtils.isEmpty(openid)){
           // Integer issue = mroc.get(0).getIssue();
            Map<String , Object> map = new HashMap<>();
            map.put("openid",openid);
            map.put("issue",issue);
            List<MroDetail> ds = mroService.queryList(map);
            String pid="",evaluate="";
            if (ds.size() > 0 ){
                for (int i=0;i<ds.size();i++){
                    pid+=ds.get(i).getPid()+",";
                    if (ds.get(i).getEvaluate() == null || ds.get(i).getEvaluate() == 3 || ds.get(i).getEvaluate() == 4)
                        evaluate+=null+",";
                    else if(ds.get(i).getEvaluate() > 4)
                        evaluate+=ds.get(i).getEvaluate() - 5 +",";
                    else
                        evaluate+=ds.get(i).getEvaluate()+",";
                }
                j.put("pid",pid.substring(0,pid.length()-1));
                j.put("evaluate",evaluate.substring(0,evaluate.length()-1));
            }
        }

        return packResult(200,"", j.toJSONString());
    }


    @RequestMapping("getAdviceData")
    @ResponseBody
    public Object getAdviceData(String pid){
        if (StringUtils.isEmpty(pid))
            return packResult(300,"",null);

        MroContent c = mroService.selectByPrimaryKey(Integer.parseInt(pid));
        return packResult(200,"",JSON.toJSONString(c));
    }

    /**
     * 生成token
     * 防止刷票行为
     * @return
     */
    private String getToken(){
        return new Md5Hash(System.currentTimeMillis() + "mro" + (new Random().nextInt(89999)+10000)).toHex();
    }



}
