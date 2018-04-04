package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.jsonEntity.UserBean;

public interface UserMapper {

	/**
	 * u-User 统计
	 * 
	 * @param status
	 * @param isAuth
	 * @param isChuangke
	 * @return
	 */
	List<UserBean> getUserCount(@Param("isChuangke") String isChuangke,
			@Param("status") String status, @Param("isAuth") String isAuth);

	/**
	 * 修改个人。。。等等。。状态
	 * 
	 * @return
	 */
	int upUserAllStatus(@Param("status") String status,
			@Param("bCode") String bCode, @Param("bIsPay") String bIsPay,
			@Param("isChuangke") String isChuangke,
			@Param("isAuth") String isAuth, @Param("uuid") String uuid);
	
	/**
	 * 通过用户的uuid查询用户
	 */
	List<UserBean> getUserByUuid(@Param("uuid") String uuid);

	/**
	 * 通过id查询用户
	 */
	List<UserBean> getUserById(@Param("id") int id,@Param("isChuangke") int isChuangke);
	
	/**
	 * 通过推荐码查询用户id
	 */
	List<UserBean> getUserIdByAccount(@Param("account") String account);
	
	/**
	 * 通过id查询积分
	 */
	List<UserBean> getUserScoreById(@Param("id") int id);
	
	//更新积分
	int updateScore(UserBean userBean);
	

}
