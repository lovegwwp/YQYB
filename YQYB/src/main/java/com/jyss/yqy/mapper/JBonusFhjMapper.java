package com.jyss.yqy.mapper;

import com.jyss.yqy.entity.JRecordTotal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    //总收益列表
    List<JRecordTotal> selectFhjTotalList();
}