package pers.jess.template.gift.dao;

import org.springframework.stereotype.Component;
import pers.jess.template.gift.model.GiftOnline;

@Component
public interface GiftOnlineMapper {
    /*int deleteByPrimaryKey(Integer id);

    int insert(GiftOnline record);

    int insertSelective(GiftOnline record);

    GiftOnline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftOnline record);

    int updateByPrimaryKey(GiftOnline record);*/

    GiftOnline queryByCode(String code);

    int updateByCode(GiftOnline record);

    int queryCount(String phone);
}