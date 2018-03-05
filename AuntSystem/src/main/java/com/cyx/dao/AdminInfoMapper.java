package com.cyx.dao;

import com.cyx.pojo.AdminInfo;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(String adminId);

    int insert(AdminInfo record);
    AdminInfo selectByPhoneAndPassword(String phoneno,String password);
    
    AdminInfo selectByPhoneno(String phoneno);
    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}