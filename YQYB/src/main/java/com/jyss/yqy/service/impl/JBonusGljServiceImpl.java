package com.jyss.yqy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyss.yqy.entity.JBonusGljResult;
import com.jyss.yqy.mapper.JBonusGljMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusGljService;

@Service
@Transactional
public class JBonusGljServiceImpl implements JBonusGljService{
	
	@Autowired
	private JBonusGljMapper jBonusGljMapper;
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;
	
	/**
	 * 昨日总金额
	 */
	@Override
	public JBonusGljResult selectGljTotal() {
		double amount = jBonusGljMapper.selectGljTotal();
		float cashScore = scoreBalanceMapper.selectTotalCashScore(4);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScore(4);
		JBonusGljResult result = new JBonusGljResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 本周总金额
	 */
	@Override
	public JBonusGljResult selectGljTotalByWek() {
		double amount = jBonusGljMapper.selectGljTotalByWek();
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByWek(4);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByWek(4);
		JBonusGljResult result = new JBonusGljResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 两个日期查询总收益
	 */
	@Override
	public JBonusGljResult selectGljTotalByDay(String beginTime, String endTime) {
		double amount = jBonusGljMapper.selectGljTotalByDay(beginTime, endTime);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByDay(4, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByDay(4, beginTime, endTime);
		JBonusGljResult result = new JBonusGljResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	
	
	/**
	 * 按月查询总收益
	 */
	@Override
	public JBonusGljResult selectGljTotalByMonth(String month) {
		double amount = jBonusGljMapper.selectGljTotalByMonth(month);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByMonth(4, month);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByMonth(4, month);
		JBonusGljResult result = new JBonusGljResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		return result;
	}
	

}
