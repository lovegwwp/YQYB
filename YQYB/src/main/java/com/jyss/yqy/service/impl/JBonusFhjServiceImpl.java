package com.jyss.yqy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusFxjResult;
import com.jyss.yqy.entity.Xtcl;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.mapper.XtclMapper;
import com.jyss.yqy.service.JBonusFhjService;

@Service
@Transactional
public class JBonusFhjServiceImpl implements JBonusFhjService{
	
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;
	@Autowired
	private XtclMapper xtclMapper;
	
	
	/**
	 * 当日金额
	 */
	@Override
	public JBonusFxjResult selectFhjTotal(){
		Xtcl xtcl = xtclMapper.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());        //0.7
		
		float cashScore = scoreBalanceMapper.selectTodayCashScore(11);
		float shoppingScore = scoreBalanceMapper.selectTodayShoppingScore(11);
		float amount = (float) (Math.round((cashScore / float1) * 100)) / 100;      //现金积分转pv
		
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 本周金额
	 */
	@Override
	public JBonusFxjResult selectFhjTotalByWek(){
		Xtcl xtcl = xtclMapper.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());        //0.7
		
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByWek(11);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByWek(11);
		float amount = (float) (Math.round((cashScore / float1) * 100)) / 100;
		
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 两个时间段的总金额
	 */
	@Override
	public JBonusFxjResult selectFhjTotalByDay(String beginTime,String endTime){
		Xtcl xtcl = xtclMapper.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());        //0.7
		
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByDay(11, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByDay(11, beginTime, endTime);
		float amount = (float) (Math.round((cashScore / float1) * 100)) / 100;
		
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 按月查询总金额
	 */
	@Override
	public JBonusFxjResult selectFhjTotalByMonth(String month){
		Xtcl xtcl = xtclMapper.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());        //0.7
		
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByMonth(11, month);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByMonth(11, month);
		float amount = (float) (Math.round((cashScore / float1) * 100)) / 100;
		
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}


}
