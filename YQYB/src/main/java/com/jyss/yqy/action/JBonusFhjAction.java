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
import com.jyss.yqy.entity.Xtcl;
import com.jyss.yqy.service.JBonusFhjService;
import com.jyss.yqy.service.XtclService;

@Controller
public class JBonusFhjAction {

	@Autowired
	private JBonusFhjService jBonusFhjService;
	@Autowired
	private XtclService xtclService;

	@RequestMapping("/fhjtj")
	public String fhjtjTz() {
		return "fhjtj";
	}


	/**
	 * 当日金额
	 */
	@RequestMapping("/showFhj/list")
	@ResponseBody
	public Page<JBonusFxjResult> selectGljTotal(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		Xtcl xtcl = xtclService.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());         //0.7
		
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFhjService.selectFhjTotal(float1);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	
	/**
	 * 本周总金额
	 */
	@RequestMapping("/showFhj/listByWek")
	@ResponseBody
	public Page<JBonusFxjResult> selectGljTotalByWek(
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		Xtcl xtcl = xtclService.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());         //0.7
		
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFhjService.selectFhjTotalByWek(float1);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	/**
	 * 两个日期查询总收益
	 */
	@RequestMapping("/showFhj/listByDay")
	@ResponseBody
	public Page<JBonusFxjResult> selectGljTotalByDay(
			@RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		Xtcl xtcl = xtclService.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());         //0.7
		
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFhjService.selectFhjTotalByDay(float1,beginTime, endTime);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(
				list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

	/**
	 * 按月查询总收益
	 */
	@RequestMapping("/showFhj/listByMonth")
	@ResponseBody
	public Page<JBonusFxjResult> selectGljTotalByMonth(
			@RequestParam("month") String month,
			@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "rows", required = true) int rows) {
		Xtcl xtcl = xtclService.getClsValue("jjbl_type", "xj");      //现金积分比例
		float float1 = Float.parseFloat(xtcl.getBz_value());         //0.7
		
		List<JBonusFxjResult> list = new ArrayList<JBonusFxjResult>();
		PageHelper.startPage(page, rows);// 分页语句
		JBonusFxjResult result = jBonusFhjService.selectFhjTotalByMonth(float1,month);
		list.add(result);
		PageInfo<JBonusFxjResult> pageInfoBy = new PageInfo<JBonusFxjResult>(list);
		return new Page<JBonusFxjResult>(pageInfoBy);
	}

}
