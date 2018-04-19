package com.jyss.yqy.mapper;

import com.jyss.yqy.entity.JBonusScj;
import java.util.List;

import com.jyss.yqy.entity.JRecordTotal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface JBonusScjMapper {
    
    //查询昨日列表详情
	List<JBonusScj> selectJBonusScj(@Param("zjUid")Integer zjUid);
	
	//查询昨日总pv
	float selectScjTotal();
	
	//查询本周列表详情
	List<JBonusScj> selectJBonusScjWek(@Param("zjUid")Integer zjUid);
	
	//查询本周总pv
	float selectScjTotalWek();
	
    //按两个日期查询个人列表详情
	//List<JBonusScj> selectJBonusScjByUidAndDay(@Param("uId")int uId, @Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询个人列表总值详情
	List<JBonusScj> selectJBonusScjByDay(@Param("zjUid")Integer zjUid,@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按两个日期查询总pv
	float selectScjTotalByDay(@Param("beginTime")String beginTime,@Param("endTime")String endTime);
	
	//按月查询查询个人列表详情
	//List<JBonusScj> selectJBonusScjByUidAndMonth(@Param("uId")int uId, @Param("month")String month);
	
	//按月查询查询个人列表总值详情
	List<JBonusScj> selectJBonusScjByMonth(@Param("zjUid")Integer zjUid,@Param("month")String month);
	
	//按月查询查询总pv
	float selectScjTotalByMonth(@Param("month")String month);

	//查询列表历史总值
	List<JBonusScj> selectTotalJBonusScj();

	//总收益列表
	List<JRecordTotal> selectScjTotalList();
	
}