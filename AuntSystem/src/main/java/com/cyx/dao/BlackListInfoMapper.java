package com.cyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.BlackListContentInfo;
import com.cyx.pojo.BlackListInfo;

public interface BlackListInfoMapper {
    int deleteByPrimaryKey(String blacklistId);

    int insert(BlackListInfo record);

    int insertSelective(BlackListInfo record);
    int selectByUserIdLimitCount(String userId);
    List<BlackListContentInfo>selectByUserIdLimit(@Param("userId")String userId,@Param("start")int start,@Param("end")int end);
    BlackListInfo selectByPrimaryKey(String blacklistId);
    int deleteByAuntUserId(String userId,String auntId);
    
    int updateByPrimaryKeySelective(BlackListInfo record);

    int updateByPrimaryKey(BlackListInfo record);
    List<BlackListInfo>selectBlacklistByUserId(String userId);
	List<BlackListContentInfo> selectByUserId(String userId);
}