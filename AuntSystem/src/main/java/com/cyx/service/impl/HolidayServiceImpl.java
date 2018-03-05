package com.cyx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cyx.dao.HolidayInfoMapper;
import com.cyx.pojo.HolidayInfo;
import com.cyx.pojo.OrderInfo;
import com.cyx.service.IHolidayService;
@Service("holidayService")
public class HolidayServiceImpl implements IHolidayService {
	@Resource
	HolidayInfoMapper holidayMapper;
	@Override
	public int deleteByPrimaryKey(String holidayId) {
		// TODO Auto-generated method stub
		return holidayMapper.deleteByPrimaryKey(holidayId);
	}

	@Override
	public int insert(HolidayInfo record) {
		// TODO Auto-generated method stub
		return holidayMapper.insert(record);
	}

	@Override
	public int insertSelective(HolidayInfo record) {
		// TODO Auto-generated method stub
		return holidayMapper.insertSelective(record);
	}

	@Override
	public HolidayInfo selectByPrimaryKey(String holidayId) {
		// TODO Auto-generated method stub
		return holidayMapper.selectByPrimaryKey(holidayId);
	}

	@Override
	public int updateByPrimaryKeySelective(HolidayInfo record) {
		// TODO Auto-generated method stub
		return holidayMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HolidayInfo record) {
		// TODO Auto-generated method stub
		return holidayMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<HolidayInfo> selectByAuntId(String auntId,String mouth) {
		// TODO Auto-generated method stub
		return holidayMapper.selectByAuntId(auntId,mouth);
	}

	@Override
	public int insertHoliDays(List<HolidayInfo> holidays) {
		// TODO Auto-generated method stub
		return holidayMapper.insertHoliDays(holidays);
	}

	@Override
	public List<String> selectAuntByHoliday(String s1,String s2) {
		// TODO Auto-generated method stub
		return holidayMapper.selectAuntByHoliday(s1,s2);
	}

}
