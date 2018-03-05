package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.HolidayInfo;
import com.cyx.pojo.OrderInfo;

public interface HolidayInfoMapper {
    int deleteByPrimaryKey(String holidayId);

    int insert(HolidayInfo record);

    int insertSelective(HolidayInfo record);
    List<String>selectAuntByHoliday(String orderRes,String orderRouth);
    HolidayInfo selectByPrimaryKey(String holidayId);
    int insertHoliDays(List<HolidayInfo> holidays);
    int updateByPrimaryKeySelective(HolidayInfo record);
    List<HolidayInfo> selectByAuntId(String auntId,String mouth);
    int updateByPrimaryKey(HolidayInfo record);
}