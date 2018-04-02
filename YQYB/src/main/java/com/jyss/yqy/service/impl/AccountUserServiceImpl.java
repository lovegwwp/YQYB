package com.jyss.yqy.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jyss.yqy.utils.Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.AccountUser;
import com.jyss.yqy.mapper.AccountUserMapper;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.utils.CommTool;
import com.jyss.yqy.utils.PasswordUtil;



@Service
@Transactional
public class AccountUserServiceImpl implements AccountUserService {
	@Autowired
	private AccountUserMapper auMapper;

	@Override
	public AccountUser getAuBy(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuBy(username);
	}

	@Override
	public int getAuNum(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuNum(username);
	}

	@Override
	public int upHtPwd(String username, String password, String salt) {
		// TODO Auto-generated method stub
		password = PasswordUtil.generate(password, salt);
		return auMapper.upHtPwd(username, password, salt);
	}

	@Override
	public Set<AccountUser> getPermissionBy(String username) {
		// TODO Auto-generated method stub
		return auMapper.getPermissionBy(username);
	}

	@Override
	public List<AccountUser> getPermissionLsitBy(@Param("username") String username) {
		return auMapper.getPermissionLsitBy(username);
	}

	@Override
	public AccountUser getAuByUsernameAndPassword(String username,
			String password) {
		// TODO Auto-generated method stub
		return auMapper.getAuByUsernameAndPassword(username, password);
	}



	@Override
	public List<AccountUser> getAuByUsername(String username) {
		// TODO Auto-generated method stub
		return auMapper.getAuByUsername(username);
	}

	@Override
	public int addAccount(AccountUser au) {
		// TODO Auto-generated method stub
		au.setName(au.getUsername());
		au.setStatus(1);
		au.setPassword("666666");
		au.setSalt(CommTool.getSalt());
		au.setPassword(PasswordUtil.generate(au.getPassword(), au.getSalt()));
		return auMapper.addAccount(au);
	}

	@Override
	public int addRoles(AccountUser au) {
		return auMapper.addRoles(au);
	}

	@Override
	public int addrolePermission(@Param("roleId") String roleId, @Param("permissionId") String permissionId) {
		return auMapper.addrolePermission(roleId, permissionId);
	}

	@Override
	public int updateMyRoles(AccountUser au, @Param("ids") List<Long> ids) {
		/////修改角色（角色修改，权限先删除，后添加）
		au.setRoleName(au.getRoleSign());
		int  count2 =  auMapper.upRoles(au);
		int  countSecond2 = 0;
		String roleId = au.getId()+"";
		if (count2==1){
			///先删除，
			count2 = auMapper.delRolePermission(roleId);
			///再添加
			if(count2>=1){
				for(long pId : ids){
					if(pId!=-1){
						countSecond2 +=auMapper.addrolePermission(roleId,pId+"");
					}
				}
			}
		}
		return count2+countSecond2;
	}

	@Override
	public int addMyRoles(AccountUser au, @Param("ids") List<Long> ids) {
		au.setRoleName(au.getRoleSign());
        int  count =  auMapper.addRoles(au);
		int  countSecond = 0;
		String roleId = au.getId()+"";
		if (count==1){
			for(long pId : ids){
				if(pId!=-1){
				   countSecond +=auMapper.addrolePermission(roleId,pId+"");
				}
			}
		}
		return count+countSecond;
	}

	@Override
	public List<AccountUser> getMennuTree(@Param("code") String code) {
		return auMapper.getMennuTree(code);
	}

	@Override
	public List<AccountUser> getRolePermission(@Param("roleId") String roleId) {
		return auMapper.getRolePermission(roleId);
	}

	@Override
	public int deleteAccounts(List<Long> ids) {
		// TODO Auto-generated method stub
		return auMapper.deleteAccounts(ids);
	}

	@Override
	public int delRoles(@Param("ids") List<Long> ids) {
		return auMapper.delRoles(ids);
	}

	@Override
	public int upAccountStatus(List<Long> ids, String status) {
		// TODO Auto-generated method stub
		return auMapper.upAccountStatus(ids, status);
	}

	@Override
	public List<AccountUser> getPermissionAndName(@Param("username") String username, @Param("roleId") String roleId) {
		return auMapper.getPermissionAndName(username,  roleId);
	}

	@Override
	public int upRoles(AccountUser au) {
		return auMapper.upRoles(au);
	}

	@Override
	public int delRolePermission(@Param("roleId") String roleId) {
		return auMapper.delRolePermission(roleId);
	}

	@Override
	public int addLog(@Param("username") String username, @Param("description") String description) {
		return auMapper.addLog(username,description);
	}

	@Override
	public int upAccount(AccountUser au) {
		// TODO Auto-generated method stub
		au.setName(au.getUsername());
		au.setStatus(1);
		au.setSalt(CommTool.getSalt());
		au.setPassword("666666");
		au.setPassword(PasswordUtil.generate(au.getPassword(), au.getSalt()));
		return auMapper.upAccount(au);
	}

	@Override
	public List<AccountUser> getRoles(String roleSign) {
		// TODO Auto-generated method stub
		return auMapper.getRoles(roleSign);
	}

}
