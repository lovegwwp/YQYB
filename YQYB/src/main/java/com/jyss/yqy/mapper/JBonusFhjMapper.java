package com.jyss.yqy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JBonusFhjMapper {


	//查询当日总金额
	float selectFhjTotal();

	//查询本周总金额
	float selectFhjTotalByWek();

	//按两个日期查询总收益
	float selectFhjTotalByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    //按月查询总收益
    float selectFhjTotalByMonth(@Param("month") String month);
    
}