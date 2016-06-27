package com.xmu.javaBean;

import java.io.Serializable;

public class ShipTonnage implements Serializable{
	private int totalTonnage;     //总吨位
	private int loadingTonnage;   //载重吨位
	private int netTonnage;          //净吨位
	public int getTotalTonnage() {
		return totalTonnage;
	}
	public int getLoadingTonnage() {
		return loadingTonnage;
	}
	public int getNetTonnage() {
		return netTonnage;
	}
	public void setTotalTonnage(int totalTonnage) {
		this.totalTonnage = totalTonnage;
	}
	public void setLoadingTonnage(int loadingTonnage) {
		this.loadingTonnage = loadingTonnage;
	}
	public void setNetTonnage(int netTonnage) {
		this.netTonnage = netTonnage;
	}
	
}
