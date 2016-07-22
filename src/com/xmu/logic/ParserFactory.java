package com.xmu.logic;

import java.util.ArrayList;
import java.util.HashMap;

import com.xmu.javaBean.NecessaryShipInfo;
import com.xmu.javaBean.PortShipInfo;
import com.xmu.jdbc.SaveToMySql;

public class ParserFactory {
	private String sourceUrl;
	private HashMap<Integer, PortShipInfo> portShipInfoMap;
	private ArrayList<NecessaryShipInfo> necessaryShipInfosList; 
	public ParserFactory(String url) {
		this.sourceUrl = url;
	}
	public void start() {
		long start = System.currentTimeMillis();
		//抓取sourceUrl网址具体船舶的url
		ShipDynamicInfoParser parser = new ShipDynamicInfoParser(sourceUrl);
		parser.parseUrl();
		ArrayList<String> linkList = parser.getShipInfoLinkList();
		portShipInfoMap = parser.getPortShipInfoMap();
		
		ShipInfoExtraction extraction = new ShipInfoExtraction(linkList, portShipInfoMap);
		extraction.extractAllShipInfo();
		necessaryShipInfosList = extraction.getShipInfosList();
		
		SaveToMySql save = new SaveToMySql(necessaryShipInfosList);
		save.batchInsert();
		
		System.out.println("总共爬取网页："+necessaryShipInfosList.size());
	    long end = System.currentTimeMillis();
	    System.out.println("爬取完成，共花费时间"+(end-start)/1000+"s\n");
	}
	//得到港口中船舶的动态信息
	public HashMap<Integer, PortShipInfo> getPortShipInfoMap() {
		return portShipInfoMap;
	}
	//得到船舶必要信息
	public ArrayList<NecessaryShipInfo> getNecessaryShipInfosList() {
		return necessaryShipInfosList;
	}
}
