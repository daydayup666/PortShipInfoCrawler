package com.xmu.logic;

import java.util.ArrayList;

import com.xmu.javaBean.NecessaryShipInfo;
import com.xmu.javaBean.PortShipInfo;
import com.xmu.javaBean.ShipBaseInfo;
import com.xmu.javaBean.ShipBodyInfo;
import com.xmu.javaBean.ShipMainInfo;
import com.xmu.javaBean.ShipTonnage;

public class GetAllShipInfo {
	/**
	 * 厦门港船舶动态信息
	 */
	ArrayList<PortShipInfo> shipInfos;
	/**
	 * 船舶主要信息
	 */
	ArrayList<ShipMainInfo> mainInfos;
	/**
	 * 船舶基本信息
	 */
	ArrayList<ShipBaseInfo> baseInfos;
	/**
	 * 船体信息
	 */
	ArrayList<ShipBodyInfo> bodyInfos;
	/**
	 * 吨位
	 */
	ArrayList<ShipTonnage> tonnages;
	ArrayList<NecessaryShipInfo> necessaryShipInfos;
	int count = 0;
	
	public void getShipInfo() {
		long start = System.currentTimeMillis();
        ShipDynamicInfoParser parser = new ShipDynamicInfoParser(
                "http://www.chinaports.com/shipline/1/20/26/shipstatView");
        parser.parseUrl();
       
		ShipInfoExtraction infoExtraction = new ShipInfoExtraction();
		infoExtraction.extractAllShipInfo();
        
		necessaryShipInfos = infoExtraction.getShipInfosList();
  
        System.out.println("总共爬取网页："+necessaryShipInfos.size());
        long end = System.currentTimeMillis();
		System.out.println("爬取完成，共花费时间"+(end-start)/1000+"s\n");
	}
}
