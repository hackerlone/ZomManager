package com.zom.cms.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <strong> 描&nbsp;&nbsp;&nbsp;&nbsp;述：</strong> 持久层com.zom.cms.model/charge.java <p>
 * <strong> 作&nbsp;&nbsp;&nbsp;&nbsp;者：</strong> 王家明 <p>
 * <strong> 编写时间：</strong>2016年8月23日 上午9:20:03<p>
 * <strong> 公&nbsp;&nbsp;&nbsp;&nbsp;司：</strong> 成都蓝海飞鱼科技有限公司 <p>
 * <strong> 版&nbsp;&nbsp;&nbsp;&nbsp;本：</strong> 1.0 <p>
 */
public class Charge {
	private int id;
	private int cropId;
	private BigDecimal money;
	private Date chargeTime;
	private String chargeNote;
	public Charge(int id, int cropId, BigDecimal money, Date chargeTime, String chargeNote) {
		super();
		this.id = id;
		this.cropId = cropId;
		this.money = money;
		this.chargeTime = chargeTime;
		this.chargeNote = chargeNote;
	}
	public Charge() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCropId() {
		return cropId;
	}
	public void setCropId(int cropId) {
		this.cropId = cropId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Date getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}
	public String getChargeNote() {
		return chargeNote;
	}
	public void setChargeNote(String chargeNote) {
		this.chargeNote = chargeNote;
	}
	
	
	
	
	
	
}
