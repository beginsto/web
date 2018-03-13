package pers.jess.template.ad.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.java.emoji.EmojiConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.ad.model.AdImages;
import pers.jess.template.ad.model.AdOrganization;
import pers.jess.template.ad.model.AdUser;
import pers.jess.template.ad.service.AdService;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.model.User;
import pers.jess.template.jxwxInterface.utils.JxMpUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "ad")
public class AdController extends BaseController{

    /**
     * emoji表情符处理
     */
    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Resource
    private AdService adService;

    /**
     * 广告管理--登陆
     * @param callback
     * @param openid
     * @param req
     * @return
     */
    @RequestMapping(value = "getUserData", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getUserData(String callback, String openid, HttpServletRequest req) {

        if (StringUtils.isEmpty(openid)) {
            return packResult(callback, 10000, "参数异常", null);
        }

        Object u =  getSessionUserInfo(req, "mp_user");
        User user;

        if (u == null){
            // 获取用户信息（头像，昵称，绑定手机）
            String rs = JxMpUtil.getMpUserInfo(openid);
            if (StringUtils.isEmpty(rs)) {
                return packResult(callback, 30000, "接口异常", null);
            }
            user = JSONObject.parseObject(rs,User.class);
            setSession(req, user, "mp_user");
        }else {
            user = (User) u;
        }

        String phone = user.getPhone();
        if (StringUtils.isEmpty(phone) || phone.length() != 11){
            return packResult(callback,20001,"未关联手机",null);
        }

        AdUser adUser = adService.queryByPhone(phone);

        if (adUser == null){
            return packResult(callback,20002,"非目标用户",null);
        }

        if (StringUtils.isEmpty(adUser.getNickName()) && StringUtils.isEmpty(user.getNick())){
            adUser.setNickName(emojiConverter.toAlias(user.getNick()));
            adService.update(adUser);
        }

        return packResult(callback,20000,"success","{\"userId\":"+adUser.getId()+"}");
    }

    /**
     * 获取门店列表
     * @param callback
     * @param userId
     * @return
     */
    @RequestMapping(value = "getOrg", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getOrg(String callback, String userId){

        Pattern pattern = Pattern.compile("^-?[0-9]+");

        if (StringUtils.isEmpty(userId) ||
                !pattern.matcher(userId).matches()){
            return packResult(callback, 10000, "参数异常", null);
        }

        List<AdOrganization> list = adService.listByUserId(Integer.parseInt(userId));
        if (StringUtils.isEmpty(callback)){
            return JSON.toJSONString(list);
        }else{
            return callback + "(" + JSON.toJSONString(list) + ")";
        }

    }

    /**
     * 获取门店详情
     * @param callback
     * @param userid
     * @param orgid
     * @return
     */
    @RequestMapping(value = "getOrgInfo", produces={"text/html;charset=UTF-8;","application/json;"})
    @ResponseBody
    public Object getOrgInfo(String callback, String userid, String orgid){
        Pattern pattern = Pattern.compile("^-?[0-9]+");

        if (StringUtils.isEmpty(userid) ||
                StringUtils.isEmpty(orgid) ||
                !pattern.matcher(userid).matches() ||
                !pattern.matcher(orgid).matches()){
            return packResult(callback, 10000, "参数异常", null);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("userid",userid);
        params.put("orgid",orgid);
        List<AdImages> list = new ArrayList<>();
        String result = "";
        try {
            list  = adService.listByUserIdAndOrgId(params);
            result = JSON.toJSONString(list);
        }catch (Exception e){
            e.printStackTrace();
            result = "服务异常";
        }
        if (StringUtils.isEmpty(callback)){
            return result;
        }else{
            return callback + "(" +result + ")";
        }

    }

    /**
     * 上传文件入库
     * @param callback
     * @param record
     * @return
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public Object save(String callback,  AdImages record){
        Map<String, Object> params = new HashMap<>();
        String result = "";

        if (StringUtils.isEmpty(record.getImg()) || StringUtils.isEmpty(record.getImgpre())){
            return packResult(callback,10000,"图片不能为空",null);
        }
        if (record.getMid() == null) {
            return packResult(callback,10000,"模块不能为空",null);
        }
        if (record.getOrgid() == null) {
            return packResult(callback,10000,"门店不能为空",null);
        }
        if (record.getOrdernum() == null){
            return packResult(callback,10000,"排序不能为空",null);
        }
        params.put("userid",record.getUserid());
        params.put("orgid",record.getOrgid());
        params.put("mid",record.getMid());
        params.put("ordernum",record.getOrdernum());
        int temp = 0;
        try {
            AdImages adImages = adService.query(params);
            if (adImages != null){
                adImages.setGmtModified(new Date());
                adImages.setImg(record.getImg());
                adImages.setImgpre(record.getImgpre());
                temp = adService.update(adImages);
            }else {
                record.setState(0);
                record.setGmtCreate(new Date());
                temp = adService.insert(record);
            }
            if (temp > 0){
                result = "success";
            }else{
                result = "保存异常";
            }
        }catch (Exception e){
            e.printStackTrace();
            result = "系统异常";
        }
        if (StringUtils.isEmpty(callback)){
            return result;
        }else{
            return callback + "(" +result + ")";
        }

    }




}
