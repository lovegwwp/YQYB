package com.jyss.yqy.entity;

import java.io.Serializable;
import java.util.List;

public class JBonusScjResult implements Serializable{
	
	private Float total;             //总pv
	private Float cashScore;         //现金积分
	private Float shoppingScore;     //购物积分
	private List<JBonusScj> list;
	
	
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
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
