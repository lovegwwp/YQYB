package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.service.AccountUserService;
import com.jyss.yqy.service.JBonusCjService;
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
public class JBonusCjAction {

	@Autowired
	private JBonusCjService bonusCjService;
	@Autowired
	private AccountUserService auService;

	//层奖统计
	@RequestMapping("/hhrcjtj")
	public String hhrcjtjTz() {
		return "hhrcjtj";
	}

	/**
	 * 查询当日总金额
	 */
	@RequestMapping("/showCj/list")
	@ResponseBody
	public List<JRecordResult> selectCjTotal() {
		List<JRecordResult> ll = new ArrayList<JRecordResult>();
		JRecordResult result = bonusCjService.selectJBonusCj();
		ll.add(result);
		Subject us = SecurityUtils.getSubject();
		String lName = us.getPrincipal().toString();
		auService.addLog(lName,"奖项统计-层奖当日查询");
		return ll;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showCj/listByWek")
	@ResponseBody
	public List<JRecordResult> selectCjTotalByWek() {
		List<JRecordResult> llw = new ArrayList<JRecordResult>();
		JRecordResult result = bonusCjService.selectJBonusCjWek();
		llw.add(result);
		return llw;
	}


	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showCj/listByDay")
	@ResponseBody
	public List<JRecordResult> selectCjTotalByDay(@RequestParam("beginTime") String beginTime,
											@RequestParam("endTime") String endTime) {
		List<JRecordResult> lld = new ArrayList<JRecordResult>();
		JRecordResult result = bonusCjService.selectJBonusCjByDay(beginTime,endTime);
		lld.add(result);
		return lld;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showCj/listByMonth")
	@ResponseBody
	public List<JRecordResult> selectCjTotalByMonth(@RequestParam("month") String month) {
		List<JRecordResult> llm = new ArrayList<JRecordResult>();
		JRecordResult result = bonusCjService.selectJBonusCjByMonth(month);
		llm.add(result);
		return llm;
	}


	/**
	 * 总收益列表
	 */
	@RequestMapping("/showCj/totalList")
	@ResponseBody
	public List<JRecordTotal> selectCjTotalList() {
		List<JRecordTotal> list = bonusCjService.selectCjTotalList();
		return list;
	}

}
