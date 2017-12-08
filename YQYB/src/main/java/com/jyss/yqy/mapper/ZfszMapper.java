package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.Zfsz;

public interface ZfszMapper {
	/**
	 * 获取支付设置列表
	 * 
	 * @return
	 */
	List<Zfsz> getZfsz();

	/**
	 * 获取支付设置列表
	 * 
	 * @param type
	 * @return
	 */
	List<Zfsz> getZfszBy(@Param("type") String type);

	/**
	 * 新增
	 * 
	 * @param zf
	 * @return
	 */
	int addZfsz(Zfsz zf);

	/**
	 * 修改
	 * 
	 * @param zf
	 * @return
	 */
	int updateZfsz(Zfsz zf);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteZfsz(@Param("ids") List<Long> ids);

}
