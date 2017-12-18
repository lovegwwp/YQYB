package com.jyss.yqy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusFdjResult;
import com.jyss.yqy.service.JBonusFdjService;


@Controller
@RequestMapping("/showFdj")
public class JBonusFdjAction {
	
	@Autowired
	private JBonusFdjService jBonusFdjService;

	
	/**
	 * 查询昨日总金额
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JBonusFdjResult selectFdjTotal(){
		JBonusFdjResult result = jBonusFdjService.selectFdjTotal();
		return result;
	}
	
	
	/**
	 * 查询本周总金额
	 */
	@RequestMapping("/listByWek")
	@ResponseBody
	public JBonusFdjResult selectFdjTotalByWek(){
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByWek();
		return result;
	}
	
	
	/**
	 * 按天查询总金额
	 */
	@RequestMapping("/listByDay")
	@ResponseBody
	public JBonusFdjResult selectFdjTotalByDay(@RequestParam("beginTime")String beginTime,@RequestParam("endTime")String endTime){
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByDay(beginTime, endTime);
		return result;
	}
	
	
	/**
	 * 按月查询总金额
	 */
	@RequestMapping("/listByMonth")
	@ResponseBody
	public JBonusFdjResult selectFdjTotalByMonth(@RequestParam("month")String month){
		JBonusFdjResult result = jBonusFdjService.selectFdjTotalByMonth(month);
		return result;
	}

}
