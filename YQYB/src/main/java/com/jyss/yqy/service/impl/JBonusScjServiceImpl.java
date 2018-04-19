package com.jyss.yqy.service.impl;

import java.util.List;

import com.jyss.yqy.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * 昨日数据
	 */
	@Override
	public JRecordResult selectJBonusScjInfo() {
		float amount = bonusScjMapper.selectScjTotal();
		float cashScore = scoreBalanceMapper.selectCashScore(6);
		float shoppingScore = scoreBalanceMapper.selectShoppingScore(6);
		float elecScore = scoreBalanceMapper.selectElecScore(6);

		JRecordResult result = new JRecordResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		result.setElecScore(elecScore);
		return result;
	}

	/**
	 * 昨日列表
	 */
	@Override
	public List<JBonusScj> selectJBonusScj(Integer zjUid){
		//PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScj(zjUid);
		//PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		//return new Page(pageInfo);
		return list;
	}



	/**
	 * 本周数据
	 */
	@Override
	public JRecordResult selectJBonusScjWekInfo() {
		float amount = bonusScjMapper.selectScjTotalWek();
		float cashScore = scoreBalanceMapper.selectCashScoreByWek(6);
		float shoppingScore = scoreBalanceMapper.selectShoppingScoreByWek(6);
		float elecScore = scoreBalanceMapper.selectElecScoreByWek(6);

		JRecordResult result = new JRecordResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		result.setElecScore(elecScore);
		return result;
	}

	/**
	 * 本周列表总值
	 */
	@Override
	public List<JBonusScj> selectJBonusScjWek(Integer zjUid){
		//PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjWek(zjUid);
		//PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		return list;
		
	}



	/**
	 * 两个日期数据
	 */
	@Override
	public JRecordResult selectJBonusScjByDayInfo(String beginTime, String endTime) {
		float amount = bonusScjMapper.selectScjTotalByDay(beginTime, endTime);
		float cashScore = scoreBalanceMapper.selectCashScoreByDay(6, beginTime, endTime);
		float shoppingScore = scoreBalanceMapper.selectShoppingScoreByDay(6, beginTime, endTime);
		float elecScore = scoreBalanceMapper.selectElecScoreByDay(6, beginTime, endTime);

		JRecordResult result = new JRecordResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		result.setElecScore(elecScore);
		return result;
	}

	/**
	 * 按两个日期查询个人列表总值
	 */
	@Override
	public List<JBonusScj> selectJBonusScjByDay(Integer zjUid,String beginTime,String endTime){
		//PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjByDay(zjUid, beginTime, endTime);
		//PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		return list;
	}


	/**
	 * 按月数据
	 */
	@Override
	public JRecordResult selectJBonusScjByMonthInfo(String month) {
		float amount = bonusScjMapper.selectScjTotalByMonth(month);
		float cashScore = scoreBalanceMapper.selectCashScoreByMonth(6, month);
		float shoppingScore = scoreBalanceMapper.selectShoppingScoreByMonth(6, month);
		float elecScore = scoreBalanceMapper.selectElecScoreByMonth(6, month);

		JRecordResult result = new JRecordResult();
		result.setAmount(amount);
		result.setCashScore(cashScore);
		result.setShoppingScore(shoppingScore);
		result.setElecScore(elecScore);
		return result;
	}

	/**
	 * 按月查询查询个人列表总值
	 */
	@Override
	public List<JBonusScj> selectJBonusScjByMonth(Integer zjUid,String month){
		//PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectJBonusScjByMonth(zjUid, month);
		//PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		return list;
	}



	/**
	 * 查询个人总值列表
	 */
	@Override
	public List<JBonusScj> selectTotalJBonusScj(){
		//PageHelper.startPage(page, limit);
		List<JBonusScj> list = bonusScjMapper.selectTotalJBonusScj();
		//PageInfo<JBonusScj> pageInfo = new PageInfo<JBonusScj>(list);
		return list;
	}


	/**
	 * 总收益列表
	 */
	@Override
	public List<JRecordTotal> selectScjTotalList(String month) {
		return bonusScjMapper.selectScjTotalList(month);
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
