package pers.jess.template.gift.service;

import pers.jess.template.gift.model.GiftOnline;

public interface GiftOnlineService {


    GiftOnline queryByCode(String code);

    int updateByCode(GiftOnline record);

    int queryCount(String phone);
}
