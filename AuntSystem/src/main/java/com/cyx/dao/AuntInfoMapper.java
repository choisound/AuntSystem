package com.cyx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyx.pojo.AuntInfo;

public interface AuntInfoMapper {
    int deleteByPrimaryKey(String auntId);
    AuntInfo adminselectByPrimaryKey(String auntId);
    List<AuntInfo>selectAllList(int index,int end);
    List<AuntInfo>selectResigerList(int index,int end);
    List<AuntInfo>selectByAddress(String address);
    List<AuntInfo>queryListByIds(@Param("lists")List<String> lists);
    int selectAllListCount();
    int selectResigerListCount();
    int insert(AuntInfo record);
    AuntInfo selectByPhonenoAndPassword(String phone,String password);
    int insertSelective(AuntInfo record);
    int updateByPhoneNoSelective(AuntInfo auntPhoneno);
    AuntInfo selectByPrimaryKey(String auntId);
    AuntInfo selectByPhoneno(String phoneno);
    AuntInfo selectByIdentity(String identity);
    int updateByPrimaryKeySelective(AuntInfo record);
    int updateByPrimaryKey(AuntInfo record);
}