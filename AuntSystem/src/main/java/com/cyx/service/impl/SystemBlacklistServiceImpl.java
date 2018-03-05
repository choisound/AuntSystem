package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.SystemBlacklistInfoMapper;
import com.cyx.pojo.SystemBlackListContentInfo;
import com.cyx.pojo.SystemBlacklistInfo;
import com.cyx.service.ISystemBlacklistService;

@Service("systemBlacklistService")
public class SystemBlacklistServiceImpl implements ISystemBlacklistService {
	@Resource
	SystemBlacklistInfoMapper sbim;
	
	@Override
	public int addSystemBlacklist(SystemBlacklistInfo sbi) {
		// TODO Auto-generated method stub
		return sbim.insert(sbi);
	}

	@Override
	public int deleteSystemBlacklist(String auntId) {
		// TODO Auto-generated method stub
		return sbim.deleteByAuntId(auntId);
	}

	
	@Override
	public SystemBlacklistInfo findSystemBlacklist(String auntId) {
		// TODO Auto-generated method stub
		return sbim.selectByAuntId(auntId);
	}

	@Override
	public List<SystemBlacklistInfo> findallSystemBlacklist() {
		// TODO Auto-generated method stub
		return sbim.selectAll();
	}

	@Override
	public int selectAllCount() {
		// TODO Auto-generated method stub
		return sbim.selectAllCount();
	}

	@Override
	public List<SystemBlackListContentInfo> selectAll(int start, int end) {
		// TODO Auto-generated method stub
		return sbim.selectAll(start, end);
	}

}
