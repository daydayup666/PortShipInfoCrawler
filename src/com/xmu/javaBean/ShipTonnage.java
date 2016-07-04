package com.xmu.javaBean;

import java.io.Serializable;

public class ShipTonnage implements Serializable{
	/**
	 * 序列化对象版本控制
	 */
	private static final long serialVersionUID = 1L;
	private double totalTonnage;     //总吨位
	private double loadingTonnage;   //载重吨位
	private double netTonnage;          //净吨位
	
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
	
}
