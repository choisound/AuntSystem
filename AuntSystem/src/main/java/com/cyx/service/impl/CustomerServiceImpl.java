package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.CustomerServiceInfoMapper;
import com.cyx.pojo.CustomerServiceInfo;
import com.cyx.service.ICustomerService;
@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {
	@Resource
	CustomerServiceInfoMapper customerServiceMapper;
	@Override
	public int deleteByPrimaryKey(String customerserviceId) {
		// TODO Auto-generated method stub
		return customerServiceMapper.deleteByPrimaryKey(customerserviceId);
	}

	@Override
	public int insert(CustomerServiceInfo record) {
		// TODO Auto-generated method stub
		return customerServiceMapper.insert(record);
	}
	
	public int deleteByPhoneno(String customerservicePhoneno){
		return customerServiceMapper.deleteByPhoneno(customerservicePhoneno);
	}
	
	@Override
	public int insertSelective(CustomerServiceInfo record) {
		// TODO Auto-generated method stub
		return customerServiceMapper.insertSelective(record);
	}

	@Override
	public CustomerServiceInfo selectByPrimaryKey(String customerserviceId) {
		// TODO Auto-generated method stub
		return customerServiceMapper.selectByPrimaryKey(customerserviceId);
	}

	@Override
	public int updateByPrimaryKeySelective(CustomerServiceInfo record) {
		// TODO Auto-generated method stub
		return customerServiceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(CustomerServiceInfo record) {
		// TODO Auto-generated method stub
		return customerServiceMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<CustomerServiceInfo> selectAll() {
		// TODO Auto-generated method stub
		return customerServiceMapper.selectAll();
	}

}
