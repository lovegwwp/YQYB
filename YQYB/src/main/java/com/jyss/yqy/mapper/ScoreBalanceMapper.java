package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.ScoreBalance;

public interface ScoreBalanceMapper {
	/**
	 * 现金积分查询
	 * 
	 * @param uUuid
	 * @return
	 */
	List<ScoreBalance> getCashScoreBalance(@Param("uUuid") String uUuid);

	/**
	 * 购物积分查询
	 * 
	 * @param uUuid
	 * @return
	 */
	List<ScoreBalance> getShoppingScoreBalance(@Param("uUuid") String uUuid);

	/**
	 * 现金积分插入
	 * 
	 * @param sb
	 * @return
	 */
	int addCashScoreBalance(ScoreBalance sb);

	/**
	 * 购物积分插入
	 * 
	 * @param sb
	 * @return
	 */
	int addShoppingScoreBalance(ScoreBalance sb);
	
	
	//查询当天现金总积分
	float selectTodayCashScore(@Param("category") int category);
	
	//查询昨日现金总积分
	float selectTotalCashScore(@Param("category") int category);
	
	//查询本周现金总积分
	float selectTotalCashScoreByWek(@Param("category") int category);
	
	//查询时间段现金总积分
	float selectTotalCashScoreByDay(@Param("category") int category,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//查询月份现金总积
	float selectTotalCashScoreByMonth(@Param("category") int category, @Param("month")String month);
	
	//查询当日购物总积分
	float selectTodayShoppingScore(@Param("category") int category);
	
	//查询昨日购物总积分
	float selectTotalShoppingScore(@Param("category") int category);
		
	//查询本周现金总积分
	float selectTotalShoppingScoreByWek(@Param("category") int category);
		
	//查询时间段现金总积分
	float selectTotalShoppingScoreByDay(@Param("category") int category,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
		
	//查询月份现金总积
	float selectTotalShoppingScoreByMonth(@Param("category") int category, @Param("month")String month);
	
}
