package com.jyss.yqy.action;


import com.jyss.yqy.entity.JRecordResult;
import com.jyss.yqy.service.JBonusFhjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class JBonusFhjAction {

	@Autowired
	private JBonusFhjService bonusFhjService;


	/**
	 * 查询昨日总金额
	 */
	@RequestMapping("/showFhj/list")
	@ResponseBody
	public JRecordResult selectFhjTotal() {
		JRecordResult result = bonusFhjService.selectJBonusFhj();
		return result;
	}

	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/showFhj/listByWek")
	@ResponseBody
	public JRecordResult selectFhjTotalByWek() {
		JRecordResult result = bonusFhjService.selectJBonusFhjWek();
		return result;
	}

	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/showFhj/listByDay")
	@ResponseBody
	public JRecordResult selectFhjTotalByDay(@RequestParam("beginTime") String beginTime,
											@RequestParam("endTime") String endTime) {
		JRecordResult result = bonusFhjService.selectJBonusFhjByDay(beginTime,endTime);
		return result;
	}

	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/showFhj/listByMonth")
	@ResponseBody
	public JRecordResult selectFhjTotalByMonth(@RequestParam("month") String month) {
		JRecordResult result = bonusFhjService.selectJBonusFhjByMonth(month);
		return result;
	}

}
