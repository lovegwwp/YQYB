package com.jyss.yqy.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JRecord;

public interface JRecordMapper {
    
	
	//查询用户
	List<JRecord> selectAllJRecord(@Param("uAccount")String uAccount);
	
	//根据id查询
	List<JRecord> selectJRecordById(@Param("id")int id);
	
	//根据父id查询
	List<JRecord> selectJRecordByPid(@Param("parentId")int parentId);
	
	//根据用户id查询
	List<JRecord> selectJRecordByUid(@Param("uId")int uId);
	
	//添加市场用户
	int insertJRecord(JRecord jRecord);
	
	//修改市场用户
	int updateJRecordById(JRecord jRecord);
	
	//修改父id
	int upJRecordById(@Param("parentId")int parentId,@Param("id")int id);
	
	//删除市场用户
	int deleteJRecordById(@Param("id")int id);
    
  
}