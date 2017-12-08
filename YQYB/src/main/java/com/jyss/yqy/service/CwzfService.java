package com.jyss.yqy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.Cwzf;

public interface CwzfService {

	/**
	 * 获取支付记录列表
	 * 
	 * @return
	 */
	List<Cwzf> getCw();

	/**
	 * 获取支付记录列表
	 * 
	 * @param NO
	 * @return
	 */
	List<Cwzf> getCwByNo(@Param("NO") String NO);

	/**
	 * 获取支付记录列表
	 * 
	 * @param macId
	 * @return
	 */
	List<Cwzf> getCwBy(@Param("account") String account,
			@Param("kssj") String kssj, @Param("jssj") String jssj,
			@Param("czType") String czType);

	/**
	 * 新增
	 * 
	 * @param cw
	 * @return
	 */
	int addCw(Cwzf cw);

	/**
	 * 修改
	 * 
	 * @param cw
	 * @return
	 */
	int upCw(Cwzf cw);

	/**
	 * 修改
	 * 
	 * @param cw
	 * @return
	 */
	// int updateCw(Cwzf cw);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteCw(@Param("ids") List<Long> ids);

}
