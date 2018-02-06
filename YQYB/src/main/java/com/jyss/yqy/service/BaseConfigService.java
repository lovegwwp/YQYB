package com.jyss.yqy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.BaseConfig;

public interface BaseConfigService {
	
	 int insertConfig(BaseConfig bc);

	    int updateByPrimaryKey(BaseConfig bc);

	    //查询所有
	    List<BaseConfig> getAllConfig(@Param("title") String title,@Param("key") String key);
	    
		/**
		 * 删除
		 * 
		 * @param ids
		 * @return
		 * 
		 */
		int deleteConfig(@Param("ids") List<Long> ids);

}
