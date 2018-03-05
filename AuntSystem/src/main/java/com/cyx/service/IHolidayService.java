package com.cyx.service;

import java.util.List;

import com.cyx.pojo.HolidayInfo;
import com.cyx.pojo.OrderInfo;

public interface IHolidayService {
	int deleteByPrimaryKey(String holidayId);

    int insert(HolidayInfo record);
    int insertHoliDays(List<HolidayInfo> holidays);
    int insertSelective(HolidayInfo record);
    List<HolidayInfo> selectByAuntId(String auntId,String mouth);
    HolidayInfo selectByPrimaryKey(String holidayId);
    List<String>selectAuntByHoliday(String orderRes,String orderRouth);
    int updateByPrimaryKeySelective(HolidayInfo record);

    int updateByPrimaryKey(HolidayInfo record);
}
