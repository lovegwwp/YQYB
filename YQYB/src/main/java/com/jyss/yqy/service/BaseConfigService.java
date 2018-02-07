package com.jyss.yqy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.BaseConfig;
import com.jyss.yqy.entity.BaseShare;

public interface BaseConfigService {

	int insertConfig(BaseConfig bc);

	int updateByPrimaryKey(BaseConfig bc);

	// 查询所有
	List<BaseConfig> getAllConfig(@Param("title") String title,
			@Param("key") String key);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteConfig(@Param("ids") List<Long> ids);

	// ///////分享链接/////

	int insertShare(BaseShare bs);

	int updateShare(BaseShare bs);

	// 查询所有
	List<BaseShare> getAllShare(@Param("title") String title,
			@Param("content") String content,
			@Param("shareKey") String shareKey,
			@Param("status") String status);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteShare(@Param("ids") List<Long> ids);

}
