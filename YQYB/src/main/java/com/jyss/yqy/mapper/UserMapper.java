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
	 * @param pwd
	 * @param salt
	 * @param salt
	 * @return
	 */
	int upUserAllStatus(@Param("status") String status,
			@Param("bCode") String bCode, @Param("bIsPay") String bIsPay,
			@Param("isChuangke") String isChuangke,
			@Param("isAuth") String isAuth, @Param("uuid") String uuid);

}
