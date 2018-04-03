package com.jyss.yqy.entity;

public class PageFooter {
	  private String bPv;
	  private float pv;
	  private String uName;
	  private float aId;
	  private String aName;
	  private float aPv;

		public String getbPv() {
			return bPv;
		}
		public void setbPv(String bPv) {
			this.bPv = bPv;
		}
		
		public float getPv() {
			return pv;
		}
		public void setPv(float pv) {
			this.pv = pv;
		}
		
		
		public String getuName() {
			return uName;
		}
		public void setuName(String uName) {
			this.uName = uName;
		}
		public float getaId() {
			return aId;
		}
		public void setaId(float aId) {
			this.aId = aId;
		}
		public String getaName() {
			return aName;
		}
		public void setaName(String aName) {
			this.aName = aName;
		}
		public float getaPv() {
			return aPv;
		}
		public void setaPv(float aPv) {
			this.aPv = aPv;
		}
		
		
		public PageFooter(String bPv, float pv, String uName, float aId,
				String aName, float aPv) {
			super();
			this.bPv = bPv;
			this.pv = pv;
			this.uName = uName;
			this.aId = aId;
			this.aName = aName;
			this.aPv = aPv;
		}
		public PageFooter(String bPv, float pv) {
			super();
			this.bPv = bPv;
			this.pv = pv;
}
  
}
