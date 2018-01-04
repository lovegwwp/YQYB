package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.entity.JBonusScjResult;
import com.jyss.yqy.entity.Page;


public interface JBonusScjService {
	
	Page<JBonusScj> selectJBonusScj(@Param("page")int page,@Param("limit")int limit);
	
	Page<JBonusScj> selectJBonusScjWek(@Param("page")int page,@Param("limit")int limit);
	
	Page<JBonusScj> selectJBonusScjByDay(@Param("page")int page,@Param("limit")int limit,
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	Page<JBonusScj> selectJBonusScjByMonth(@Param("page")int page,@Param("limit")int limit,@Param("month")String month);
	
	/*Page<JBonusScj> selectJBonusScjByUidAndDay(@Param("uId")int uId,@Param("page")int page,@Param("limit")int limit,
			@Param("beginTime")String beginTime,@Param("endTime")String endTime);*/
	
}
