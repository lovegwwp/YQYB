package com.jyss.yqy.service;

import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.JBonusGljResult;

public interface JBonusGljService {
	
	JBonusGljResult selectGljTotal();
	
	JBonusGljResult selectGljTotalByWek();
	
	JBonusGljResult selectGljTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	JBonusGljResult selectGljTotalByMonth(@Param("month")String month);

}
