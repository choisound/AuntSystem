package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.WorkInfoMapper;
import com.cyx.pojo.WorkInfo;
import com.cyx.service.IWorkService;
@Service("workService")
public class WorkServiceImpl implements IWorkService {

	@Resource
	WorkInfoMapper wim;
	@Override
	public int insertWork(WorkInfo workInfo) {
		// TODO Auto-generated method stub
		return wim.insert(workInfo);
	}

	@Override
	public int deleteWorkByPrimaryKey(String workId) {
		// TODO Auto-generated method stub
		return wim.deleteByPrimaryKey(workId);
	}

	@Override
	public List<WorkInfo> selectByAuntId(String auntId) {
		// TODO Auto-generated method stub
		return wim.selectByAuntId(auntId);
	}

	@Override
	public int updateWork(WorkInfo workInfo) {
		// TODO Auto-generated method stub
		return wim.updateByPrimaryKey(workInfo);
	}

}
