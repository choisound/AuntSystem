package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.BlackListInfoMapper;
import com.cyx.pojo.BlackListContentInfo;
import com.cyx.pojo.BlackListInfo;
import com.cyx.service.IBlackListService;
@Service("blackListService")
public class BlackListServiceImpl implements IBlackListService {
	@Resource
	BlackListInfoMapper blackList;
	@Override
	public int deleteByPrimaryKey(String blacklistId) {
		// TODO Auto-generated method stub
		return blackList.deleteByPrimaryKey(blacklistId);
	}
	public List<BlackListContentInfo> selectByUserId(String userId){
		return blackList.selectByUserId(userId);
	}
	@Override
	public int insert(BlackListInfo record) {
		// TODO Auto-generated method stub
		return blackList.insert(record);
	}

	@Override
	public int insertSelective(BlackListInfo record) {
		// TODO Auto-generated method stub
		return blackList.insertSelective(record);
	}

	@Override
	public BlackListInfo selectByPrimaryKey(String blacklistId) {
		// TODO Auto-generated method stub
		return blackList.selectByPrimaryKey(blacklistId);
	}

	@Override
	public int updateByPrimaryKeySelective(BlackListInfo record) {
		// TODO Auto-generated method stub
		return blackList.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BlackListInfo record) {
		// TODO Auto-generated method stub
		return blackList.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByAuntUserId(String userId, String auntId) {
		// TODO Auto-generated method stub
		return blackList.deleteByAuntUserId(userId, auntId);
	}
	@Override
	public List<BlackListContentInfo> selectByUserIdLimit(String userId,
			int start, int end) {
		// TODO Auto-generated method stub
		return blackList.selectByUserIdLimit(userId, start, end);
	}
	@Override
	public int selectByUserIdLimitCount(String userId) {
		// TODO Auto-generated method stub
		return blackList.selectByUserIdLimitCount(userId);
	}
	@Override
	public List<BlackListInfo> selectBlacklistByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
