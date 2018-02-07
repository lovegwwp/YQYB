package com.jyss.yqy.entity;

import java.util.Date;

public class BaseShare {
	
	private int id;
	private String content;
	private String shareKey;
	private String title;
	private int status;
	private String linkPic;
	private String linkUrl;
	private String cjsj;
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getShareKey() {
		return shareKey;
	}
	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLinkPic() {
		return linkPic;
	}
	public void setLinkPic(String linkPic) {
		this.linkPic = linkPic;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	
	

}
