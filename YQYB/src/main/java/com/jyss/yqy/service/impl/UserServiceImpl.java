package com.jyss.yqy.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.UserAuth;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.UserAuthMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserAuthMapper uAuthMapper;

	@Override
	public List<UserBean> getUserCount(String isChuangke, String status,
			String isAuth) {
		// TODO Auto-generated method stub
		return userMapper.getUserCount(isChuangke, status, isAuth);
	}

	@Override
	public List<UserAuth> getUserAuth(String uUuid, String account,
			String status) {
		// TODO Auto-generated method stub
		return uAuthMapper.getUserAuth(uUuid, account, status);
	}

	@Override
	public int upUserSh(String status, String statusBefore, String uuid) {
		// TODO Auto-generated method stub
		return uAuthMapper.upUserSh(status, statusBefore, uuid);
	}

	@Override
	public int upUserAllStatus(String status, String bCode, String bIsPay,
			String isChuangke, String isAuth, String uuid) {
		// TODO Auto-generated method stub
		return userMapper.upUserAllStatus(status, bCode, bIsPay, isChuangke,
				isAuth, uuid);
	}

	@Override
	public int upIsTransfer(@Param("ids") List<String> ids, @Param("isTransfer") String isTransfer) {
		return userMapper.upIsTransfer(ids, isTransfer);
	}

	/**
	 * //查询是否重复，根据bcode和uuid
	 * @param uuid
	 * @param bCode
	 * @return
	 */

	@Override
	public List<UserBean> getUserIsOnlyBy(@Param("uuid") String uuid, @Param("bCode") String bCode) {
		return userMapper.getUserIsOnlyBy( uuid,  bCode);
	}

	//通过推荐码查询推荐人信息
	@Override
	public List<UserBean> getUserByBCode(String bCode) {
		return userMapper.getUserByBCode(bCode);
	}

}
