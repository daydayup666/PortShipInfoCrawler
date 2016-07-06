package com.xmu.javaBean;

import java.io.Serializable;

public class ShipBodyInfo implements Serializable{
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
	 * 船舱数  
	 */
	private int cabinNum;   
	/**
	 * 冷藏集装箱(平方米)  
	 */
	private double containers;        
	/**
	 *  甲板数
	 */
	private int deckNum;   
	/**
	 * 翼液货舱
	 */
	private String wingTank;
	/**
	 * 总长
	 */
	private double totalLength;
	/**
	 * 液货舱
	 */
	private String cargoTank;
	/**
	 * 型深(m)
	 */
	private double molededDepth;     
	/**
	 * 中心液货舱
	 */
	private String centerCargoTank;
	/**
	 * 型宽(m)
	 */
	private double molededWidth; 
	/**
	 * 甲板液货舱
	 */
	private String deckCargoTank;
	/**
	 * 吃水米数(m)
	 */
	private double draft; 
	/**
	 * 污水舱
	 */
	private String bilgeTank;
	/**
	 * 排水量
	 */
	private double displacement;
	/**
	 * 冷藏总容量
	 */
	private double totalRefrerate;
	/**
	 * 是否配置原油洗舱
	 */
	private boolean isConfiguredWashing;  
	/**
	 * 是否装有惰性气体
	 */
	private boolean isEquippedInertGas;  
	
	public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
	public int getCabinNum() {
		return cabinNum;
	}
	public double getContainers() {
		return containers;
	}
	public int getDeckNum() {
		return deckNum;
	}
	public double getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(double totalLength) {
		this.totalLength = totalLength;
	}
	public double getMolededDepth() {
		return molededDepth;
	}
	public double getMolededWidth() {
		return molededWidth;
	}
	public double getDraft() {
		return draft;
	}
	public double getDisplacement() {
		return displacement;
	}
	public void setDisplacement(double displacement) {
		this.displacement = displacement;
	}
	public double getTotalRefrerate() {
		return totalRefrerate;
	}
	public void setTotalRefrerate(double totalRefrerate) {
		this.totalRefrerate = totalRefrerate;
	}
	public boolean isConfiguredWashing() {
		return isConfiguredWashing;
	}
	public boolean isEquippedInertGas() {
		return isEquippedInertGas;
	}
	public void setCabinNum(int cabinNum) {
		this.cabinNum = cabinNum;
	}
	public void setContainers(double containers) {
		this.containers = containers;
	}
	public void setDeckNum(int deckNum) {
		this.deckNum = deckNum;
	}
	public void setMolededDepth(double molededDepth) {
		this.molededDepth = molededDepth;
	}
	public void setMolededWidth(double molededWidth) {
		this.molededWidth = molededWidth;
	}
	public void setDraft(double draft) {
		this.draft = draft;
	}
	public String getWingTank() {
		return wingTank;
	}
	public void setWingTank(String wingTank) {
		this.wingTank = wingTank;
	}
	public String getCargoTank() {
		return cargoTank;
	}
	public void setCargoTank(String cargoTank) {
		this.cargoTank = cargoTank;
	}
	public String getCenterCargoTank() {
		return centerCargoTank;
	}
	public void setCenterCargoTank(String centerCargoTank) {
		this.centerCargoTank = centerCargoTank;
	}
	public String getDeckCargoTank() {
		return deckCargoTank;
	}
	public void setDeckCargoTank(String deckCargoTank) {
		this.deckCargoTank = deckCargoTank;
	}
	public String getBilgeTank() {
		return bilgeTank;
	}
	public void setBilgeTank(String bilgeTank) {
		this.bilgeTank = bilgeTank;
	}
	public void setConfiguredWashing(boolean isConfiguredWashing) {
		this.isConfiguredWashing = isConfiguredWashing;
	}
	public void setEquippedInertGas(boolean isEquippedInertGas) {
		this.isEquippedInertGas = isEquippedInertGas;
	}
	
}
