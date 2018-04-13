package com.jyss.yqy.action;

import com.jyss.yqy.entity.JRecordResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusScj;
import com.jyss.yqy.service.JBonusScjService;

import java.util.List;

@Controller
public class JBonusScjAction {

	@Autowired
	private JBonusScjService bonusScjService;

	@RequestMapping("/scjtj")
	public String scjtjTz() {
		return "scjtj";
	}

	@RequestMapping("/hhrljtj")
	public String hhrljtjTz() {
		return "hhrljtjTz";
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
	public List<JBonusScj> selectJBonusScj(@RequestParam(value = "zjUid") Integer zjUid) {
		List<JBonusScj> result = bonusScjService.selectJBonusScj(zjUid);
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
	public List<JBonusScj> selectJBonusScjWek(@RequestParam(value = "zjUid") Integer zjUid) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjWek(zjUid);
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
	public List<JBonusScj> selectJBonusScjByDay(@RequestParam(value = "zjUid") Integer zjUid,
												@RequestParam("beginTime") String beginTime,
												@RequestParam("endTime") String endTime) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjByDay(zjUid, beginTime, endTime);
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
	public List<JBonusScj> selectJBonusScjByMonth(@RequestParam(value = "zjUid") Integer zjUid,
												  @RequestParam("month") String month) {
		List<JBonusScj> result = bonusScjService.selectJBonusScjByMonth(zjUid, month);
		return result;
	}


	/**
	 * 查询个人总值列表
	 */
	@RequestMapping("/showScj/total")
	@ResponseBody
	public List<JBonusScj> selectTotalJBonusScj() {
		List<JBonusScj> result = bonusScjService.selectTotalJBonusScj();
		return result;
	}


}
