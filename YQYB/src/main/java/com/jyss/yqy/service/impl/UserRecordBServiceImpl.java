package com.jyss.yqy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.mapper.UUserRRecordBMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.service.UserRecordBService;

@Service
@Transactional
public class UserRecordBServiceImpl implements UserRecordBService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UUserRRecordBMapper userRecordMapper;

	// @Override
	// public Map<String, String> insertUserRecordB(String uuid, String bCode) {
	// Map<String, String> map = new HashMap<String, String>();
	// List<UserBean> userList = userMapper.getUserByUuid(uuid);
	// UserBean userBean = userList.get(0); // 获取被推荐人信息
	// int isAuth = userBean.getIsAuth();
	// int uLevel = userBean.getIsChuangke();
	// List<UserBean> parentList = userMapper.getUserByBCode(bCode);
	// if (parentList != null && parentList.size() > 0) {
	// UserBean parentUser = parentList.get(0); // 获取推荐人信息
	// int pLevel = parentUser.getIsChuangke();
	// // 推荐关系表
	// if (isAuth == 0) {
	// map.put("code", "-1");
	// map.put("status", "false");
	// map.put("message", "未实名");
	// map.put("data", "");
	// return map;
	//
	// }
	// // if(isAuth == 2 && (uLevel==2 || uLevel==3 || uLevel==4) &&
	// // (pLevel==2 || pLevel==3 || pLevel==4)){
	// if (pLevel == 2 || pLevel == 3 || pLevel == 4) {
	// UUserRRecordB userRRecordB = new UUserRRecordB();
	// userRRecordB.setuId(userBean.getId());
	// userRRecordB.setrId(parentUser.getId());
	// userRRecordB.setStatus(0); // 设置初始值为0
	// userRRecordB.setType(pLevel);
	// userRRecordB.setCreatedAt(new Date());
	// int idNum = userRecordMapper.insert(userRRecordB);
	// String val = idNum + "";
	// if (val != null && !"".equals(val)) {
	// map.put("code", "0");
	// map.put("status", "true");
	// map.put("message", "推荐码使用成功！");
	// map.put("data", "");
	// return map;
	// }
	// }
	// /*
	// * map.put("code", "-2"); map.put("status", "false");
	// * map.put("message", "您的上级还不是代理人！"); map.put("data", ""); return
	// * map;
	// */
	// }
	// map.put("code", "-2");
	// map.put("status", "false");
	// map.put("message", "此推荐码不可用！");
	// map.put("data", "");
	// return map;
	// }

}
