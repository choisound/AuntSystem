package com.cyx.service;

import java.util.List;

import com.cyx.pojo.SystemDataInfo;

public interface ISystemDataService {
	public int addSystemData(SystemDataInfo sdi);
	
	public int deleteSystemData(String systemdataId);
	
	public int updateSystemData(SystemDataInfo sdi);
	
	public SystemDataInfo findSystemData(String dataName);
	
	public List<SystemDataInfo> findAll();

}
