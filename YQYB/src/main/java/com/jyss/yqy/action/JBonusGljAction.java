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
import com.jyss.yqy.entity.JBonusGljResult;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.service.JBonusGljService;

@Controller
public class JBonusGljAction {

	@Autowired
	private JBonusGljService jBonusGljService;

	@RequestMapping("/gljtj")
	public String gljtjTz() {
		return "gljtj";
	}

	/**
	 * 昨日总金额
	 */
	// @RequestMapping("/showGlj/list")
	// @ResponseBody
	// public JBonusGljResult selectGljTotal(){
	// JBonusGljResult result = jBonusGljService.selectGljTotal();
	// return result;
	// }

	/**
	 * 本周总金额
	 */
	// @RequestMapping("/showGlj/listByWek")
	// @ResponseBody
	// public JBonusGljResult selectGljTotalByWek(){
	// JBonusGljResult result = jBonusGljService.selectGljTotalByWek();
	// return result;
	// }

	/**
	 * 两个日期查询总收益
	 */
	// @RequestMapping("/showGlj/listByDay")
	// @ResponseBody
	// public JBonusGljResult
	// selectGljTotalByDay(@RequestParam("beginTime")String
	// beginTime,@RequestParam("endTime")String endTime){
	// JBonusGljResult result = jBonusGljService.selectGljTotalByDay(beginTime,
	// endTime);
	// return result;
	// }

	/**
	 * 按月查询总收益
	 */
	// @RequestMapping("/showGlj/listByMonth")
	// @ResponseBody
	// public JBonusGljResult selectGljTotalByMonth(@RequestParam("month")String
	// month){
	// JBonusGljResult result = jBonusGljService.selectGljTotalByMonth(month);
	// return result;
	// }

	/**
	 * 昨日总金额
	 */
	@RequestMapping("/showGlj/list")
	@ResponseBody
	public Page<JBonusGljResult> selectGljTotal(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusGljResult> list = new ArrayList<JBonusGljResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusGljResult result = jBonusGljService.selectGljTotal();
		list.add(result);
		PageInfo<JBonusGljResult> pageInfoBy = new PageInfo<JBonusGljResult>(
				list);
		return new Page<JBonusGljResult>(pageInfoBy);
	}

	/**
	 * 本周总金额
	 */
	@RequestMapping("/showGlj/listByWek")
	@ResponseBody
	public Page<JBonusGljResult> selectGljTotalByWek(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusGljResult> list = new ArrayList<JBonusGljResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusGljResult result = jBonusGljService.selectGljTotalByWek();
		list.add(result);
		PageInfo<JBonusGljResult> pageInfoBy = new PageInfo<JBonusGljResult>(
				list);
		return new Page<JBonusGljResult>(pageInfoBy);
	}

	/**
	 * 两个日期查询总收益
	 */
	@RequestMapping("/showGlj/listByDay")
	@ResponseBody
	public Page<JBonusGljResult> selectGljTotalByDay(
			@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusGljResult> list = new ArrayList<JBonusGljResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusGljResult result = jBonusGljService.selectGljTotalByDay(
				beginTime, endTime);
		list.add(result);
		PageInfo<JBonusGljResult> pageInfoBy = new PageInfo<JBonusGljResult>(
				list);
		return new Page<JBonusGljResult>(pageInfoBy);
	}

	/**
	 * 按月查询总收益
	 */
	@RequestMapping("/showGlj/listByMonth")
	@ResponseBody
	public Page<JBonusGljResult> selectGljTotalByMonth(
			@RequestParam("month") String month,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		List<JBonusGljResult> list = new ArrayList<JBonusGljResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusGljResult result = jBonusGljService.selectGljTotalByMonth(month);
		list.add(result);
		PageInfo<JBonusGljResult> pageInfoBy = new PageInfo<JBonusGljResult>(
				list);
		return new Page<JBonusGljResult>(pageInfoBy);
	}

}
