package com.cyx.dao;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AddorderInfo;
import com.cyx.pojo.OrderInfo;

public interface AddorderInfoMapper {
    int deleteByPrimaryKey(String index,String orderId);

    int insert(@Param("index")int index, @Param("addorder")OrderInfo record);

    int insertSelective(@Param("index")int index, @Param("addorder")OrderInfo record);

    AddorderInfo selectByPrimaryKey(String index,String orderId);

    int updateByPrimaryKeySelective(@Param("index")int index, @Param("addorder")OrderInfo record);

    int updateByPrimaryKey(@Param("index")int index, @Param("addorder")OrderInfo record);
}