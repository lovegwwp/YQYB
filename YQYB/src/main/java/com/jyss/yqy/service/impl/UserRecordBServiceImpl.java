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
		UserBean userBean = userList.get(0);   // 获取被推荐人信息
		int isAuth = userBean.getIsAuth();
		int uLevel = userBean.getIsChuangke();
		if(isAuth == 2 && (uLevel == 2 || uLevel == 3 || uLevel == 4 || uLevel == 5)){
			int count = userRecordMapper.updateByUid(userBean.getId(), 1);
			
			if(count == 1){
				computeJBonusGlj(userBean.getId());

				return new ResponseEntity("true", "操作成功！");
			}
		}
		return new ResponseEntity("false", "操作失败！");
		
	}


	/**
	 * 计算管理奖
	 * @param uId
	 */
	private void computeJBonusGlj(int uId){
		//查询关系表
		UUserRRecordBExample example = new UUserRRecordBExample();
		Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(uId);
		criteria.andStatusEqualTo(1);
		List<UUserRRecordB> list = userRecordMapper.selectByExample(example);
		if(list != null && list.size()>0){
			UUserRRecordB userRecord = list.get(0);
			Integer pLevel = userRecord.getType();    //父级代理级别

			//查询奖励额
			Xtcl xtcl1 = xtclMapper.getClsValue("glj_type", "1");   //初级获得金额
			double double1 = Double.parseDouble(xtcl1.getBz_value());            //40.00
			Xtcl xtcl2 = xtclMapper.getClsValue("glj_type", "2");   //中级获得金额
			double double2 = Double.parseDouble(xtcl2.getBz_value());            //80.00
			Xtcl xtcl3 = xtclMapper.getClsValue("glj_type", "3");   //高级获得金额
			double double3 = Double.parseDouble(xtcl3.getBz_value());            //120.00
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//经理人
			if(pLevel == 5){
				computeJBonusGlj(userRecord.getrId());

			//高级代理人，结束
			}else if(pLevel == 4){
				List<UserBean> userList = userMapper.getUserNameById(userRecord.getuId());
				if(userList != null && userList.size()>0){
					UserBean userBean = userList.get(0);
					JBonusGlj bonusGlj = new JBonusGlj();
					bonusGlj.setuId(userBean.getId());
					bonusGlj.setuName(userBean.getRealName());
					bonusGlj.setParentId(userRecord.getrId());
					bonusGlj.setStatus(1);
					bonusGlj.setCreated(sdf.format(new Date()));
					bonusGlj.setParentMoney(double3);
					bonusGljMapper.insert(bonusGlj);
					computeJF(userRecord.getrId(),double3);
				}

			//中级代理人
			}else if(pLevel == 3){
				List<UserBean> userList = userMapper.getUserNameById(userRecord.getuId());
				if(userList != null && userList.size()>0){
					UserBean userBean = userList.get(0);
					JBonusGlj bonusGlj = new JBonusGlj();
					bonusGlj.setuId(userBean.getId());
					bonusGlj.setuName(userBean.getRealName());
					bonusGlj.setParentId(userRecord.getrId());
					bonusGlj.setStatus(1);
					bonusGlj.setCreated(sdf.format(new Date()));
					bonusGlj.setParentMoney(double2);
					bonusGljMapper.insert(bonusGlj);
					computeJF(userRecord.getrId(),double2);
					//递归到高级代理人，结束
					UUserRRecordB userRecord1 = selectGjDlr(userRecord.getrId(), 4);
					if(userRecord1 != null){
						List<UserBean> userList1 = userMapper.getUserNameById(userRecord1.getuId());
						if(userList1 != null && userList1.size()>0){
							UserBean userBean1 = userList1.get(0);
							JBonusGlj bonusGlj1 = new JBonusGlj();
							bonusGlj1.setuId(userBean1.getId());
							bonusGlj1.setuName(userBean1.getRealName());
							bonusGlj1.setParentId(userRecord1.getrId());
							bonusGlj1.setStatus(1);
							bonusGlj1.setCreated(sdf.format(new Date()));
							bonusGlj1.setParentMoney(double3 - double2);
							bonusGljMapper.insert(bonusGlj1);
							computeJF(userRecord1.getrId(),double3 - double2);
						}
					}
				}

			//初级代理人
			}else if(pLevel == 2){
				List<UserBean> userList = userMapper.getUserNameById(userRecord.getuId());
				if(userList != null && userList.size()>0){
					UserBean userBean = userList.get(0);
					JBonusGlj bonusGlj = new JBonusGlj();
					bonusGlj.setuId(userBean.getId());
					bonusGlj.setuName(userBean.getRealName());
					bonusGlj.setParentId(userRecord.getrId());
					bonusGlj.setStatus(1);
					bonusGlj.setCreated(sdf.format(new Date()));
					bonusGlj.setParentMoney(double1);
					bonusGljMapper.insert(bonusGlj);
					computeJF(userRecord.getrId(),double1);

					UUserRRecordB userRecord1 = selectGjDlr(userRecord.getrId(), 3);
					if(userRecord1 != null){
						Integer type = userRecord1.getType();
						//递归到高级代理人，结束
						if(type == 4){
							List<UserBean> userList1 = userMapper.getUserNameById(userRecord1.getuId());
							if(userList1 != null && userList1.size()>0){
								UserBean userBean1 = userList1.get(0);
								JBonusGlj bonusGlj1 = new JBonusGlj();
								bonusGlj1.setuId(userBean1.getId());
								bonusGlj1.setuName(userBean1.getRealName());
								bonusGlj1.setParentId(userRecord1.getrId());
								bonusGlj1.setStatus(1);
								bonusGlj1.setCreated(sdf.format(new Date()));
								bonusGlj1.setParentMoney(double3 - double1);
								bonusGljMapper.insert(bonusGlj1);
								computeJF(userRecord1.getrId(),double3 - double1);
							}
						//递归到中级代理人
						}else if(type == 3){
							List<UserBean> userList1 = userMapper.getUserNameById(userRecord1.getuId());
							if(userList1 != null && userList1.size()>0){
								UserBean userBean1 = userList1.get(0);
								JBonusGlj bonusGlj1 = new JBonusGlj();
								bonusGlj1.setuId(userBean1.getId());
								bonusGlj1.setuName(userBean1.getRealName());
								bonusGlj1.setParentId(userRecord1.getrId());
								bonusGlj1.setStatus(1);
								bonusGlj1.setCreated(sdf.format(new Date()));
								bonusGlj1.setParentMoney(double2 - double1);
								bonusGljMapper.insert(bonusGlj1);
								computeJF(userRecord1.getrId(),double2 - double1);

								//递归到高级代理人，结束
								UUserRRecordB userRecord2 = selectGjDlr(userRecord.getrId(), 4);
								if(userRecord2 != null){
									List<UserBean> userList2 = userMapper.getUserNameById(userRecord2.getuId());
									if(userList2 != null && userList2.size()>0){
										double gjDlr = double3 - (double2 - double1) - double1;
										UserBean userBean2 = userList2.get(0);
										JBonusGlj bonusGlj2 = new JBonusGlj();
										bonusGlj2.setuId(userBean2.getId());
										bonusGlj2.setuName(userBean2.getRealName());
										bonusGlj2.setParentId(userRecord2.getrId());
										bonusGlj2.setStatus(1);
										bonusGlj2.setCreated(sdf.format(new Date()));
										bonusGlj2.setParentMoney(gjDlr);
										bonusGljMapper.insert(bonusGlj2);
										computeJF(userRecord2.getrId(),gjDlr);
									}
								}
							}
						}
					}
				}
			}
		}
	}


	/**
	 * 递归中高级代理
	 * @param uId
	 * @param type
	 * @return
	 */
	private UUserRRecordB selectGjDlr(int uId,int type){
		UUserRRecordBExample example = new UUserRRecordBExample();
		Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(uId);
		criteria.andStatusEqualTo(1);
		List<UUserRRecordB> list = userRecordMapper.selectByExample(example);
		if(list != null && list.size()>0){
			UUserRRecordB userRecord = list.get(0);
			Integer type1 = userRecord.getType();
			if(type <= type1 && type1 < 5){
				return userRecord;
			}else{
				selectGjDlr(userRecord.getrId(),type);
			}
		}
		return null;
	}


	/**
	 * 计算管理奖的积分方法
	 * @param id
	 * @param money
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
			float totalPv = userBean.getTotalPv();
			if(totalPv > 0){
				if(money <= totalPv){
					
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
					
					if(count1 == 1 && count2 == 1){
						UserBean userBean2 = new UserBean();
						userBean2.setId(id);
						userBean2.setCashScore((float)(money * double1)+ userBean.getCashScore());
						userBean2.setShoppingScore((float)(money * double2) + userBean.getShoppingScore());
						userBean2.setTotalPv((float) (totalPv - money));
						userMapper.updateScore(userBean2);
					}
				}else{
					//添加现金积分
					ScoreBalance score1 = new ScoreBalance();
					score1.setEnd(2);
					score1.setuUuid(userBean.getUuid());
					score1.setCategory(4);
					score1.setType(1);
					score1.setScore((float)(totalPv * double1));
					score1.setJyScore((float)(totalPv * double1)+ userBean.getCashScore());
					score1.setCreatedAt(new Date());
					score1.setStatus(1);
					int count1 = scoreBalanceMapper.addCashScoreBalance(score1);
					
					ScoreBalance score2 = new ScoreBalance();
					score2.setEnd(2);
					score2.setuUuid(userBean.getUuid());
					score2.setCategory(4);
					score2.setType(1);
					score2.setScore((float) (totalPv * double2));
					score2.setJyScore((float)(totalPv * double2) + userBean.getShoppingScore());
					score2.setCreatedAt(new Date());
					score2.setStatus(1);
					int count2 = scoreBalanceMapper.addShoppingScoreBalance(score2);
					
					if(count1 == 1 && count2 == 1){
						UserBean userBean2 = new UserBean();
						userBean2.setId(id);
						userBean2.setCashScore((float)(totalPv * double1)+ userBean.getCashScore());
						userBean2.setShoppingScore((float)(totalPv * double2) + userBean.getShoppingScore());
						userBean2.setTotalPv(totalPv - totalPv);
						userMapper.updateScore(userBean2);
					}
				}
			}
		}
	}

	

}
