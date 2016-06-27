package com.xmu.javaBean;

import java.io.Serializable;

public class ShipBodyInfo implements Serializable{
	private int cabinNum;         //船舱数  
	private int containers;       //冷藏集装箱
	private int deckNum;          //甲板数
	private int molededDepth;     //型深
	private int molededWidth;     //型宽
	private int draft;            //吃水米数
	
	private boolean isConfiguredWashing;  //是否配置原油洗舱
	private boolean isEquippedInertGas;    //是否装有惰性气体
	public int getCabinNum() {
		return cabinNum;
	}
	public int getContainers() {
		return containers;
	}
	public int getDeckNum() {
		return deckNum;
	}
	public int getMolededDepth() {
		return molededDepth;
	}
	public int getMolededWidth() {
		return molededWidth;
	}
	public int getDraft() {
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
	public void setContainers(int containers) {
		this.containers = containers;
	}
	public void setDeckNum(int deckNum) {
		this.deckNum = deckNum;
	}
	public void setMolededDepth(int molededDepth) {
		this.molededDepth = molededDepth;
	}
	public void setMolededWidth(int molededWidth) {
		this.molededWidth = molededWidth;
	}
	public void setDraft(int draft) {
		this.draft = draft;
	}
	public void setConfiguredWashing(boolean isConfiguredWashing) {
		this.isConfiguredWashing = isConfiguredWashing;
	}
	public void setEquippedInertGas(boolean isEquippedInertGas) {
		this.isEquippedInertGas = isEquippedInertGas;
	}
	
}
