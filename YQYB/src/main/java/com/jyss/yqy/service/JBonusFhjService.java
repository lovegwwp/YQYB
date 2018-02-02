package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusFxjResult;


public interface JBonusFhjService {
	
	JBonusFxjResult selectFhjTotal();
	
	JBonusFxjResult selectFhjTotalByWek();
	
	JBonusFxjResult selectFhjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusFxjResult selectFhjTotalByMonth(@Param("month")String month);

}
