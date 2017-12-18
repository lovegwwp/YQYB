package com.jyss.yqy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusFxjResult;
import com.jyss.yqy.mapper.JBonusFxjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusFxjService;


@Service
@Transactional
public class JBonusFxjServiceImpl implements JBonusFxjService{
	
	@Autowired
	private JBonusFxjMapper bonusFxjMapper;
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;
	
	
	/**
	 * 查询昨日总金额
	 */
	@Override
	public JBonusFxjResult selectFxjTotal(){
		float amount = bonusFxjMapper.selectFxjTotal();
		float cashScore = scoreBalanceMapper.selectTotalCashScore(6);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScore(6);
		
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 查询本周总金额
	 */
	@Override
	public JBonusFxjResult selectFxjTotalByWek(){
		float amount = bonusFxjMapper.selectFxjTotalByWek();
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByWek(6);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByWek(6);
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 按两个日期查询总收益
	 */
	@Override
	public JBonusFxjResult selectFxjTotalByDay(String beginTime,String endTime){
		float amount = bonusFxjMapper.selectFxjTotalByDay(beginTime, endTime);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByDay(6, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByDay(6, beginTime, endTime);
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 按月查询总收益
	 */
	@Override
	public JBonusFxjResult selectFxjTotalByMonth(String month){
		float amount = bonusFxjMapper.selectFxjTotalByMonth(month);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByMonth(6, month);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByMonth(6, month);
		JBonusFxjResult result = new JBonusFxjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}

}
