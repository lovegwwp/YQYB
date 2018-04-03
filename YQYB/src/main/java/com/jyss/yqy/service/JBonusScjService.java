package com.jyss.yqy.service;

import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.entity.Page;


public interface JBonusScjService {

	JRecordResult selectJBonusScjInfo();

	Page<JBonusScj> selectJBonusScj(@Param("page")Integer zjUid,@Param("page")int page,@Param("limit")int limit);


	JRecordResult selectJBonusScjWekInfo();
	
	Page<JBonusScj> selectJBonusScjWek(@Param("page")Integer zjUid,@Param("page")int page,@Param("limit")int limit);


	JRecordResult selectJBonusScjByDayInfo(@Param("beginTime")String beginTime,@Param("endTime")String endTime);

	Page<JBonusScj> selectJBonusScjByDay(@Param("page")Integer zjUid,@Param("page")int page,@Param("limit")int limit,
										 @Param("beginTime")String beginTime,@Param("endTime")String endTime);


	JRecordResult selectJBonusScjByMonthInfo(@Param("month")String month);

	Page<JBonusScj> selectJBonusScjByMonth(@Param("page")Integer zjUid,@Param("page")int page,
										   @Param("limit")int limit, @Param("month")String month);
	

}
