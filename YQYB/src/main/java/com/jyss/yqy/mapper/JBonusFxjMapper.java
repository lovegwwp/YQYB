package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.JBonusFxj;

public interface JBonusFxjMapper {
	
	//查询昨日个人列表详情
	//List<JBonusFxj> selectJBonusFxjByPid(@Param("parentId")int parentId);
	
	//查询昨日个人列表总值
	//List<JBonusFxj> selectJBonusFxj();
	
	//查询昨日总金额
	float selectFxjTotal();
	
	//查询本周总金额
	float selectFxjTotalByWek();
	
	//按两个日期查询个人列表详情
	//List<JBonusFxj> selectJBonusFxjByPidAndDay(@Param("parentId")int parentId, @Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询个人列表总值
	//List<JBonusFxj> selectJBonusFxjByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询总收益
	float selectFxjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
    //查询本周个人列表总值
    //List<JBonusFxj> selectJBonusFxjWek();
    
    //按月查询
    //List<JBonusFxj> selectJBonusFxjByMonth(@Param("parentId")int parentId, @Param("month")String month);
    
    //按月查询总收益
    float selectFxjTotalByMonth(@Param("month")String month);
    
}