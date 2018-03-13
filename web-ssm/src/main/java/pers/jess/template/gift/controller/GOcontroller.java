package pers.jess.template.gift.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.jess.template.common.BaseController.BaseController;
import pers.jess.template.common.utils.CommonUtil;
import pers.jess.template.gift.model.GiftOnline;
import pers.jess.template.gift.service.GiftOnlineService;
import pers.jess.template.localphone.model.LocalPhone;
import pers.jess.template.localphone.service.LocalPhoneService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("gift")
public class GOcontroller extends BaseController{

    private Logger log = Logger.getLogger(GOcontroller.class);

    @Resource
    private GiftOnlineService giftOnlineService;

    @Resource
    private LocalPhoneService localPhoneService;

    @RequestMapping("index")
    public Object index(HttpServletRequest req, Model model){
        String code = req.getParameter("code");
        model.addAttribute("code",code);
        return "gift/index";
    }

    @RequestMapping("confirm")
    @ResponseBody
    public Object confirm(GiftOnline gift){
        try {
            if (gift == null )
                return packResult(404,"",null);

            GiftOnline record = giftOnlineService.queryByCode(gift.getCodeNum());
            if (record == null)
                return packResult(300,"",null);

            if (record.getIsUsed()!= null && record.getIsUsed() != 0)
                return packResult(303,"",null);


            int count = giftOnlineService.queryCount(gift.getCodedesc());
            if (count > 1)
                return packResult(304,"",null);


            record.setIsUsed(1);
            record.setCodedesc(gift.getCodedesc());
            record.setUseTime(new Date());
            int temp = giftOnlineService.updateByCode(record);
            if(temp == 1){
                if (CommonUtil.pushData(gift.getCodedesc(),gift.getCodeNum()).equals("Y")){
                    return packResult(200,"",null);
                }else{
                    log.info("兑换码活动：手机号码"+record.getCodedesc()+",券码:"+ record.getCodeNum()+"数据推送失败...");
                }
            }


            return packResult(500,"",null);
        }catch (Exception e){
            log.error("流量兑换异常，Exception［" + e.toString() + "］");
            e.printStackTrace();
            return packResult(500,"",null);
        }

    }

    @RequestMapping("validated")
    @ResponseBody
    public Object validated(HttpServletRequest req){
        String phone = req.getParameter("tel");
        //是否嘉兴移动号码
        LocalPhone lp = localPhoneService.quaryByPhone(phone.substring(0,7));
        if (lp == null)
            return packResult(304,"",null);
        else
            return packResult(200,"",null);
    }

}
