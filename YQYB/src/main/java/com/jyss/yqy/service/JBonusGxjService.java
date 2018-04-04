package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;

public interface JBonusGxjService {

    JRecordResult selectJBonusGxj();

    JRecordResult selectJBonusGxjWek();

    JRecordResult selectJBonusGxjByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    JRecordResult selectJBonusGxjByMonth(@Param("month") String month);


}
