package com.jyss.yqy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusFxjResult;
import com.jyss.yqy.service.JBonusFxjService;


@Controller
@RequestMapping("/showFxj")
public class JBonusFxjAction {
	
	@Autowired
	private JBonusFxjService jBonusFxjService;
	
	
	/**
	 * 昨日总金额
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JBonusFxjResult selectFxjTotal(){
		JBonusFxjResult result = jBonusFxjService.selectFxjTotal();
		return result;
	}
	
	
	/**
	 * 本周总金额
	 */
	@RequestMapping("/listByWek")
	@ResponseBody
	public JBonusFxjResult selectFxjTotalByWek(){
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByWek();
		return result;
	}
	
	
	/**
	 * 按两个日期查询总收益
	 */
	@RequestMapping("/listByDay")
	@ResponseBody
	public JBonusFxjResult selectFxjTotalByDay(@RequestParam("beginTime")String beginTime,@RequestParam("endTime")String endTime){
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByDay(beginTime, endTime);
		return result;
	}
	
	
	/**
	 * 按月查询总收益
	 */
	@RequestMapping("/listByMonth")
	@ResponseBody
	public JBonusFxjResult selectFxjTotalByMonth(@RequestParam("month")String month){
		JBonusFxjResult result = jBonusFxjService.selectFxjTotalByMonth(month);
		return result;
	}

}
