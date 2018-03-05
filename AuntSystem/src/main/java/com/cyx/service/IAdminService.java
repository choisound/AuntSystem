package com.cyx.service;

import com.cyx.pojo.AdminInfo;

public interface IAdminService {
	int deleteByPrimaryKey(String AdminInfoId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);
    AdminInfo selectByPhoneno(String phoneno);
    AdminInfo selectByPrimaryKey(String AdminInfoId);
    AdminInfo selectByPhoneAndPassword(String phoneno,String password);
    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}
