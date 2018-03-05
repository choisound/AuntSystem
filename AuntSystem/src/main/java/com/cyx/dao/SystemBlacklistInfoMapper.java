package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.SystemBlackListContentInfo;
import com.cyx.pojo.SystemBlacklistInfo;

public interface SystemBlacklistInfoMapper {
    int deleteByPrimaryKey(String systemBlacklistId);

    int insert(SystemBlacklistInfo record);

    int insertSelective(SystemBlacklistInfo record);

    SystemBlacklistInfo selectByPrimaryKey(String systemBlacklistId);
   
    int updateByPrimaryKeySelective(SystemBlacklistInfo record);
    int selectAllCount();
    List<SystemBlackListContentInfo>selectAll(int start,int end);
    int updateByPrimaryKey(SystemBlacklistInfo record);

	SystemBlacklistInfo selectByAuntId(String auntId);

	int deleteByAuntId(String auntId);

	List<SystemBlacklistInfo> selectAll();
	
}