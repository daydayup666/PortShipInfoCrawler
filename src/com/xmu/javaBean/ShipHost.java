package com.xmu.javaBean;

import java.io.Serializable;

public class ShipHost implements Serializable {
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
	 * 主机制造商
	 */
	private String hostManufacturer;
	/**
	 * 主机性能
	 */
	private String hostPerformance;
	/**
	 * 主机种类
	 */
	private String hostType;
	/**
	 * 主机燃料
	 */
	private String hostFuel;
	/**
	 * 主机型号
	 */
	private String hostVersion;
	/**
	 * 主机总功率
	 */
	private double hostTotalPower;
	/**
	 * 主机速率
	 */
	private double hostSpeed;
	
	public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public String getHostManufacturer() {
		return hostManufacturer;
	}
	public void setHostManufacturer(String hostManufacturer) {
		this.hostManufacturer = hostManufacturer;
	}
	public String getHostPerformance() {
		return hostPerformance;
	}
	public void setHostPerformance(String hostPerformance) {
		this.hostPerformance = hostPerformance;
	}
	public String getHostType() {
		return hostType;
	}
	public void setHostType(String hostType) {
		this.hostType = hostType;
	}
	public String getHostFuel() {
		return hostFuel;
	}
	public void setHostFuel(String hostFuel) {
		this.hostFuel = hostFuel;
	}
	public String getHostVersion() {
		return hostVersion;
	}
	public void setHostVersion(String hostVersion) {
		this.hostVersion = hostVersion;
	}
	public double getHostTotalPower() {
		return hostTotalPower;
	}
	public void setHostTotalPower(double hostTotalPower) {
		this.hostTotalPower = hostTotalPower;
	}
	public double getHostSpeed() {
		return hostSpeed;
	}
	public void setHostSpeed(double hostSpeed) {
		this.hostSpeed = hostSpeed;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
