package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.JBonusFhjService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class JBonusFhjAction {

	@Autowired
	private JBonusFhjService bonusFhjService;
	@Autowired
	private AccountUserService auService;

	//分红奖统计
	@RequestMapping("/hhrfhjtj")
	public String hhrfhjtjTz() {
		return "hhrfhjtj";
	}

	@RequestMapping("/hhrfhjtjhz")
	public String hhrfhjtjhzTz() {
		return "hhrfhjtjhz";
	}

	/**
	 * 查询当日总金额
	 */
	@RequestMapping("/showFhj/list")
	@ResponseBody
	public List<JRecordResult> selectFhjTotal() {
		List<JRecordResult> ll = new ArrayList<JRecordResult>();
		JRecordResult result = bonusFhjService.selectJBonusFhj();
		ll.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-分红奖当日查询");
		return ll;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showFhj/listByWek")
	@ResponseBody
	public List<JRecordResult> selectFhjTotalByWek() {
		List<JRecordResult> llw = new ArrayList<JRecordResult>();
		JRecordResult result = bonusFhjService.selectJBonusFhjWek();
		llw.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-分红奖本周查询");
		return llw;
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showFhj/listByDay")
	@ResponseBody
	public List<JRecordResult> selectFhjTotalByDay(@RequestParam("beginTime") String beginTime,
											 @RequestParam("endTime") String endTime) {
		List<JRecordResult> lld = new ArrayList<JRecordResult>();
		JRecordResult result = bonusFhjService.selectJBonusFhjByDay(beginTime,endTime);
		lld.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-分红奖按天查询");
		return lld;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showFhj/listByMonth")
	@ResponseBody
	public List<JRecordResult> selectFhjTotalByMonth(@RequestParam("month") String month) {
		List<JRecordResult> llm = new ArrayList<JRecordResult>();
		JRecordResult result = bonusFhjService.selectJBonusFhjByMonth(month);
		llm.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-分红奖按月查询");
		return llm;
	}

	/**
	 * 总收益列表
	 */
	@RequestMapping("/showFhj/totalList")
	@ResponseBody
	public List<JRecordTotal> selectFhjTotalList(@RequestParam("month") String month) {
		List<JRecordTotal> list = bonusFhjService.selectFhjTotalList(month);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-分红奖汇总统计");
		return list;
	}



}
