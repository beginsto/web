package pers.jess.template.gift.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jess.template.gift.dao.GiftOnlineMapper;
import pers.jess.template.gift.model.GiftOnline;
import pers.jess.template.gift.service.GiftOnlineService;


@Service("giftOnlineService")
public class GiftOnlineServiceImpl implements GiftOnlineService {

    @Autowired
    private GiftOnlineMapper giftOnlineMapper;

    @Override
    public GiftOnline queryByCode(String code){
        return giftOnlineMapper.queryByCode(code);
    }
    @Override
    public int updateByCode(GiftOnline record){
        return giftOnlineMapper.updateByCode(record);

    }

    @Override
    public  int queryCount(String phone){
        return giftOnlineMapper.queryCount(phone);
    }
}
