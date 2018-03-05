package com.cyx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cyx.pojo.WorkInfo;


public interface IWorkService {
	
	public int insertWork(WorkInfo workInfo);
	
	public int deleteWorkByPrimaryKey(String workId);
	
	public List<WorkInfo> selectByAuntId(String auntId);
	
	public int updateWork(WorkInfo workInfo); 
}
