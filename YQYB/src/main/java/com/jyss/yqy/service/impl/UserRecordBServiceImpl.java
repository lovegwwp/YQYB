package com.jyss.yqy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusGlj;
import com.jyss.yqy.entity.UUserRRecordB;
import com.jyss.yqy.entity.UUserRRecordBExample;
import com.jyss.yqy.entity.UUserRRecordBExample.Criteria;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.JBonusGljMapper;
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
	@Autowired
	private JBonusGljMapper bonusGljMapper;

	
	
	/**
	 * 管理奖
	 */
	@Override
	public Map<String,String>  insertJBonusGlj(String uuid){      
		Map<String, String> map = new HashMap<String, String>();
		List<UserBean> userList = userMapper.getUserByUuid(uuid);
		UserBean userBean = userList.get(0); // 获取被推荐人信息
		int isAuth = userBean.getIsAuth();
		int uLevel = userBean.getIsChuangke();
		if(isAuth == 2 && (uLevel == 2 || uLevel == 3 || uLevel == 4)){
			int count = userRecordMapper.updateByUid(userBean.getId(), 1);
			
			if(count > 0){
				UUserRRecordBExample example = new UUserRRecordBExample();
				Criteria criteria = example.createCriteria();
				criteria.andUIdEqualTo(userBean.getId());
				criteria.andStatusEqualTo(1);
				List<UUserRRecordB> list = userRecordMapper.selectByExample(example);
				if(list != null && list.size()>0){
					UUserRRecordB userRecord = list.get(0);
					
					List<UserBean> nameById = userMapper.getUserNameById(userRecord.getrId());
					if(nameById != null && nameById.size()>0){
						UserBean userBean1 = nameById.get(0);
						int pLevel = userBean1.getIsChuangke();
						
						JBonusGlj bonusGlj = new JBonusGlj();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						bonusGlj.setuId(userBean.getId());
						bonusGlj.setuName(userBean.getRealName());
						bonusGlj.setParentId(userRecord.getrId());
						bonusGlj.setStatus(1);
						bonusGlj.setCreated(sdf.format(new Date()));
						if (pLevel == 4) {
							bonusGlj.setParentMoney(120.00);
							bonusGljMapper.insert(bonusGlj);
							
						} else if(pLevel == 3) {
							bonusGlj.setParentMoney(80.00);
							bonusGljMapper.insert(bonusGlj);
							
							UUserRRecordBExample example1 = new UUserRRecordBExample();
							Criteria criteria1 = example1.createCriteria();
							criteria1.andUIdEqualTo(userRecord.getrId());
							criteria1.andStatusEqualTo(1);
							List<UUserRRecordB> list1 = userRecordMapper.selectByExample(example1);
							if(list1 != null && list1.size()>0){
								UUserRRecordB userRecord1 = list1.get(0);
								/*List<UserBean> nameById1 = userMapper.getUserNameById(userRecord1.getrId());
							if(nameById1 != null && nameById1.size()>0){
								UserBean userBean2 = nameById1.get(0);*/
								
								JBonusGlj bonusGlj1 = new JBonusGlj();
								bonusGlj1.setuId(userRecord1.getuId());
								bonusGlj1.setuName(userBean1.getRealName());
								bonusGlj1.setParentId(userRecord1.getrId());
								bonusGlj1.setParentMoney(40.00);
								bonusGlj1.setStatus(1);
								bonusGlj1.setCreated(sdf.format(new Date()));
								bonusGljMapper.insert(bonusGlj1);
								//}
							}
						}else if(pLevel == 2){
							bonusGlj.setParentMoney(40.00);
							bonusGljMapper.insert(bonusGlj);
							
							UUserRRecordBExample example1 = new UUserRRecordBExample();
							Criteria criteria1 = example1.createCriteria();
							criteria1.andUIdEqualTo(userRecord.getrId());
							criteria1.andStatusEqualTo(1);
							List<UUserRRecordB> list1 = userRecordMapper.selectByExample(example1);
							if(list1 != null && list1.size()>0){
								UUserRRecordB userRecord1 = list1.get(0);
								List<UserBean> nameById1 = userMapper.getUserNameById(userRecord1.getrId());
								if(nameById1 != null && nameById1.size()>0){
									UserBean userBean2 = nameById1.get(0);
									int level1 = userBean2.getIsChuangke();
									if(level1 == 3 || level1 == 4){
										JBonusGlj bonusGlj1 = new JBonusGlj();
										bonusGlj1.setuId(userRecord1.getuId());
										bonusGlj1.setuName(userBean1.getRealName());
										bonusGlj1.setParentId(userRecord1.getrId());
										bonusGlj1.setParentMoney(80.00);
										bonusGlj1.setStatus(1);
										bonusGlj1.setCreated(sdf.format(new Date()));
										bonusGljMapper.insert(bonusGlj1);
									}else if(level1 == 2){
										JBonusGlj bonusGlj1 = new JBonusGlj();
										bonusGlj1.setuId(userRecord1.getuId());
										bonusGlj1.setuName(userBean1.getRealName());
										bonusGlj1.setParentId(userRecord1.getrId());
										bonusGlj1.setParentMoney(40.00);
										bonusGlj1.setStatus(1);
										bonusGlj1.setCreated(sdf.format(new Date()));
										bonusGljMapper.insert(bonusGlj1);
										
										UUserRRecordBExample example2 = new UUserRRecordBExample();
										Criteria criteria2 = example2.createCriteria();
										criteria2.andUIdEqualTo(userRecord1.getrId());
										criteria2.andStatusEqualTo(1);
										List<UUserRRecordB> list2 = userRecordMapper.selectByExample(example2);
										if (list2 != null && list2.size() > 0){
											UUserRRecordB userRecord2 = list2.get(0);
											JBonusGlj bonusGlj2 = new JBonusGlj();
											bonusGlj2.setuId(userRecord2.getuId());
											bonusGlj2.setuName(userBean2.getRealName());
											bonusGlj2.setParentId(userRecord2.getrId());
											bonusGlj2.setParentMoney(40.00);
											bonusGlj2.setStatus(1);
											bonusGlj2.setCreated(sdf.format(new Date()));
											bonusGljMapper.insert(bonusGlj2);
										}
									}
								}
								
							}
						}
					}
					map.put("messgae", "管理奖计算成功！");
					return map;
				}
			}
		}
		map.put("messgae", "用户还不是代理人！");
		return map;
		
		
	}

}
