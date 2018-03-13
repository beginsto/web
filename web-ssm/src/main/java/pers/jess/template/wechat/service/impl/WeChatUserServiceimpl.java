package pers.jess.template.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pers.jess.template.wechat.dao.WechatUserUnionidMapper;
import pers.jess.template.wechat.model.WechatUserUnionid;
import pers.jess.template.wechat.service.WeChatUserService;

public class WeChatUserServiceimpl implements WeChatUserService {

    @Autowired
    private WechatUserUnionidMapper dao;

    @Override
    public int insert(WechatUserUnionid record){
        return dao.insert(record);
    }

    @Override
    public WechatUserUnionid queryByUnionid(String unionid){
        return dao.queryByUnionid(unionid);
    }
}
