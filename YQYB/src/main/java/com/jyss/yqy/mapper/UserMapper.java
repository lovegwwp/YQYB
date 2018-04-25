package com.jyss.yqy.mapper;

import java.util.List;

import com.jyss.yqy.entity.User;
import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.jsonEntity.UserBean;
import org.springframework.stereotype.Repository;

@Repository
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


	//通过推荐码查询推荐人信息
	List<UserBean> getUserByBCode(@Param("bCode") String bCode);

	//测试
	List<UserBean> getUserByAccount(@Param("account") String account);


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

	//用户账号查询
	List<UserBean> getUserBy(@Param("account") String account,@Param("status") String status);

	//用户注册
	int addUser(User user);

	//查询所有借贷总额
	double selectTotalBorrow();

}
