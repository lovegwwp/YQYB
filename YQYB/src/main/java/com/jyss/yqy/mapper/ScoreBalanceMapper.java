package com.jyss.yqy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.ScoreBalance;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreBalanceMapper {

	/**
	 * 现金积分查询
	 */
	List<ScoreBalance> getCashScoreBalance(@Param("uUuid") String uUuid);

	/**
	 * 购物积分查询
	 */
	List<ScoreBalance> getShoppingScoreBalance(@Param("uUuid") String uUuid);

	/**
	 * 现金积分插入
	 */
	int addCashScoreBalance(ScoreBalance sb);

	/**
	 * 购物积分插入
	 */
	int addShoppingScoreBalance(ScoreBalance sb);


	//插入报单券充值记录
	int insertEntryScore(ScoreBalance sb);

	//条件查询充值记录
	List<ScoreBalance> getEntryScoreBalance(@Param("secoCate") Integer secoCate, @Param("beginTime")String beginTime,
											@Param("endTime")String endTime);

	//条件查询充值总值
	double getTotalEntryScore(@Param("secoCate") Integer secoCate, @Param("beginTime")String beginTime,
							  @Param("endTime")String endTime);



	//////// 现金积分（股券）////////
	//查询当天现金总积分(实际时间)
	float selectTodayCashScore(@Param("category") int category);
	
	//查询昨日现金总积分
	float selectCashScore(@Param("category") int category);

	//查询本周现金总积分
	float selectCashScoreByWek(@Param("category") int category);

	//查询本周现金总积分(实际时间)
	float selectCashScoreByWek1(@Param("category") int category);
	
	//查询时间段现金总积分
	float selectCashScoreByDay(@Param("category") int category,@Param("beginTime")String beginTime,
							   @Param("endTime")String endTime);

	//查询时间段现金总积分(实际时间)
	float selectCashScoreByDay1(@Param("category") int category,@Param("beginTime")String beginTime,
								@Param("endTime")String endTime);

	//查询月份现金总积分
	float selectCashScoreByMonth(@Param("category") int category, @Param("month")String month);

	//查询月份现金总积分(实际时间)
	float selectCashScoreByMonth1(@Param("category") int category, @Param("month")String month);



	//////// 购物积分（消费券）////////
	//查询当日购物总积分(实际时间)
	float selectTodayShoppingScore(@Param("category") int category);
	
	//查询昨日购物总积分
	float selectShoppingScore(@Param("category") int category);
		
	//查询本周现金总积分
	float selectShoppingScoreByWek(@Param("category") int category);

	//查询本周现金总积分(实际时间)
	float selectShoppingScoreByWek1(@Param("category") int category);
		
	//查询时间段现金总积分
	float selectShoppingScoreByDay(@Param("category") int category,@Param("beginTime")String beginTime,
								   @Param("endTime")String endTime);

	//查询时间段现金总积分(实际时间)
	float selectShoppingScoreByDay1(@Param("category") int category,@Param("beginTime")String beginTime,
									@Param("endTime")String endTime);

	//查询月份现金总积分
	float selectShoppingScoreByMonth(@Param("category") int category, @Param("month")String month);

	//查询月份现金总积分(实际时间)
	float selectShoppingScoreByMonth1(@Param("category") int category, @Param("month")String month);



	//////// 电子券积分 ////////
	//查询当日电子券总积分(实际时间)
	float selectTodayElecScore(@Param("category") int category);

	//查询昨日电子券总积分
	float selectElecScore(@Param("category") int category);

	//查询本周电子券总积分
	float selectElecScoreByWek(@Param("category") int category);

	//查询本周电子券总积分(实际时间)
	float selectElecScoreByWek1(@Param("category") int category);

	//查询时间段电子券总积分
	float selectElecScoreByDay(@Param("category") int category,@Param("beginTime")String beginTime,
							   @Param("endTime")String endTime);

	//查询时间段电子券总积分(实际时间)
	float selectElecScoreByDay1(@Param("category") int category,@Param("beginTime")String beginTime,
								@Param("endTime")String endTime);

	//查询月份电子券总积分
	float selectElecScoreByMonth(@Param("category") int category, @Param("month")String month);

	//查询月份电子券总积分(实际时间)
	float selectElecScoreByMonth1(@Param("category") int category, @Param("month")String month);
}
