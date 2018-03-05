package com.cyx.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.BlackListContentInfo;
import com.cyx.pojo.BlackListInfo;

public interface IBlackListService {
	int deleteByPrimaryKey(String BlackListInfoId);
	List<BlackListInfo>selectBlacklistByUserId(String userId);
    int insert(BlackListInfo record);
    List<BlackListContentInfo> selectByUserId(String userId);//��ѯ����
    int insertSelective(BlackListInfo record);
    int deleteByAuntUserId(String userId,String auntId);
    BlackListInfo selectByPrimaryKey(String BlackListInfoId);
    List<BlackListContentInfo>selectByUserIdLimit(String userId,int start,int end);
    int updateByPrimaryKeySelective(BlackListInfo record);
    int selectByUserIdLimitCount(String userId);
    int updateByPrimaryKey(BlackListInfo record);
}
