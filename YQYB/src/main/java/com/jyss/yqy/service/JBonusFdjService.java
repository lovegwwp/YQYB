package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.JBonusFdjResult;


public interface JBonusFdjService {
	
	JBonusFdjResult selectFdjTotal();
	
	JBonusFdjResult selectFdjTotalByWek();
	
	JBonusFdjResult selectFdjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusFdjResult selectFdjTotalByMonth(@Param("month")String month);

}
