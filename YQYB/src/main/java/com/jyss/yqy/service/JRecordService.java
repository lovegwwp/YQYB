package com.jyss.yqy.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JRecord;
import com.jyss.yqy.entity.ResponseEntity;

public interface JRecordService {
	
	//添加市场用户
	ResponseEntity insertJRecord(@Param("uAccount")String uAccount,@Param("pAccount")String pAccount,
								 @Param("zjUid")Integer zjUid,@Param("depart")Integer depart);
	
	//展示市场用户
	List<JRecord> getJRecordList(@Param("zjUid")Integer zjUid);

	//搜索展示市场用户
	List<JRecord> getJRecordListByAccount(@Param("account")String account,@Param("uId")String uId);

	//修改市场用户
	ResponseEntity updateJRecord(@Param("id")int id,@Param("account")String account);
	
	//删除市场用户
	ResponseEntity deleteJRecord(@Param("id")int id);

	//查询市场总监
	List<JRecord> selectJRecordByFloor(@Param("floor")int floor);
	

}
