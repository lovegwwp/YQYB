package com.jyss.yqy.entity;

import java.util.Date;

public class ScoreBalance {

	private Integer id;
	private Integer end;// 1=A端 2=B端'
	private String uUuid;// 用户uuid
	private Integer category;// 积分来源或去向[1=A端取现，2=A端消费，3=A端返佣，4=管理奖，5=辅导奖，6=分销奖，7=市场奖，8=B端消费]
	private Integer type;// 1=收入 2=支出
	private Float score;// 积分数额
	private Float jyScore;// 结余数额
	private Date createdAt;
	private Date jsTime;   //结算时间用
	private String title;// 用户uuid
	private String orderSn;// 订单号
	private String cjsj;// /格式化创建时间
	private Integer status;// 1=收入 2=支出
	private Integer secoCate;//1支付宝，2微信，3线下充值
	private String zzCode;  //
	private String detail;  //凭证说明



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getuUuid() {
		return uUuid;
	}

	public void setuUuid(String uUuid) {
		this.uUuid = uUuid;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Float getJyScore() {
		return jyScore;
	}

	public void setJyScore(Float jyScore) {
		this.jyScore = jyScore;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getJsTime() {
		return jsTime;
	}

	public void setJsTime(Date jsTime) {
		this.jsTime = jsTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSecoCate() {
		return secoCate;
	}

	public void setSecoCate(Integer secoCate) {
		this.secoCate = secoCate;
	}

	public String getZzCode() {
		return zzCode;
	}

	public void setZzCode(String zzCode) {
		this.zzCode = zzCode;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
