package com.jyss.yqy.service;


import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreBalanceService {

    ResponseEntity insertBdScore(@Param("uuid")String uuid,@Param("payAmount")Float payAmount,
                                 @Param("zzCode")String zzCode);

    ResponseEntity updateUserBorrow(@Param("uuid")String uuid,@Param("payAmount")Float payAmount,
                                    @Param("zzCode")String zzCode);

    List<ScoreBalance> getEntryScoreBalance(@Param("secoCate")Integer secoCate,@Param("beginTime")String beginTime,
                                            @Param("endTime")String endTime);

    double selectTotalBdScore(@Param("secoCate")Integer secoCate,@Param("beginTime")String beginTime,
                              @Param("endTime")String endTime);

}
