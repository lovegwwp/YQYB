package com.jyss.yqy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusGlj;
import com.jyss.yqy.entity.ResponseEntity;
import com.jyss.yqy.entity.ScoreBalance;
import com.jyss.yqy.entity.UUserRRecordB;
import com.jyss.yqy.entity.UUserRRecordBExample;
import com.jyss.yqy.entity.UUserRRecordBExample.Criteria;
import com.jyss.yqy.entity.Xtcl;
import com.jyss.yqy.entity.jsonEntity.UserBean;
import com.jyss.yqy.mapper.JBonusGljMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.mapper.UUserRRecordBMapper;
import com.jyss.yqy.mapper.UserMapper;
import com.jyss.yqy.mapper.XtclMapper;
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
	@Autowired
	private XtclMapper xtclMapper;
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;

	
	
	/**
	 * 管理奖
	 */
	@Override
	public ResponseEntity insertJBonusGlj(String uuid){      
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
						
						//查询奖励额
						Xtcl xtcl1 = xtclMapper.getClsValue("glj_type", "1");   //初级获得金额
						double double1 = Double.parseDouble(xtcl1.getBz_value());    //40.00
						Xtcl xtcl2 = xtclMapper.getClsValue("glj_type", "2");   //中级获得金额
						double double2 = Double.parseDouble(xtcl2.getBz_value());    //80.00
						Xtcl xtcl3 = xtclMapper.getClsValue("glj_type", "3");   //高级获得金额
						double double3 = Double.parseDouble(xtcl3.getBz_value());    //120.00
						 
						
						JBonusGlj bonusGlj = new JBonusGlj();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						bonusGlj.setuId(userBean.getId());
						bonusGlj.setuName(userBean.getRealName());
						bonusGlj.setParentId(userRecord.getrId());
						bonusGlj.setStatus(1);
						bonusGlj.setCreated(sdf.format(new Date()));
						if (pLevel == 4) {
							bonusGlj.setParentMoney(double3);
							bonusGljMapper.insert(bonusGlj);
							computeJF(userRecord.getrId(),double3);
							
						} else if(pLevel == 3) {
							bonusGlj.setParentMoney(double2);
							bonusGljMapper.insert(bonusGlj);
							computeJF(userRecord.getrId(),double2);
							
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
								bonusGlj1.setParentMoney(double1);
								bonusGlj1.setStatus(1);
								bonusGlj1.setCreated(sdf.format(new Date()));
								bonusGljMapper.insert(bonusGlj1);
								computeJF(userRecord1.getrId(),double1);
								//}
							}
						}else if(pLevel == 2){
							bonusGlj.setParentMoney(double1);
							bonusGljMapper.insert(bonusGlj);
							computeJF(userRecord.getrId(),double1);
							
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
										bonusGlj1.setParentMoney(double2);
										bonusGlj1.setStatus(1);
										bonusGlj1.setCreated(sdf.format(new Date()));
										bonusGljMapper.insert(bonusGlj1);
										computeJF(userRecord1.getrId(),double2);
									}else if(level1 == 2){
										JBonusGlj bonusGlj1 = new JBonusGlj();
										bonusGlj1.setuId(userRecord1.getuId());
										bonusGlj1.setuName(userBean1.getRealName());
										bonusGlj1.setParentId(userRecord1.getrId());
										bonusGlj1.setParentMoney(double1);
										bonusGlj1.setStatus(1);
										bonusGlj1.setCreated(sdf.format(new Date()));
										bonusGljMapper.insert(bonusGlj1);
										computeJF(userRecord1.getrId(),double1);
										
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
											bonusGlj2.setParentMoney(double1);
											bonusGlj2.setStatus(1);
											bonusGlj2.setCreated(sdf.format(new Date()));
											bonusGljMapper.insert(bonusGlj2);
											computeJF(userRecord2.getrId(),double1);
										}
									}
								}
								
							}
						}
					}
					return new ResponseEntity("true", "操作成功！");
				}
			}
		}
		return new ResponseEntity("false", "操作失败！");
		
	}
	
	
	/**
	 * 计算积分
	 */
	private void computeJF(int id,double money){
		//查询积分比例
		Xtcl xtcl1 = xtclMapper.getClsValue("jjbl_type", "xj");      //现金积分比例
		double double1 = Double.parseDouble(xtcl1.getBz_value());    //0.7
		Xtcl xtcl2 = xtclMapper.getClsValue("jjbl_type", "gw");      //购物积分比例
		double double2 = Double.parseDouble(xtcl2.getBz_value());    //0.2
		
		List<UserBean> userList = userMapper.getUserScoreById(id);     //ParentId
		if(userList != null && userList.size()>0){
			//Double money = jBonusGlj.getParentMoney(); 
			UserBean userBean = userList.get(0);
			//添加现金积分
			ScoreBalance score1 = new ScoreBalance();
			score1.setEnd(2);
			score1.setuUuid(userBean.getUuid());
			score1.setCategory(4);
			score1.setType(1);
			score1.setScore((float)(money * double1));
			score1.setJyScore((float)(money * double1)+ userBean.getCashScore());
			score1.setCreatedAt(new Date());
			score1.setStatus(1);
			int count1 = scoreBalanceMapper.addCashScoreBalance(score1);
			
			ScoreBalance score2 = new ScoreBalance();
			score2.setEnd(2);
			score2.setuUuid(userBean.getUuid());
			score2.setCategory(4);
			score2.setType(1);
			score2.setScore((float)(money * double2));
			score2.setJyScore((float)(money * double2) + userBean.getShoppingScore());
			score2.setCreatedAt(new Date());
			score2.setStatus(1);
			int count2 = scoreBalanceMapper.addShoppingScoreBalance(score2);
			
			if(count1 > 0 && count2 > 0){
				UserBean userBean2 = new UserBean();
				userBean2.setId(id);
				userBean2.setCashScore((float)(money * double1)+ userBean.getCashScore());
				userBean2.setShoppingScore((float)(money * double2) + userBean.getShoppingScore());
				userMapper.updateScore(userBean2);
			}
		}
	}

	

}
