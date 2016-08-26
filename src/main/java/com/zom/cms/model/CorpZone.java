package com.zom.cms.model;
/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层com.zom.cms.model/CorpZone.java <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年8月25日 下午6:07:16<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
public class CorpZone {
	private int corpId;
	private int zoneId;
	public CorpZone(int corpId, int zoneId) {
		super();
		this.corpId = corpId;
		this.zoneId = zoneId;
	}
	public CorpZone() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCorpId() {
		return corpId;
	}
	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	
	
}
