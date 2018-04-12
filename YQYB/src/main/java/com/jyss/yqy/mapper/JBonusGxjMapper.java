package com.jyss.yqy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JBonusGxjMapper {


	//查询昨日总金额
	float selectGxjTotal();

	//查询本周总金额
	float selectGxjTotalByWek();

	//按两个日期查询总收益
	float selectGxjTotalByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    //按月查询总收益
    float selectGxjTotalByMonth(@Param("month") String month);
    
}