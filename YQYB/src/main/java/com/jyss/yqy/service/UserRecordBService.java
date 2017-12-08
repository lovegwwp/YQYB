package com.jyss.yqy.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface UserRecordBService {

	Map<String,String> insertJBonusGlj(@Param("uuid") String uuid);

}
