package com.jyss.yqy.service.impl;

import java.util.List;

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

}
