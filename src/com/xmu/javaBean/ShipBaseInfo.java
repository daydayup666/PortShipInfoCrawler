package com.xmu.javaBean;

import java.io.Serializable;

public class ShipBaseInfo implements Serializable{
	/**
	 * 序列化对象版本控制
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 船名
	 */
	private String shipName;  
	/**
	 * 原船名
	 */
	private String originShipName;      
	/**
	 * 国籍
	 */
	private String nationality;          
	/**
	 * 原国籍
	 */
	private String originNationality;   
	/**
	 * 船舶中文名字
	 */
	private String shipChineseName;   
	/**
	 * 船速
	 */
	private int shipSpeed;           
	/**
	 * 水上移动通信业务标识码
	 */
	private int MMSI;                
	/**
	 * 船舶代码
	 */
	private int IMO;                  
	/**
	 * 呼号
	 */
	private String callLetter;           
	/**
	 * 造船厂
	 */
	private String createShipFactory;  
	/**
	 * 造船地点
	 */
	private String createShipPlace;   
	/**
	 * 造船时间
	 */
	private String createShipTime;     
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
	public String getCallLetter() {
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
	public void setCallLetter(String callLetter) {
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
