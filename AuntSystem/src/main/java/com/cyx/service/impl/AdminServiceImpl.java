package com.cyx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.AdminInfoMapper;
import com.cyx.pojo.AdminInfo;
import com.cyx.service.IAdminService;
@Service("adminService")
public class AdminServiceImpl implements IAdminService {
	@Resource
	AdminInfoMapper adminMapper;
	@Override
	public int deleteByPrimaryKey(String adminId) {
		// TODO Auto-generated method stub
		return adminMapper.deleteByPrimaryKey(adminId);
	}

	@Override
	public int insert(AdminInfo record) {
		// TODO Auto-generated method stub
		return adminMapper.insert(record);
	}

	@Override
	public int insertSelective(AdminInfo record) {
		// TODO Auto-generated method stub
		return adminMapper.insertSelective(record);
	}

	@Override
	public AdminInfo selectByPrimaryKey(String adminId) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPrimaryKey(adminId);
	}

	@Override
	public int updateByPrimaryKeySelective(AdminInfo record) {
		// TODO Auto-generated method stub
		return adminMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdminInfo record) {
		// TODO Auto-generated method stub
		return adminMapper.updateByPrimaryKey(record);
	}

	@Override
	public AdminInfo selectByPhoneAndPassword(String phoneno, String password) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPhoneAndPassword(phoneno, password);
	}

	@Override
	public AdminInfo selectByPhoneno(String phoneno) {
		// TODO Auto-generated method stub
		return adminMapper.selectByPhoneno(phoneno);
	}

}
