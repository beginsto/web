package pers.jess.template.bindMobile.dao;

import pers.jess.template.bindMobile.model.BindMobile;

public interface BindMobileMapper {
    //int deleteByPrimaryKey(Integer id);

    //int insert(BindMobile record);

    int insertSelective(BindMobile record);

   // BindMobile selectByPrimaryKey(Integer id);
//
   // int updateByPrimaryKeySelective(BindMobile record);

    //int updateByPrimaryKey(BindMobile record);
}