package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.SystemShareInfoMapper;
import com.cyx.pojo.SystemShareInfo;
import com.cyx.service.ISystemShareService;

@Service("systemShareService")
public class SystemShareServceImpl implements ISystemShareService {

	@Resource
	SystemShareInfoMapper ssim;
	@Override
	public int insert(SystemShareInfo ssi) {
		// TODO Auto-generated method stub
		return ssim.insert(ssi);
	}

	@Override
	public int deleteByPrimaryKey(String shareInfoId) {
		// TODO Auto-generated method stub
		return ssim.deleteByPrimaryKey(shareInfoId);
	}

	@Override
	public List<SystemShareInfo> findByAdminId(String adminId) {
		// TODO Auto-generated method stub
		return ssim.selectByAdminId(adminId);
	}

}
