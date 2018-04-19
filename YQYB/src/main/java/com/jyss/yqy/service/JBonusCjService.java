package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JBonusCjService {

    JRecordResult selectJBonusCj();

    JRecordResult selectJBonusCjWek();

    JRecordResult selectJBonusCjByDay(@Param("beginTime")String beginTime, @Param("endTime")String endTime);

    JRecordResult selectJBonusCjByMonth(@Param("month")String month);

    //总收益列表
    List<JRecordTotal> selectCjTotalList();


}
