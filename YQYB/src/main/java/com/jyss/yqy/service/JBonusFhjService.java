package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JBonusFhjService {

    JRecordResult selectJBonusFhj();

    JRecordResult selectJBonusFhjWek();

    JRecordResult selectJBonusFhjByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    JRecordResult selectJBonusFhjByMonth(@Param("month") String month);

    //总收益列表
    List<JRecordTotal> selectFhjTotalList(@Param("month") String month);
}
