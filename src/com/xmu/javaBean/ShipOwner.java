package com.xmu.javaBean;

import java.io.Serializable;

public class ShipOwner implements Serializable {

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
	 * 公司
	 */
	private String company;
	
//	private String contactPerson;
//	private int postcode;
//	private String shipNationality;
//	private int phone;
//	private int contactPhone;
//	private String fax;
//	private String 

}
