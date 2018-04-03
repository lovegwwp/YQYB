package com.jyss.yqy.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyss.yqy.entity.JBonusFdjResult;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.service.JBonusFdjService;
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
	private JBonusFdjService jBonusFdjService;

	@RequestMapping("/fdjtj")
	public String fdjtjTz() {
		return "fdjtj";
	}

	/**
	 * 查询昨日总金额
	 */
	/*
	 * @RequestMapping("/showFdj/list")
	 * 
	 * @ResponseBody public JBonusFdjResult selectFdjTotal() { JBonusFdjResult
	 * result = jBonusFdjService.selectFdjTotal(); return result; }
	 */

	/**
	 * 查询本周总金额
	 */
	// @RequestMapping("/showFdj/listByWek")
	// @ResponseBody
	// public JBonusFdjResult selectFdjTotalByWek() {
	// JBonusFdjResult result = jBonusFdjService.selectFdjTotalByWek();
	// return result;
	// }

	/**
	 * 按天查询总金额
	 */
	// @RequestMapping("/showFdj/listByDay")
	// @ResponseBody
	// public JBonusFdjResult selectFdjTotalByDay(
	// @RequestParam("beginTime") String beginTime,
	// @RequestParam("endTime") String endTime) {
	// JBonusFdjResult result = jBonusFdjService.selectFdjTotalByDay(
	// beginTime, endTime);
	// return result;
	// }

	/**
	 * 按月查询总金额
	 */
	// @RequestMapping("/showFdj/listByMonth")
	// @ResponseBody
	// public JBonusFdjResult selectFdjTotalByMonth(
	// @RequestParam("month") String month) {
	// JBonusFdjResult result = jBonusFdjService.selectFdjTotalByMonth(month);
	// return result;
	// }

	/**
	 * 查询昨日总金额
	 */
	@RequestMapping("/showFdj/list")
	@ResponseBody
	public Page<JBonusFdjResult> selectFdjTotal(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFdjResult> list = new ArrayList<JBonusFdjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFdjResult result = jBonusFdjService.selectFdjTotal();
		list.add(result);
		PageInfo<JBonusFdjResult> pageInfoBy = new PageInfo<JBonusFdjResult>(
				list);
		return new Page<JBonusFdjResult>(pageInfoBy);
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showFdj/listByWek")
	@ResponseBody
	public Page<JBonusFdjResult> selectFdjTotalByWek(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFdjResult> list = new ArrayList<JBonusFdjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByWek();
		list.add(result);
		PageInfo<JBonusFdjResult> pageInfoBy = new PageInfo<JBonusFdjResult>(
				list);
		return new Page<JBonusFdjResult>(pageInfoBy);
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showFdj/listByDay")
	@ResponseBody
	public Page<JBonusFdjResult> selectFdjTotalByDay(
			@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFdjResult> list = new ArrayList<JBonusFdjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByDay(
				beginTime, endTime);
		list.add(result);
		PageInfo<JBonusFdjResult> pageInfoBy = new PageInfo<JBonusFdjResult>(
				list);
		return new Page<JBonusFdjResult>(pageInfoBy);
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showFdj/listByMonth")
	@ResponseBody
	public Page<JBonusFdjResult> selectFdjTotalByMonth(
			@RequestParam("month") String month,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusFdjResult> list = new ArrayList<JBonusFdjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByMonth(month);
		list.add(result);
		PageInfo<JBonusFdjResult> pageInfoBy = new PageInfo<JBonusFdjResult>(
				list);
		return new Page<JBonusFdjResult>(pageInfoBy);
	}

}
