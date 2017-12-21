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
import com.jyss.yqy.entity.JBonusScjResult;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.service.JBonusScjService;

@Controller
public class JBonusScjAction {

	@Autowired
	private JBonusScjService bonusScjService;

	/**
	 * 昨日列表
	 */
	// @RequestMapping("/showScj/list")
	// @ResponseBody
	// public JBonusScjResult selectJBonusScj(
	// @RequestParam(value = "page", required = true) int page,
	// @RequestParam(value = "rows", required = true) int rows) {
	// JBonusScjResult result = bonusScjService.selectJBonusScj(page, rows);
	// return result;
	// }

	/**
	 * 本周列表
	 */
	// @RequestMapping("/showScj/listByWek")
	// @ResponseBody
	// public JBonusScjResult selectJBonusScjWek(
	// @RequestParam(value = "page", required = true) int page,
	// @RequestParam(value = "rows", required = true) int rows) {
	// JBonusScjResult result = bonusScjService
	// .selectJBonusScjWek(page, rows);
	// return result;
	// }

	/**
	 * 两个日期查询个人列表总值
	 */
	// @RequestMapping("/showScj/listByDay")
	// @ResponseBody
	// public JBonusScjResult selectJBonusScjByDay(
	// @RequestParam(value = "page", required = true) int page,
	// @RequestParam(value = "rows", required = true) int rows,
	// @RequestParam("beginTime") String beginTime,
	// @RequestParam("endTime") String endTime) {
	// JBonusScjResult result = bonusScjService.selectJBonusScjByDay(page,
	// rows, beginTime, endTime);
	// return result;
	// }

	/**
	 * 按月查询查询个人列表总值
	 */
	// @RequestMapping("/showScj/listByMonth")
	// @ResponseBody
	// public JBonusScjResult selectJBonusScjByMonth(
	// @RequestParam(value = "page", required = true) int page,
	// @RequestParam(value = "rows", required = true) int rows,
	// @RequestParam("month") String month) {
	// JBonusScjResult result = bonusScjService.selectJBonusScjByMonth(page,
	// rows, month);
	// return result;
	// }

	/**
	 * 昨日列表
	 */
	@RequestMapping("/showScj/list")
	@ResponseBody
	public Page<JBonusScjResult> selectJBonusScj(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusScjResult> list = new ArrayList<JBonusScjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusScjResult result = bonusScjService.selectJBonusScj(page, rows);
		list.add(result);
		PageInfo<JBonusScjResult> pageInfoBy = new PageInfo<JBonusScjResult>(
				list);
		return new Page<JBonusScjResult>(pageInfoBy);
	}

	/**
	 * 本周列表
	 */
	@RequestMapping("/showScj/listByWek")
	@ResponseBody
	public Page<JBonusScjResult> selectJBonusScjWek(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusScjResult> list = new ArrayList<JBonusScjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusScjResult result = bonusScjService.selectJBonusScjWek(page, rows);
		list.add(result);
		PageInfo<JBonusScjResult> pageInfoBy = new PageInfo<JBonusScjResult>(
				list);
		return new Page<JBonusScjResult>(pageInfoBy);
	}

	/**
	 * 两个日期查询个人列表总值
	 */
	@RequestMapping("/showScj/listByDay")
	@ResponseBody
	public Page<JBonusScjResult> selectJBonusScjByDay(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows,
			@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime) {
		List<JBonusScjResult> list = new ArrayList<JBonusScjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusScjResult result = bonusScjService.selectJBonusScjByDay(page,
				rows, beginTime, endTime);
		list.add(result);
		PageInfo<JBonusScjResult> pageInfoBy = new PageInfo<JBonusScjResult>(
				list);
		return new Page<JBonusScjResult>(pageInfoBy);
	}

	/**
	 * 按月查询查询个人列表总值
	 */
	@RequestMapping("/showScj/listByMonth")
	@ResponseBody
	public Page<JBonusScjResult> selectJBonusScjByMonth(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows,
			@RequestParam("month") String month) {
		List<JBonusScjResult> list = new ArrayList<JBonusScjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusScjResult result = bonusScjService.selectJBonusScjByMonth(page,
				rows, month);
		list.add(result);
		PageInfo<JBonusScjResult> pageInfoBy = new PageInfo<JBonusScjResult>(
				list);
		return new Page<JBonusScjResult>(pageInfoBy);
	}

}
