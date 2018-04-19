package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.entity.JRecordTotal;
import com.jyss.yqy.service.JBonusFhjService;
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

	//分红奖统计
	@RequestMapping("/hhrfhjtj")
	public String hhrfhjtjTz() {
		return "hhrfhjtj";
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
		return llm;
	}

	/**
	 * 总收益列表
	 */
	@RequestMapping("/showFhj/totalList")
	@ResponseBody
	public List<JRecordTotal> selectFhjTotalList() {
		List<JRecordTotal> list = bonusFhjService.selectFhjTotalList();
		return list;
	}



}
