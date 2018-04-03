package com.jyss.yqy.action;

import com.jyss.yqy.entity.JRecordResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.entity.Page;
import com.jyss.yqy.service.JBonusScjService;

@Controller
public class JBonusScjAction {

	@Autowired
	private JBonusScjService bonusScjService;

	@RequestMapping("/scjtj")
	public String scjtjTz() {
		return "scjtj";
	}



	/**
	 * 昨日数据
	 */
	@RequestMapping("/showScj/info")
	@ResponseBody
	public JRecordResult selectJBonusScjInfo() {
		JRecordResult result = bonusScjService.selectJBonusScjInfo();
		return result;
	}

	/**
	 * 昨日列表
	 */
	@RequestMapping("/showScj/list")
	@ResponseBody
	public Page<JBonusScj> selectJBonusScj(@RequestParam(value = "zjUid") Integer zjUid,
										   @RequestParam(value = "page", required = true) int page,
										   @RequestParam(value = "rows", required = true) int rows) {
		Page<JBonusScj> result = bonusScjService.selectJBonusScj(zjUid, page, rows);
		return result;
	}



	/**
	 * 本周数据
	 */
	@RequestMapping("/showScj/wekInfo")
	@ResponseBody
	public JRecordResult selectJBonusScjWekInfo() {
		JRecordResult result = bonusScjService.selectJBonusScjWekInfo();
		return result;
	}

	/**
	 * 本周列表
	 */
	@RequestMapping("/showScj/listByWek")
	@ResponseBody
	public Page<JBonusScj> selectJBonusScjWek(@RequestParam(value = "zjUid") Integer zjUid,
											  @RequestParam(value = "page", required = true) int page,
											  @RequestParam(value = "rows", required = true) int rows) {
		Page<JBonusScj> result = bonusScjService.selectJBonusScjWek(zjUid, page, rows);
		return result;
	}



	/**
	 * 两个日期数据
	 */
	@RequestMapping("/showScj/dayInfo")
	@ResponseBody
	public JRecordResult selectJBonusScjByDayInfo(@RequestParam("beginTime") String beginTime,
												  @RequestParam("endTime") String endTime) {
		JRecordResult result = bonusScjService.selectJBonusScjByDayInfo(beginTime, endTime);
		return result;
	}

	/**
	 * 两个日期查询个人总值列表
	 */
	@RequestMapping("/showScj/listByDay")
	@ResponseBody
	public Page<JBonusScj> selectJBonusScjByDay(@RequestParam(value = "zjUid") Integer zjUid,
												@RequestParam(value = "page", required = true) int page,
												@RequestParam(value = "rows", required = true) int rows,
												@RequestParam("beginTime") String beginTime,
												@RequestParam("endTime") String endTime) {
		Page<JBonusScj> result = bonusScjService.selectJBonusScjByDay(zjUid, page, rows, beginTime, endTime);
		return result;
	}



	/**
	 * 按月数据
	 */
	@RequestMapping("/showScj/monthInfo")
	@ResponseBody
	public JRecordResult selectJBonusScjByMonthInfo(@RequestParam("month") String month) {
		JRecordResult result = bonusScjService.selectJBonusScjByMonthInfo(month);
		return result;
	}

	/**
	 * 按月查询查询个人总值列表
	 */
	@RequestMapping("/showScj/listByMonth")
	@ResponseBody
	public Page<JBonusScj> selectJBonusScjByMonth(@RequestParam(value = "zjUid") Integer zjUid,
												  @RequestParam(value = "page", required = true) int page,
												  @RequestParam(value = "rows", required = true) int rows,
												  @RequestParam("month") String month) {
		Page<JBonusScj> result = bonusScjService.selectJBonusScjByMonth(zjUid, page, rows, month);
		return result;
	}




}
