package pers.jess.template.wechat.service;

import pers.jess.template.wechat.model.WechatUserUnionid;

public interface WeChatUserService {

    int insert(WechatUserUnionid record);

    WechatUserUnionid queryByUnionid(String unionid);
}
