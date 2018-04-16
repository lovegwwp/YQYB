package com.jyss.yqy.service;

import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;
import com.jyss.yqy.entity.JBonusScj;
import java.util.List;


public interface JBonusScjService {

	JRecordResult selectJBonusScjInfo();

	List<JBonusScj> selectJBonusScj(@Param("zjUid")Integer zjUid);


	JRecordResult selectJBonusScjWekInfo();

	List<JBonusScj> selectJBonusScjWek(@Param("zjUid")Integer zjUid);


	JRecordResult selectJBonusScjByDayInfo(@Param("beginTime")String beginTime,@Param("endTime")String endTime);

	List<JBonusScj> selectJBonusScjByDay(@Param("zjUid")Integer zjUid,@Param("beginTime")String beginTime,
										 @Param("endTime")String endTime);


	JRecordResult selectJBonusScjByMonthInfo(@Param("month")String month);

	List<JBonusScj> selectJBonusScjByMonth(@Param("zjUid")Integer zjUid,@Param("month")String month);

	List<JBonusScj> selectTotalJBonusScj();

}
