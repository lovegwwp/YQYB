package com.jyss.yqy.service;


import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScoreBalanceService {

    ResponseEntity insertBdScore(@Param("")String uuid,@Param("payAmount")Float payAmount,
                                 @Param("zzCode")String zzCode);


    List<ScoreBalance> getEntryScoreBalance(@Param("secoCate")Integer secoCate);
}
