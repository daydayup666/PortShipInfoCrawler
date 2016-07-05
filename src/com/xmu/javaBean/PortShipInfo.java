package com.xmu.javaBean;


import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/21.
 */

public class PortShipInfo implements Serializable{
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
	 * 船舶名
	 */
	private String shipName;
    /**
     * 船舶动态信息，停靠or进入
     */
	private String dynamicInfo;
	/**
	 * 口岸名称
	 */
    private String portName;
    /**
     * 时间
     */
    private String  date;

    public PortShipInfo() {

    }
    public int getMMSI() {
		return MMSI;
	}
	public void setMMSI(int mMSI) {
		MMSI = mMSI;
	}
    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getDynamicInfo() {
        return dynamicInfo;
    }

    public void setDynamicInfo(String dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
