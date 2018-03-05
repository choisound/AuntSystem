package com.cyx.service;

import java.util.List;

import com.cyx.pojo.UserInfo;

public interface IUserService {
	int deleteByPrimaryKey(String userId);
	 int selectAllUserCount();
	 List<UserInfo>selectAllList(int start,int end);
	 int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);
    
    UserInfo selectByPhoneno(String phoneno);
    UserInfo adminselectByPrimaryKey(String userId);
    UserInfo selectByPhonenoAndPassword(String phone,String password);
    int updateByPrimaryKeySelective(UserInfo record);
    int updateByPhoneNoSelective(UserInfo user);
    int updateByPrimaryKey(UserInfo record);
}
