package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.service.JBonusCjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JBonusCjAction {

	@Autowired
	private JBonusCjService bonusCjService;


	/**
	 * 查询当日总金额
	 */
	@RequestMapping("/showCj/list")
	@ResponseBody
	public JRecordResult selectCjTotal() {
		JRecordResult result = bonusCjService.selectJBonusCj();
		return result;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showCj/listByWek")
	@ResponseBody
	public JRecordResult selectCjTotalByWek() {
		JRecordResult result = bonusCjService.selectJBonusCjWek();
		return result;
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showCj/listByDay")
	@ResponseBody
	public JRecordResult selectCjTotalByDay(@RequestParam("beginTime") String beginTime,
											@RequestParam("endTime") String endTime) {
		JRecordResult result = bonusCjService.selectJBonusCjByDay(beginTime,endTime);
		return result;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showCj/listByMonth")
	@ResponseBody
	public JRecordResult selectCjTotalByMonth(@RequestParam("month") String month) {
		JRecordResult result = bonusCjService.selectJBonusCjByMonth(month);
		return result;
	}

}
