package com.jyss.yqy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyss.yqy.entity.UserAuth;

public interface UserAuthMapper {

	// 添加实名用户信息
	int insertUserAuth(UserAuth userAuth);

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

}
