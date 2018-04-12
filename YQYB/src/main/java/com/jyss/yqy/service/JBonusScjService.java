package com.jyss.yqy.service;

import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.JBonusScj;
import java.util.List;


public interface JBonusScjService {

	JRecordResult selectJBonusScjInfo();

	List<JBonusScj> selectJBonusScj(@Param("page")Integer zjUid);


	JRecordResult selectJBonusScjWekInfo();

	List<JBonusScj> selectJBonusScjWek(@Param("page")Integer zjUid);


	JRecordResult selectJBonusScjByDayInfo(@Param("beginTime")String beginTime,@Param("endTime")String endTime);

	List<JBonusScj> selectJBonusScjByDay(@Param("page")Integer zjUid,@Param("beginTime")String beginTime,
										 @Param("endTime")String endTime);


	JRecordResult selectJBonusScjByMonthInfo(@Param("month")String month);

	List<JBonusScj> selectJBonusScjByMonth(@Param("page")Integer zjUid,@Param("month")String month);

	List<JBonusScj> selectTotalJBonusScj();

}
