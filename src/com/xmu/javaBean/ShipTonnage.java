package com.xmu.javaBean;

import java.io.Serializable;

public class ShipTonnage implements Serializable{
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
	 * 总吨位
	 */
	private double totalTonnage;    
	/**
	 * 载重吨位
	 */
	private double loadingTonnage;  
	/**
	 * 净吨位
	 */
	private double netTonnage;        
	                

	public double getTotalTonnage() {
		return totalTonnage;
	}
	public double getLoadingTonnage() {
		return loadingTonnage;
	}
	public double getNetTonnage() {
		return netTonnage;
	}
	public void setTotalTonnage(double totalTonnage) {
		this.totalTonnage = totalTonnage;
	}
	public void setLoadingTonnage(double loadingTonnage) {
		this.loadingTonnage = loadingTonnage;
	}
	public void setNetTonnage(double netTonnage) {
		this.netTonnage = netTonnage;
	}
	public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	
}
