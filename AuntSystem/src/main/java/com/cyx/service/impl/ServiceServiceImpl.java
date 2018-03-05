package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.cyx.dao.ServiceInfoMapper;
import com.cyx.pojo.ServiceInfo;
import com.cyx.service.IServiceService;
@org.springframework.stereotype.Service("serviceService")
public class ServiceServiceImpl implements IServiceService {
	@Resource
	ServiceInfoMapper serviceMapper;
	@Override
	public int deleteByPrimaryKey(String serviceId) {
		// TODO Auto-generated method stub
		return serviceMapper.deleteByPrimaryKey(serviceId);
	}

	@Override
	public int insert(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.insert(record);
	}

	@Override
	public int insertSelective(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.insertSelective(record);
	}

	@Override
	public ServiceInfo selectByPrimaryKey(String serviceId) {
		// TODO Auto-generated method stub
		return serviceMapper.selectByPrimaryKey(serviceId);
	}

	@Override
	public int updateByPrimaryKeySelective(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByName(String name) {
		// TODO Auto-generated method stub
		return serviceMapper.deleteByName(name);
	}

	@Override
	public int updateByNameSelective(ServiceInfo record) {
		// TODO Auto-generated method stub
		return serviceMapper.updateByNameSelective(record);
	}

	@Override
	public List<ServiceInfo> selectAll() {
		// TODO Auto-generated method stub
		return serviceMapper.selectAll();
	}

}
