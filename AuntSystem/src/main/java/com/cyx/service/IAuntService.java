package com.cyx.service;

import java.util.List;

import com.cyx.pojo.AuntInfo;

public interface IAuntService {
	int deleteByPrimaryKey(String auntId);

    int insert(AuntInfo record);

    int insertSelective(AuntInfo record);

    AuntInfo selectByPhoneno(String phoneno);
    
    AuntInfo selectByPrimaryKey(String auntId);

    AuntInfo selectByPhonenoAndPassword(String phone,String password);
    AuntInfo selectByIdentity(String identity);
    int updateByPrimaryKeySelective(AuntInfo record);
    List<AuntInfo>selectAllList(int index,int end);
    List<AuntInfo>selectResigerList(int index,int end);
    int selectAllListCount();
    int selectResigerListCount();
    List<AuntInfo>queryListByIds(List<String> lists);
    int updateByPrimaryKey(AuntInfo record);
    List<AuntInfo>selectByAddress(String address);
    AuntInfo adminselectByPrimaryKey(String auntId);
    int updateByPhoneNoSelective(AuntInfo auntPhoneno);
}
