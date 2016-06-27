package com.xmu.javaBean;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.Date;

public class ShipBaseInfo implements Serializable{
	private String shipName;             //船名
	private String originShipName;      //原船名
	private String nationality;          //国籍
	private String originNationality;   //原国籍
	private String shipChineseName;   //船舶中文名字
	private int shipSpeed;           //船速
	private int MMSI;                //水上移动通信业务标识码
	private int IMO;                  //船舶代码
	private int callLetter;            //呼号
	private String createShipFactory;  //造船厂
	private String createShipPlace;   //造船地点
    private String createShipTime;     //造船时间
	public String getShipName() {
		return shipName;
	}
	public String getOriginShipName() {
		return originShipName;
	}
	public String getNationality() {
		return nationality;
	}
	public String getOriginNationality() {
		return originNationality;
	}
	public String getShipChineseName() {
		return shipChineseName;
	}
	public int getShipSpeed() {
		return shipSpeed;
	}
	public int getMMSI() {
		return MMSI;
	}
	public int getIMO() {
		return IMO;
	}
	public int getCallLetter() {
		return callLetter;
	}
	public String getCreateShipFactory() {
		return createShipFactory;
	}
	public String getCreateShipPlace() {
		return createShipPlace;
	}
	public String getCreateShipTime() {
		return createShipTime;
	}	
=======
public class ShipBaseInfo {

>>>>>>> f2717e7c57a717cdebb653facf3a28387775bf8c
}
