package com.jyss.yqy.mapper;

import com.jyss.yqy.entity.JBonusScj;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JBonusScjMapper {
    
    //查询昨日列表详情
	List<JBonusScj> selectJBonusScj();
	
	//查询昨日总pv
	float selectScjTotal();
	
	//查询本周列表详情
	List<JBonusScj> selectJBonusScjWek();
	
	//查询本周总pv
	float selectScjTotalWek();
	
    //按两个日期查询个人列表详情
	//List<JBonusScj> selectJBonusScjByUidAndDay(@Param("uId")int uId, @Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询个人列表总值
	List<JBonusScj> selectJBonusScjByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询总pv
	float selectScjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按月查询查询个人列表详情
	//List<JBonusScj> selectJBonusScjByUidAndMonth(@Param("uId")int uId, @Param("month")String month);
	
	//按月查询查询个人列表总值
	List<JBonusScj> selectJBonusScjByMonth(@Param("month")String month);
	
	//按月查询查询总pv
	float selectScjTotalByMonth(@Param("month")String month);
	
}