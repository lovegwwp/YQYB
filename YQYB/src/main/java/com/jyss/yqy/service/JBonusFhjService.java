package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import org.apache.ibatis.annotations.Param;

public interface JBonusFhjService {

    JRecordResult selectJBonusFhj();

    JRecordResult selectJBonusFhjWek();

    JRecordResult selectJBonusFhjByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    JRecordResult selectJBonusFhjByMonth(@Param("month") String month);


}
