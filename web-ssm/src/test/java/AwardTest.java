import pers.jess.template.starSign.model.ConstellationAward;
import pers.jess.template.starSign.service.StarSignService;
import pers.jess.template.starSign.service.impl.StarSignServiceImpl;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AwardTest {


    /**
     * @param date
     * @return
     */
    private static String DateToString(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }


    public static void main(String[] args) {
        /*StarSignService starSignService = new StarSignServiceImpl();
        Date now = new Date();
        String[] awards = {"100M流量券", "200M流量券", "300M流量券", "500M流量券", "1GB流量券"};
        for (int i = 1; i < 150; i++) {
            for (int j = 0; j < 5; j++) {
                ConstellationAward award = new ConstellationAward();
                award.setIssue(DateToString(new Date(now.getTime() + i * 24 * 3600 * 1000)));
                award.setAward(awards[j]);
                award.setAmount(0);
                award.setVersion(0);
                starSignService.installAward(award);
                System.out.println("第"+i+"_"+j+"条记录插入完成...");
            }

        }*/
        String str = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68c88f3dca13e997&redirect_uri=http%3A%2F%2Fcard.1860.cn%2Fpublic%2Fweixin%2FweixinMob%2FunionUrl%3Fflag%3D9%26jumpMid%3Dgh_1cc642719ceb&response_type=code&scope=snsapi_base&state=gh_7181b250f86a&connect_redirect=1#wechat_redirect" ;
        try {
            str = URLDecoder.decode(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
