package com.jyss.yqy.service;


import com.jyss.yqy.entity.JRecordZl;
import com.jyss.yqy.entity.ResponseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JRecordZlService {

    //查询市场
    List<JRecordZl> selectJRecordZl(@Param("uId")Integer uId);

    //添加总监助理
    ResponseEntity insertJRecordZl(@Param("uId")Integer uId, @Param("zjUid")Integer zjUid,
                                   @Param("zjCode")String zjCode, @Param("zjName")String zjName);

    //修改总监助理
    ResponseEntity updateJRecordZl(@Param("id")Integer id, @Param("uId")Integer uId,
                                   @Param("zjUid")Integer zjUid, @Param("zjCode")String zjCode,
                                   @Param("zjName")String zjName);


    //删除市场总监
    ResponseEntity deleteJRecordZl(@Param("id")Integer id);


}
