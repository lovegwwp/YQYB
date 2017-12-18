package com.jyss.yqy.entity;

import java.io.Serializable;
import java.util.List;

public class JBonusScjResult implements Serializable{
	
	private Float amount;            //总pv 
	private Long total;              //总条数
	private Float cashScore;         //现金积分
	private Float shoppingScore;     //购物积分
	private List<JBonusScj> list;    
	
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Float getCashScore() {
		return cashScore;
	}
	public void setCashScore(Float cashScore) {
		this.cashScore = cashScore;
	}
	public Float getShoppingScore() {
		return shoppingScore;
	}
	public void setShoppingScore(Float shoppingScore) {
		this.shoppingScore = shoppingScore;
	}
	public List<JBonusScj> getList() {
		return list;
	}
	public void setList(List<JBonusScj> list) {
		this.list = list;
	}
	
	
	

}
