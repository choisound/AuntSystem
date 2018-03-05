package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.SystemDataInfoMapper;
import com.cyx.pojo.SystemDataInfo;
import com.cyx.service.ISystemDataService;
@Service("systemDataService")
public class SystemDataServiceimpl implements ISystemDataService {
	@Resource
	SystemDataInfoMapper sdim;
	@Override
	public int addSystemData(SystemDataInfo sdi) {
		// TODO Auto-generated method stub
		return sdim.insert(sdi);
	}

	@Override
	public int deleteSystemData(String systemdataId) {
		// TODO Auto-generated method stub
		return sdim.deleteByPrimaryKey(systemdataId);
	}

	@Override
	public int updateSystemData(SystemDataInfo sdi) {
		// TODO Auto-generated method stub
		return sdim.updateByPrimaryKey(sdi);
	}

	@Override
	public SystemDataInfo findSystemData(String dataName) {
		// TODO Auto-generated method stub
		return sdim.selectByDataName(dataName);
		
	}

	@Override
	public List<SystemDataInfo> findAll() {
		// TODO Auto-generated method stub
		return sdim.selectAll();
	}

}
