package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.JBonusGxjService;
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
public class JBonusGxjAction {

	@Autowired
	private JBonusGxjService bonusGxjService;
	@Autowired
	private AccountUserService auService;

	//共享奖统计
	@RequestMapping("/hhrgxjtj")
	public String hhrgxjtjTz() {
		return "hhrgxjtj";
	}

	@RequestMapping("/hhrgxjtjhz")
	public String hhrgxjtjhzTz() {
		return "hhrgxjtjhz";
	}

	/**
	 * 查询昨日总金额
	 */
	@RequestMapping("/showGxj/list")
	@ResponseBody
	public List<JRecordResult> selectGxjTotal() {
		List<JRecordResult> ll = new ArrayList<JRecordResult>();
		JRecordResult result = bonusGxjService.selectJBonusGxj();
		ll.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-共享奖昨日查询");
		return ll;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showGxj/listByWek")
	@ResponseBody
	public List<JRecordResult> selectGxjTotalByWek() {
		List<JRecordResult> llw = new ArrayList<JRecordResult>();
		JRecordResult result = bonusGxjService.selectJBonusGxjWek();
		llw.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-共享奖本周查询");
		return llw;
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showGxj/listByDay")
	@ResponseBody
	public List<JRecordResult> selectGxjTotalByDay(@RequestParam("beginTime") String beginTime,
											 @RequestParam("endTime") String endTime) {
		List<JRecordResult> lld = new ArrayList<JRecordResult>();
		JRecordResult result = bonusGxjService.selectJBonusGxjByDay(beginTime, endTime);
		lld.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-共享奖按天查询");
		return lld;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showGxj/listByMonth")
	@ResponseBody
	public List<JRecordResult> selectGxjTotalByMonth(@RequestParam("month") String month) {
		List<JRecordResult> llm = new ArrayList<JRecordResult>();
		JRecordResult result = bonusGxjService.selectJBonusGxjByMonth(month);
		llm.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-共享奖按月查询");
		return llm;
	}

	/**
	 * 总收益列表
	 */
	@RequestMapping("/showGxj/totalList")
	@ResponseBody
	public List<JRecordTotal> selectGxjTotalList(@RequestParam("month") String month) {
		List<JRecordTotal> list = bonusGxjService.selectGxjTotalList(month);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-共享奖汇总统计");
		return list;
	}

}
