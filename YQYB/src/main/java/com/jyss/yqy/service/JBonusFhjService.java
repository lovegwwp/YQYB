package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusFxjResult;


public interface JBonusFhjService {
	
	JBonusFxjResult selectFhjTotal(@Param("float1")float float1);
	
	JBonusFxjResult selectFhjTotalByWek(@Param("float1")float float1);
	
	JBonusFxjResult selectFhjTotalByDay(@Param("float1")float float1,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusFxjResult selectFhjTotalByMonth(@Param("float1")float float1,@Param("month")String month);

}
