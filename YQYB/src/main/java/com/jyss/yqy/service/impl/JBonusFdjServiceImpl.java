package com.jyss.yqy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusFdjResult;
import com.jyss.yqy.mapper.JBonusFdjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusFdjService;

@Service
@Transactional
public class JBonusFdjServiceImpl implements JBonusFdjService{
	
	@Autowired
	private JBonusFdjMapper jBonusFdjMapper;
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;
	
	
	/**
	 * 昨日金额
	 */
	@Override
	public JBonusFdjResult selectFdjTotal(){
		double amount = jBonusFdjMapper.selectFdjTotal();
		float cashScore = scoreBalanceMapper.selectTotalCashScore(5);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScore(5);
		JBonusFdjResult result = new JBonusFdjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 本周金额
	 */
	@Override
	public JBonusFdjResult selectFdjTotalByWek(){
		double amount = jBonusFdjMapper.selectFdjTotalByWek();
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByWek(5);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByWek(5);
		JBonusFdjResult result = new JBonusFdjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 两个时间段的总金额
	 */
	@Override
	public JBonusFdjResult selectFdjTotalByDay(String beginTime,String endTime){
		double amount = jBonusFdjMapper.selectFdjTotalByDay(beginTime, endTime);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByDay(5, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByDay(5, beginTime, endTime);
		JBonusFdjResult result = new JBonusFdjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 按月查询总金额
	 */
	@Override
	public JBonusFdjResult selectFdjTotalByMonth(String month){
		double amount = jBonusFdjMapper.selectFdjTotalByMonth(month);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByMonth(5, month);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByMonth(5, month);
		JBonusFdjResult result = new JBonusFdjResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}


}
