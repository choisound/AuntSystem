package com.cyx.service;


import java.util.List;

import com.cyx.pojo.SystemShareInfo;

public interface ISystemShareService {

	public int insert(SystemShareInfo ssi);
	
	public int deleteByPrimaryKey(String key);
	
	public List<SystemShareInfo> findByAdminId(String adminId);
	
}
