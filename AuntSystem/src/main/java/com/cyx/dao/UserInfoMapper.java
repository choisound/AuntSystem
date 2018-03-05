package com.cyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);
    UserInfo adminselectByPrimaryKey(String userId);
    int insertSelective(UserInfo record);
    int selectAllUserCount();
    List<UserInfo>selectAllList( int start, int end);
    UserInfo selectByPrimaryKey(String userId);
    UserInfo selectByPhonenoAndPassword(String phone,String password);
    int updateByPrimaryKeySelective(UserInfo record);
    UserInfo selectByPhoneno(String phoneno);
    int updateByPrimaryKey(UserInfo record);
    int updateByPhoneNoSelective(UserInfo user);
}