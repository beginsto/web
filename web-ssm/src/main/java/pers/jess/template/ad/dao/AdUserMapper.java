package pers.jess.template.ad.dao;

import org.springframework.stereotype.Repository;
import pers.jess.template.ad.model.AdUser;

@Repository
public interface AdUserMapper {

    /**
     * 获取用户信息
     * @param phone
     * @return
     */
    AdUser queryByPhone(String phone);

    /**
     *
     * @param user
     * @return
     */
    int update(AdUser user);

}