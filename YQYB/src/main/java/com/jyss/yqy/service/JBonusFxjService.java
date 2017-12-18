package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusFxjResult;


public interface JBonusFxjService {
	
	JBonusFxjResult selectFxjTotal();
	
	JBonusFxjResult selectFxjTotalByWek();
	
	JBonusFxjResult selectFxjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusFxjResult selectFxjTotalByMonth(@Param("month")String month);

}
