package com.jyss.yqy.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.ResponseEntity;


public interface UserRecordBService {

	ResponseEntity insertJBonusGlj(@Param("uuid") String uuid);

}
