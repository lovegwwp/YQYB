package com.jyss.yqy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.UserAuth;
import com.jyss.yqy.entity.jsonEntity.UserBean;

public interface UserService {

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

	// ///查询认证用户信息
	List<UserAuth> getUserAuth(@Param("uUuid") String uUuid,
			@Param("account") String account, @Param("status") String status);

	/**
	 * 审核代理人
	 * 
	 * @param status
	 * @param statusBefore
	 * @param uuid
	 * @return
	 */
	int upUserSh(@Param("status") String status,
			@Param("statusBefore") String statusBefore,
			@Param("uuid") String uuid);

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
