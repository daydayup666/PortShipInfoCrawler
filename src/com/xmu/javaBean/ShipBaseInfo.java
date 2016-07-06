package com.xmu.javaBean;

import java.io.Serializable;

public class ShipBaseInfo implements Serializable{
	/**
	 * 序列化对象版本控制
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 水上移动通信业务标识码：
	 * 是船舶无线电通信系统在其无线电信道上发送的，
	 * 能独特识别各类台站和成组呼叫台站的一列九位数字码
	 */
	private int MMSI;
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
	private double shipSpeed;           
                
	/**
	 * 船舶代码
	 */
	private int IMO;                  
	/**
	 * 呼号
	 */
	private String callSign;           
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
	/**
	 * 船级社
	 */
	private String shipClassificationSociety;
	/**
	 * 船籍港
	 */
	private String shipHomePort;
	/**
	 * 安放龙骨日期
	 */
	private String layKeelTime;
	/**
	 * 下水时间
	 */
	private String launchingTime;
	
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
	public double getShipSpeed() {
		return shipSpeed;
	}
	public int getMMSI() {
		return MMSI;
	}
	public int getIMO() {
		return IMO;
	}
	public String getCallSign() {
		return callSign;
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
	public void setShipSpeed(double shipSpeed) {
		this.shipSpeed = shipSpeed;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public void setIMO(int iMO) {
		IMO = iMO;
	}
	public void setCallSign(String callLetter) {
		this.callSign = callLetter;
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
	public String getShipClassificationSociety() {
		return shipClassificationSociety;
	}
	public void setShipClassificationSociety(String shipClassificationSociety) {
		this.shipClassificationSociety = shipClassificationSociety;
	}
	public String getShipHomePort() {
		return shipHomePort;
	}
	public void setShipHomePort(String shipHomePort) {
		this.shipHomePort = shipHomePort;
	}
	public String getLayKeelTime() {
		return layKeelTime;
	}
	public void setLayKeelTime(String layKeelTime) {
		this.layKeelTime = layKeelTime;
	}
	public String getLaunchingTime() {
		return launchingTime;
	}
	public void setLaunchingTime(String launchingTime) {
		this.launchingTime = launchingTime;
	}
	
}
