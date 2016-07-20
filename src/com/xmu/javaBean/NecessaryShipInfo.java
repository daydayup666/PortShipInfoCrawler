package com.xmu.javaBean;

import java.io.Serializable;

public class NecessaryShipInfo implements Serializable {

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
	 * 提取时间
	 */
	private long extractTime;
	/**
	 * 船舶名
	 */
	private String shipName;
	/**
	 * 船舶状态
	 */
	private String shipStatus;
	/**
	 * 船舶类型
	 */
	private String shipType;
	/**
	 * 船舶经度
	 */
	private String longitude;
	/**
	 * 船舶纬度
	 */
	private String latitude;
	/**
	 * 总吨位
	 */
	private float totalTonnage;    
	/**
	 * 载重吨位
	 */
	private float loadingTonnage;  
	/**
	 * 净吨位
	 */
	private float netTonnage;
	public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public long getExtractTime() {
		return extractTime;
	}
	public void setExtractTime(long extractTime) {
		this.extractTime = extractTime;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipStatus() {
		return shipStatus;
	}
	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public float getTotalTonnage() {
		return totalTonnage;
	}
	public void setTotalTonnage(float totalTonnage) {
		this.totalTonnage = totalTonnage;
	}
	public float getLoadingTonnage() {
		return loadingTonnage;
	}
	public void setLoadingTonnage(float loadingTonnage) {
		this.loadingTonnage = loadingTonnage;
	}
	public float getNetTonnage() {
		return netTonnage;
	}
	public void setNetTonnage(float netTonnage) {
		this.netTonnage = netTonnage;
	}
      
	                
}
