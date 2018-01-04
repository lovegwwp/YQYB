package com.jyss.yqy.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.entity.JBonusScjResult;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.entity.PageFooter;
import com.jyss.yqy.mapper.JBonusScjMapper;
import com.jyss.yqy.mapper.ScoreBalanceMapper;
import com.jyss.yqy.service.JBonusScjService;

@Service
@Transactional
public class JBonusScjServiceImpl implements JBonusScjService{
	
	@Autowired
	private JBonusScjMapper bonusScjMapper;
	@Autowired
	private ScoreBalanceMapper scoreBalanceMapper;
	
	
	/**
	 * 昨日列表
	 */
	@Override
	public Page<JBonusScj> selectJBonusScj(int page,int limit){
		float amount = bonusScjMapper.selectScjTotal();
		float cashScore = scoreBalanceMapper.selectTotalCashScore(7);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScore(7);
		
		PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScj();
		PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);		
		//PageFooter pf = new PageFooter("总PV", amount);
		PageFooter pf = new PageFooter("总PV", amount,"现金积分",cashScore,"购物积分",shoppingScore);
		List<PageFooter> ptList = new ArrayList();
		ptList.add(pf);
		Page p = new Page(pageInfo);
	    p.setFooter(ptList);	
		return p;
	}
	
	
	/**
	 * 本周列表总值
	 */
	@Override
	public Page<JBonusScj> selectJBonusScjWek(int page,int limit){
		float amount = bonusScjMapper.selectScjTotalWek();
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByWek(7);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByWek(7);
		
		PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjWek();
		PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
			
		//PageFooter pf = new PageFooter("总PV", amount);
		PageFooter pf = new PageFooter("总PV", amount,"现金积分",cashScore,"购物积分",shoppingScore);
		List<PageFooter> ptList = new ArrayList();
		ptList.add(pf);
		Page p = new Page(pageInfo);
	    p.setFooter(ptList);	
		return p;
		
	}
	
	
	/**
	 * 按两个日期查询个人列表总值
	 */
	@Override
	public Page<JBonusScj> selectJBonusScjByDay(int page,int limit,String beginTime,String endTime){
		float amount = bonusScjMapper.selectScjTotalByDay(beginTime, endTime);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByDay(7, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByDay(7, beginTime, endTime);
		
		PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjByDay(beginTime, endTime);
		PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		
		//PageFooter pf = new PageFooter("总PV", amount);
		PageFooter pf = new PageFooter("总PV", amount,"现金积分",cashScore,"购物积分",shoppingScore);
		List<PageFooter> ptList = new ArrayList();
		ptList.add(pf);
		Page p = new Page(pageInfo);
	    p.setFooter(ptList);	
		return p;
	}
	
	
	/**
	 * 按月查询查询个人列表总值
	 */
	@Override
	public Page<JBonusScj> selectJBonusScjByMonth(int page,int limit,String month){
		float amount = bonusScjMapper.selectScjTotalByMonth(month);
		float cashScore = scoreBalanceMapper.selectTotalCashScoreByMonth(7, month);
		float shoppingScore = scoreBalanceMapper.selectTotalShoppingScoreByMonth(7, month);
		
		PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjByMonth(month);
		PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		
		//PageFooter pf = new PageFooter("总PV", amount);
		PageFooter pf = new PageFooter("总PV", amount,"现金积分",cashScore,"购物积分",shoppingScore);
		List<PageFooter> ptList = new ArrayList();
		ptList.add(pf);
		Page p = new Page(pageInfo);
	    p.setFooter(ptList);	
		return p;
	}
	
	
	
	/**
	 * 按两个日期查询个人列表详情 
	 */
	/*@Override
	public Page<JBonusScj> selectJBonusScjByUidAndDay(int uId,int page,int limit,String beginTime,String endTime){
		PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjByUidAndDay(uId, beginTime, endTime);
		PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		Page<JBonusScj> result = new Page<JBonusScj>();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}*/
	

}
