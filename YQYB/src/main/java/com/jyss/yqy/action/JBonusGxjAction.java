package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.service.JBonusGxjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JBonusGxjAction {

	@Autowired
	private JBonusGxjService bonusGxjService;


	/**
	 * 查询当日总金额
	 */
	@RequestMapping("/showGxj/list")
	@ResponseBody
	public JRecordResult selectGxjTotal() {
		JRecordResult result = bonusGxjService.selectJBonusGxj();
		return result;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showGxj/listByWek")
	@ResponseBody
	public JRecordResult selectGxjTotalByWek() {
		JRecordResult result = bonusGxjService.selectJBonusGxjWek();
		return result;
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showGxj/listByDay")
	@ResponseBody
	public JRecordResult selectGxjTotalByDay(@RequestParam("beginTime") String beginTime,
											 @RequestParam("endTime") String endTime) {
		JRecordResult result = bonusGxjService.selectJBonusGxjByDay(beginTime, endTime);
		return result;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showGxj/listByMonth")
	@ResponseBody
	public JRecordResult selectGxjTotalByMonth(@RequestParam("month") String month) {
		JRecordResult result = bonusGxjService.selectJBonusGxjByMonth(month);
		return result;
	}

}
