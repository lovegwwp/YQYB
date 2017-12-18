package com.jyss.yqy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusScjResult;
import com.jyss.yqy.service.JBonusScjService;

@Controller
@RequestMapping("/showScj")
public class JBonusScjAction {
	
	@Autowired
	private JBonusScjService bonusScjService;
	
	
	/**
	 * 昨日列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JBonusScjResult selectJBonusScj(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "limit", required = true) int limit){
		JBonusScjResult result = bonusScjService.selectJBonusScj(page, limit);
		return result;
	}
	
	
	/**
	 * 本周列表
	 */
	@RequestMapping("/listByWek")
	@ResponseBody
	public JBonusScjResult selectJBonusScjWek(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "limit", required = true) int limit){
		JBonusScjResult result = bonusScjService.selectJBonusScjWek(page, limit);
		return result;
	}
	
	
	/**
	 * 两个日期查询个人列表总值
	 */
	@RequestMapping("/listByDay")
	@ResponseBody
	public JBonusScjResult selectJBonusScjByDay(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "limit", required = true) int limit,
			@RequestParam("beginTime")String beginTime,@RequestParam("endTime")String endTime){
		JBonusScjResult result = bonusScjService.selectJBonusScjByDay(page, limit, beginTime, endTime);
		return result;
	}
	
	
	/**
	 * 按月查询查询个人列表总值
	 */
	@RequestMapping("/listByMonth")
	@ResponseBody
	public JBonusScjResult selectJBonusScjByMonth(@RequestParam(value = "page", required = true) int page,
			@RequestParam(value = "limit", required = true) int limit,
			@RequestParam("month") String month){
		JBonusScjResult result = bonusScjService.selectJBonusScjByMonth(page, limit, month);
		return result;
	}
	

}
