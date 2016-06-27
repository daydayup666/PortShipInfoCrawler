package com.xmu.javaBean;

import java.io.Serializable;

public class ShipMainInfo implements Serializable{
	private String shipName;         //船名
	private String shipChineseName;  //船舶中文名字
	private String callLetter;       //呼号
	private String nationality;      //国籍
	private String shipOwner;        //船东
	private String shipType;         //船舶类型
	private int shipLength;          //船长
	private int shipWidth;           //船宽
	private int draft;            //吃水米数
	private String lastTime;      //最后时间
	private int MMSI;              //水上移动通信业务标识码
	private int IMO;             //船舶代码
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipChineseName() {
		return shipChineseName;
	}
	public void setShipChineseName(String shipChineseName) {
		this.shipChineseName = shipChineseName;
	}
	public String getCallLetter() {
		return callLetter;
	}
	public void setCallLetter(String callLetter) {
		this.callLetter = callLetter;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getShipOwner() {
		return shipOwner;
	}
	public void setShipOwner(String shipOwner) {
		this.shipOwner = shipOwner;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public int getShipLength() {
		return shipLength;
	}
	public void setShipLength(int shipLength) {
		this.shipLength = shipLength;
	}
	public int getShipWidth() {
		return shipWidth;
	}
	public void setShipWidth(int shipWidth) {
		this.shipWidth = shipWidth;
	}
	public int getDraft() {
		return draft;
	}
	public void setDraft(int draft) {
		this.draft = draft;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public int getIMO() {
		return IMO;
	}
	public void setIMO(int iMO) {
		IMO = iMO;
	}
	
}
