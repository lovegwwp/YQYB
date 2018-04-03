package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;

public interface JBonusCjService {

    JRecordResult selectJBonusCj();

    JRecordResult selectJBonusCjWek();

    JRecordResult selectJBonusCjByDay(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

    JRecordResult selectJBonusCjByMonth(@Param("month")String month);


}
