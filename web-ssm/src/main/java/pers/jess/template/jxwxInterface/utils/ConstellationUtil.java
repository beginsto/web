package pers.jess.template.jxwxInterface.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import pers.jess.template.common.utils.CommonUtil;

import java.util.Date;

public class ConstellationUtil {

    private static Logger log = Logger.getLogger(ConstellationUtil.class);


    /**
     *
     * @param zodiac
     * @param date
     * @return
     */
    public static boolean isConstellation(String zodiac, Date date){
        try {
            String constellation_issue = CommonUtil.getConstellation(date);
            if (StringUtils.isEmpty(zodiac) && zodiac.equals(constellation_issue))
                return true;
            else
                return false;
        }catch (Exception e){
            log.error("判断星座接口异常，exception：［" + e.toString() + "］");
            e.printStackTrace();
            return false;
        }


    }
}
