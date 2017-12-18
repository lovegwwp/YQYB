package com.jyss.yqy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.yqy.entity.JBonusGljResult;
import com.jyss.yqy.service.JBonusGljService;

@Controller
@RequestMapping("/showGlj")
public class JBonusGljAction {
	
	@Autowired
	private JBonusGljService jBonusGljService;
	
	
	/**
	 * 昨日总金额
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JBonusGljResult selectGljTotal(){
		JBonusGljResult result = jBonusGljService.selectGljTotal();
		return result;
	}
	
	
	/**
	 * 本周总金额
	 */
	@RequestMapping("/listByWek")
	@ResponseBody
	public JBonusGljResult selectGljTotalByWek(){
		JBonusGljResult result = jBonusGljService.selectGljTotalByWek();
		return result;
	}
	
	
	/**
	 * 两个日期查询总收益
	 */
	@RequestMapping("/listByDay")
	@ResponseBody
	public JBonusGljResult selectGljTotalByDay(@RequestParam("beginTime")String beginTime,@RequestParam("endTime")String endTime){
		JBonusGljResult result = jBonusGljService.selectGljTotalByDay(beginTime, endTime);
		return result;
	}
	
	
	/**
	 * 按月查询总收益
	 */
	@RequestMapping("/listByMonth")
	@ResponseBody
	public JBonusGljResult selectGljTotalByMonth(@RequestParam("month")String month){
		JBonusGljResult result = jBonusGljService.selectGljTotalByMonth(month);
		return result;
	}

}
