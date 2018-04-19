package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JBonusGxjService {

    JRecordResult selectJBonusGxj();

    JRecordResult selectJBonusGxjWek();

    JRecordResult selectJBonusGxjByDay(@Param("beginTime") String beginTime, @Param("endTime") String endTime);

    JRecordResult selectJBonusGxjByMonth(@Param("month") String month);

    //总收益列表
    List<JRecordTotal> selectGxjTotalList();


}
