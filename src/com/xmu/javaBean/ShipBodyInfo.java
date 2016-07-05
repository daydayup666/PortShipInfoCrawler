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
	 * 总长
	 */
	private double totalLength;

	/**
	 * 型深(m)
	 */
	private double molededDepth;     
	/**
	 * 型宽(m)
	 */
	private double molededWidth;     
	/**
	 * 吃水米数(m)
	 */
	private double draft;            
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
	public void setConfiguredWashing(boolean isConfiguredWashing) {
		this.isConfiguredWashing = isConfiguredWashing;
	}
	public void setEquippedInertGas(boolean isEquippedInertGas) {
		this.isEquippedInertGas = isEquippedInertGas;
	}
	
}
