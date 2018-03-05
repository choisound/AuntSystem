package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.SystemShareInfo;

public interface SystemShareInfoMapper {
    int deleteByPrimaryKey(String shareInfoId);//

    int insert(SystemShareInfo record);//

    int insertSelective(SystemShareInfo record);

    SystemShareInfo selectByPrimaryKey(String shareInfoId);

    List<SystemShareInfo> selectByAdminId(String adminId);
    
    int updateByPrimaryKeySelective(SystemShareInfo record);

    int updateByPrimaryKey(SystemShareInfo record);
}