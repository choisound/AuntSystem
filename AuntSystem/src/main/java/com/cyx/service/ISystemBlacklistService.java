package com.cyx.service;

import java.util.List;

import com.cyx.pojo.SystemBlackListContentInfo;
import com.cyx.pojo.SystemBlacklistInfo;

public interface ISystemBlacklistService {
	public int addSystemBlacklist(SystemBlacklistInfo sbi);
	public int selectAllCount();
    public List<SystemBlackListContentInfo>selectAll(int start,int end);
	public int deleteSystemBlacklist(String auntId);
	
	public SystemBlacklistInfo findSystemBlacklist(String auntId);

	public List<SystemBlacklistInfo> findallSystemBlacklist();
}
