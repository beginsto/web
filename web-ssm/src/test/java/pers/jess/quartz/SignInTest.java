package pers.jess.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import pers.jess.template.signin.model.SignInAward;
import pers.jess.template.signin.model.SignInInfo;
import pers.jess.template.signin.model.SignInInvite;
import pers.jess.template.signin.model.SignInUser;
import pers.jess.template.signin.service.SignInService;
import pers.jess.template.signin.util.SignInUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

public class SignInTest {

    //size:11
    private static String[] nameArr = {"赵","钱","孙","李","周","吴","郑","王","和","秦","古"};

    private static Random rand = new Random();

    //size:15
    private static String[] headimg = { "http://wx.qlogo.cn/mmopen/EVPtJJ9TsHwy1qhryQF25O8Eh58dVZ4uagjYribwYXL3z1U9Wu7FiasmX12U89k2wZTHojJTHxUMXkkUSTiaTBAFA/0",
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

    @Autowired
    private SignInService signInService;


    public  String getNickName(){
        return nameArr[rand.nextInt(11)]+nameArr[rand.nextInt(11)]+nameArr[rand.nextInt(11)];
    }

    public  String getHeadimg(){
        return headimg[rand.nextInt(15)];
    }

    public  String getOpenid(){
        String val="";

        for(int i = 0; i < 38; i++) {
            //输出是大写字母还是小写字母
            int temp = rand.nextInt(2) % 2 == 0 ? 65 : 97;
            val += (char)(rand.nextInt(26) + temp);
        }
        return val;
    }

    public  void main(String[] args){
        Date now = new Date();
        int mobile = 10000;
        for(int i=0;i<100;i++){
            String openid = getOpenid();
            SignInUser user = new SignInUser();
            user.setOpenid(openid);
            user.setNickname(getNickName());
            user.setHeadimg(getHeadimg());
            user.setCreatetime(now);
            user.setMobile("1300573"+(mobile+i));
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

            System.out.println("用户："+openid+"数据插入完成....");

        }


    }
}
