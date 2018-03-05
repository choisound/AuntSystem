package com.cyx.dao;

import java.util.List;

import com.cyx.pojo.DisCount;
import com.cyx.pojo.DisCountExample;

import org.apache.ibatis.annotations.Param;

public interface DisCountMapper {
    int deleteByExample(DisCountExample example);

    int deleteByPrimaryKey(String discountId);

    int insert(DisCount record);

    int insertSelective(DisCount record);
    List<DisCount>selectDiscountByUserId(String userId);
    DisCount selectByPrimaryKey(String discountId);

    int updateByExampleSelective(@Param("record") DisCount record, @Param("example") DisCountExample example);

    int updateByExample(@Param("record") DisCount record, @Param("example") DisCountExample example);

    int updateByPrimaryKeySelective(DisCount record);

    int updateByPrimaryKey(DisCount record);
}