package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.JBonusScjResult;


public interface JBonusScjService {
	
	JBonusScjResult selectJBonusScj(@Param("page")int page,@Param("limit")int limit);
	
	JBonusScjResult selectJBonusScjWek(@Param("page")int page,@Param("limit")int limit);
	
	JBonusScjResult selectJBonusScjByDay(@Param("page")int page,@Param("limit")int limit,
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusScjResult selectJBonusScjByMonth(@Param("page")int page,@Param("limit")int limit,@Param("month")String month);
	
	/*Page<JBonusScj> selectJBonusScjByUidAndDay(@Param("uId")int uId,@Param("page")int page,@Param("limit")int limit,
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);*/
	
}
