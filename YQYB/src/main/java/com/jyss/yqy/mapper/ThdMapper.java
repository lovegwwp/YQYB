package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.ThOrders;
import com.jyss.yqy.entity.Thd;

public interface ThdMapper {

	/**
	 * 获取提货点用户信息
	 * 
	 * @param name
	 * @return
	 */
	List<Thd> getThdBy(@Param("name") String name);

	/**
	 * 获取提货点商品信息
	 * 
	 * @param name
	 * @return
	 */
	List<ThOrders> getThSpBy(@Param("name") String name);

	/**
	 * 判断当前登录用户是否唯一
	 * 
	 * @param name
	 * @return
	 */
	int getThdNum(@Param("name") String name);



	/**
	 * 增加用户
	 * 
	 * @param thd
	 * @return
	 * 
	 */
	int addThd(Thd thd);

	/**
	 * 修改用户
	 * 
	 * @param thd
	 * @return
	 * 
	 */
	int upThd(Thd thd);

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int deleteThds(@Param("ids") List<Long> ids);

	/**
	 * 禁用
	 * 
	 * @param ids
	 * @return
	 * 
	 */
	int upThdStatus(@Param("ids") List<Long> ids, @Param("status") String status);

	/**
	 * 根据电话查询用户
	 */

	public List<Thd> findThdByTel(@Param("tel") String tel);

	/**
	 * 修改密码
	 */

	public void updatePwd(@Param("tel") String tel,
			@Param("password") String password, @Param("salt") String salt);

}
