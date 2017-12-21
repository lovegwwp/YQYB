package com.jyss.yqy.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.JBonusFxjResult;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.service.JBonusFxjService;

@Controller
public class JBonusFxjAction {

	@Autowired
	private JBonusFxjService jBonusFxjService;

	@RequestMapping("/fxjtj")
	public String fxjtjTz() {
		return "fxjtj";
	}

	/**
	 * 昨日总金额
	 */
	// @RequestMapping("/showFxj/list")
	// @ResponseBody
	// public JBonusFxjResult selectFxjTotal() {
	// JBonusFxjResult result = jBonusFxjService.selectFxjTotal();
	// return result;
	// }

	/**
	 * 本周总金额
	 */
	// @RequestMapping("/showFxj/listByWek")
	// @ResponseBody
	// public JBonusFxjResult selectFxjTotalByWek() {
	// JBonusFxjResult result = jBonusFxjService.selectFxjTotalByWek();
	// return result;
	// }

	/**
	 * 按两个日期查询总收益
	 */
	// @RequestMapping("/showFxj/listByDay")
	// @ResponseBody
	// public JBonusFxjResult selectFxjTotalByDay(
	// @RequestParam("beginTime") String beginTime,
	// @RequestParam("endTime") String endTime) {
	// JBonusFxjResult result = jBonusFxjService.selectFxjTotalByDay(
	// beginTime, endTime);
	// return result;
	// }

	/**
	 * 按月查询总收益
	 */
	// @RequestMapping("/showFxj/listByMonth")
	// @ResponseBody
	// public JBonusFxjResult selectFxjTotalByMonth(
	// @RequestParam("month") String month) {
	// JBonusFxjResult result = jBonusFxjService.selectFxjTotalByMonth(month);
	// return result;
	// }

	/**
	 * 昨日总金额
	 */
	@RequestMapping("/showFxj/list")
	@ResponseBody
	public Page<JBonusFxjResult> selectFxjTotal(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFxjService.selectFxjTotal();
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	/**
	 * 本周总金额
	 */
	@RequestMapping("/showFxj/listByWek")
	@ResponseBody
	public Page<JBonusFxjResult> selectFxjTotalByWek(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByWek();
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	/**
	 * 按两个日期查询总收益
	 */
	@RequestMapping("/showFxj/listByDay")
	@ResponseBody
	public Page<JBonusFxjResult> selectFxjTotalByDay(
			@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByDay(
				beginTime, endTime);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	/**
	 * 按月查询总收益
	 */
	@RequestMapping("/showFxj/listByMonth")
	@ResponseBody
	public Page<JBonusFxjResult> selectFxjTotalByMonth(
			@RequestParam("month") String month,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByMonth(month);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

}
