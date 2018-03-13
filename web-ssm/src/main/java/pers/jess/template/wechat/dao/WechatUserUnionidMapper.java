package pers.jess.template.wechat.dao;

import pers.jess.template.wechat.model.WechatUserUnionid;

public interface WechatUserUnionidMapper {

    int insert(WechatUserUnionid record);

    WechatUserUnionid queryByUnionid(String unionid);

}