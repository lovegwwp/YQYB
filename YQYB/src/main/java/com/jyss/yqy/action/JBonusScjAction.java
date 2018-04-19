package com.jyss.yqy.action;

import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.service.AccountUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.service.JBonusScjService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class JBonusScjAction {

	@Autowired
	private JBonusScjService bonusScjService;
	@Autowired
	private AccountUserService auService;

	@RequestMapping("/scjtj")
	public String scjtjTz() {
		return "scjtj";
	}

	@RequestMapping("/hhrljtj")
	public String hhrljtjTz() {
		return "hhrljtj";
	}

	@RequestMapping("/hhrljtjzjzl")
	public String hhrljtjzjzlTz() {
		return "hhrljtjzjzl";
	}

	@RequestMapping("/hhrlj")
	public String hhrljTz() {
		return "hhrlj";
	}




	/**
	 * 昨日数据===统计
	 */
	@RequestMapping("/showScj/info")
	@ResponseBody
	public List<JRecordResult> selectJBonusScjInfo() {
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		List<JRecordResult> ll = new ArrayList<JRecordResult>();
		JRecordResult result = bonusScjService.selectJBonusScjInfo();
		ll.add(result);
		auService.addLog(lName,"奖项统计-市场奖昨日统计");
		return ll;
	}

	/**
	 * 昨日列表
	 */
	@RequestMapping("/showScj/list")
	@ResponseBody
	public List<JBonusScj> selectJBonusScj(@RequestParam(value = "zjUid") Integer zjUid) {
		List<JBonusScj> result = bonusScjService.selectJBonusScj(zjUid);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖昨日查询");
		return result;
	}



	/**
	 * 本周数据===统计
	 */
	@RequestMapping("/showScj/wekInfo")
	@ResponseBody
	public List<JRecordResult> selectJBonusScjWekInfo() {
		List<JRecordResult> llw = new ArrayList<JRecordResult>();
		JRecordResult result = bonusScjService.selectJBonusScjWekInfo();
		llw.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖本周统计");
		return llw;
	}

	/**
	 * 本周列表
	 */
	@RequestMapping("/showScj/listByWek")
	@ResponseBody
	public List<JBonusScj> selectJBonusScjWek(@RequestParam(value = "zjUid") Integer zjUid) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjWek(zjUid);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖本周查询");
		return result;
	}



	/**
	 * 两个日期数据===统计
	 */
	@RequestMapping("/showScj/dayInfo")
	@ResponseBody
	public List<JRecordResult> selectJBonusScjByDayInfo(@RequestParam("beginTime") String beginTime,
												  @RequestParam("endTime") String endTime) {
		List<JRecordResult> lld = new ArrayList<JRecordResult>();
		JRecordResult result = bonusScjService.selectJBonusScjByDayInfo(beginTime, endTime);
		lld.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖日期统计");
		return lld;
	}

	/**
	 * 两个日期查询个人总值列表
	 */
	@RequestMapping("/showScj/listByDay")
	@ResponseBody
	public List<JBonusScj> selectJBonusScjByDay(@RequestParam(value = "zjUid") Integer zjUid,
												@RequestParam("beginTime") String beginTime,
												@RequestParam("endTime") String endTime) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjByDay(zjUid, beginTime, endTime);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖日期查询");
		return result;
	}



	/**
	 * 按月数据===统计
	 */
	@RequestMapping("/showScj/monthInfo")
	@ResponseBody
	public List<JRecordResult> selectJBonusScjByMonthInfo(@RequestParam("month") String month) {
		List<JRecordResult> llm = new ArrayList<JRecordResult>();
		JRecordResult result = bonusScjService.selectJBonusScjByMonthInfo(month);
		llm.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖按月统计");
		return llm;
	}

	/**
	 * 按月查询查询个人总值列表
	 */
	@RequestMapping("/showScj/listByMonth")
	@ResponseBody
	public List<JBonusScj> selectJBonusScjByMonth(@RequestParam(value = "zjUid") Integer zjUid,
												  @RequestParam("month") String month) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjByMonth(zjUid, month);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖按月查询");
		return result;
	}


	/**
	 * 查询个人总值列表
	 */
	@RequestMapping("/showScj/total")
	@ResponseBody
	public List<JBonusScj> selectTotalJBonusScj() {
		List<JBonusScj> result = bonusScjService.selectTotalJBonusScj();
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-市场奖历史查询");
		return result;
	}


	/**
	 * 总收益列表
	 */
	@RequestMapping("/showScj/totalList")
	@ResponseBody
	public List<JRecordTotal> selectScjTotalList() {
		List<JRecordTotal> list = bonusScjService.selectScjTotalList();
		return list;
	}


}
