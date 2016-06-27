package com.xmu.javaBean;

import java.io.Serializable;

public class ShipBaseInfo implements Serializable{
	private String shipName;             //船名
	private String originShipName;      //原船名
	private String nationality;          //国籍
	private String originNationality;   //原国籍
	private String shipChineseName;   //船舶中文名字
	private int shipSpeed;           //船速
	private int MMSI;                //水上移动通信业务标识码
	private int IMO;                  //船舶代码
	private int callLetter;            //呼号
	private String createShipFactory;  //造船厂
	private String createShipPlace;   //造船地点
    private String createShipTime;     //造船时间
	public String getShipName() {
		return shipName;
	}
	public String getOriginShipName() {
		return originShipName;
	}
	public String getNationality() {
		return nationality;
	}
	public String getOriginNationality() {
		return originNationality;
	}
	public String getShipChineseName() {
		return shipChineseName;
	}
	public int getShipSpeed() {
		return shipSpeed;
	}
	public int getMMSI() {
		return MMSI;
	}
	public int getIMO() {
		return IMO;
	}
	public int getCallLetter() {
		return callLetter;
	}
	public String getCreateShipFactory() {
		return createShipFactory;
	}
	public String getCreateShipPlace() {
		return createShipPlace;
	}
	public String getCreateShipTime() {
		return createShipTime;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public void setOriginShipName(String originShipName) {
		this.originShipName = originShipName;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public void setOriginNationality(String originNationality) {
		this.originNationality = originNationality;
	}
	public void setShipChineseName(String shipChineseName) {
		this.shipChineseName = shipChineseName;
	}
	public void setShipSpeed(int shipSpeed) {
		this.shipSpeed = shipSpeed;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public void setIMO(int iMO) {
		IMO = iMO;
	}
	public void setCallLetter(int callLetter) {
		this.callLetter = callLetter;
	}
	public void setCreateShipFactory(String createShipFactory) {
		this.createShipFactory = createShipFactory;
	}
	public void setCreateShipPlace(String createShipPlace) {
		this.createShipPlace = createShipPlace;
	}
	public void setCreateShipTime(String createShipTime) {
		this.createShipTime = createShipTime;
	}
	
}
