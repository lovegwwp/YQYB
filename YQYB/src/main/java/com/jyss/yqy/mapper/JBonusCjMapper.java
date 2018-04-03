package com.jyss.yqy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JBonusCjMapper {


	//查询当日总金额
	float selectCjTotal();

	//查询本周总金额
	float selectCjTotalByWek();

	//按两个日期查询总收益
	float selectCjTotalByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    //按月查询总收益
    float selectCjTotalByMonth(@Param("month") String month);
    
}