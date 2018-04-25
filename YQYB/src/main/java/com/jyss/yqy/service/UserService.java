package com.jyss.yqy.service;

import java.util.List;

import com.jyss.yqy.entity.User;
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
	 * @param status
	 * @param bIsPay
	 * @param bCode
	 * @param isChuangke
	 * @param isAuth
	 * @param uuid
	 * @return
	 */
	int upUserAllStatus(@Param("status") String status,
			@Param("bCode") String bCode, @Param("bIsPay") String bIsPay,
			@Param("isChuangke") String isChuangke,
			@Param("isAuth") String isAuth, @Param("uuid") String uuid);

	/**
	 * 设置转账
	 *
	 * @param isTransfer
	 * @param ids
	 * @return
	 *
	 */
	int upIsTransfer(@Param("ids") List<String> ids,@Param("isTransfer") String isTransfer);

	/**
	 * //查询是否重复，根据bcode和uuid
	 * @param uuid
	 * @param bCode
	 * @return
	 */
	List<UserBean> getUserIsOnlyBy(@Param("uuid") String uuid,@Param("bCode") String bCode);


	//通过推荐码查询推荐人信息，用于充值
	List<UserBean> getUserByBCode(@Param("bCode") String bCode);

	//用户账号查询
	List<UserBean> getUserBy(@Param("account") String account,@Param("status") String status);

	//添加app用户，总监助理
	int addUser(User user);


	//账户查询，用于充值
	List<UserBean> getUserByAccount(@Param("account") String account);

	//查询所有借贷总额
	double selectTotalBorrow();

	//查询所有合伙人信息
	List<UserBean> getUsers(@Param("account") String account);

	//禁用用户
	int updateUserStatus(@Param("status") String status,@Param("uuid") String uuid);
}
