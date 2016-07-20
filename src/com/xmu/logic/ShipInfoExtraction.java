package com.xmu.logic;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

import com.xmu.javaBean.NecessaryShipInfo;
import com.xmu.javaBean.PortShipInfo;


public class ShipInfoExtraction {
	private int count = 0;
	private HashMap<Integer, PortShipInfo> shipInfoMap;
	private ArrayList<NecessaryShipInfo> shipInfosList = new ArrayList<>();
	private ArrayList<String> linkList;
	public ShipInfoExtraction() {
		shipInfoMap = ShipDynamicInfoParser.portShipInfoMap;
		linkList = ShipDynamicInfoParser.shipInfoLinkList;
	}
	
	public void extractAllShipInfo() {
		begin();
	}
	private void begin() {
		
			Thread t1 = new Thread(new Runnable() {			
				@Override
				public void run() {
					loop();	
				}
			},"线程1");
			Thread t2 = new Thread(new Runnable() {			
				@Override
				public void run() {
					loop();	
				}
			},"线程2");
			Thread t3 = new Thread(new Runnable() {			
				@Override
				public void run() {
					loop();	
				}
			},"线程3");
			Thread t4 = new Thread(new Runnable() {			
				@Override
				public void run() {
					loop();	
				}
			},"线程4");
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			try {
				t1.join();
				t2.join();
				t3.join();
				t4.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	//线程执行的方法体
	private void loop() {
		while(true) {
			String link = getUrl();
			if(link!=null) {
				extract(link);
			}else {
				break;
			}
		}
	}
	private synchronized String getUrl() {
		if(count>=linkList.size()) 
			return null;
		return linkList.get(count++);
	}
	public void extract(String url) {
		NecessaryShipInfo shipInfo = new NecessaryShipInfo();
		try {
			System.out.println("开始由"+Thread.currentThread().getName()+"爬取船舶必要信息...");
			shipInfo.setExtractTime(new Date().getTime());
			//匹配MMSI
			Pattern pat = Pattern.compile("\\d{8,}");
			Matcher mat = pat.matcher(url);
			while(mat.find()) {
				//System.out.println(mat.group());
				int MMSI =Integer.valueOf(mat.group());
				shipInfo.setMMSI(MMSI);
				shipInfo.setShipStatus(shipInfoMap.get(MMSI).getDynamicInfo());
				break;
			}
			Parser parser = new Parser(url);
			TextExtractingVisitor visitor = new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			String textInPage = visitor.getExtractedText();
			
			Pattern pattern = Pattern.compile("\".*?\"");
			Matcher matcher = pattern.matcher(textInPage);
			ArrayList<String> info = new ArrayList<>();
			while(matcher.find()) {
				info.add(matcher.group().replace("\"", ""));
			}
			//String[] info = textInPage.split(", ");
			shipInfo.setShipName(info.get(0));
			System.out.println("船名："+info.get(0));
			shipInfo.setShipType(info.get(8));
			System.out.println("船舶类型："+info.get(8));
			shipInfo.setLongitude(info.get(6));
			System.out.println("经度:"+info.get(6));
			shipInfo.setLatitude(info.get(4));
			System.out.println("纬度:"+info.get(4));
			if(!info.get(20).equals(" "))
				shipInfo.setTotalTonnage(Float.valueOf(info.get(20)));
			System.out.println("总（吨）："+info.get(20));
			if(!info.get(21).equals(" "))
				shipInfo.setLoadingTonnage(Float.valueOf(info.get(21)));
			System.out.println("载重（吨）："+info.get(21));
			if(!info.get(22).equals(" "))
				shipInfo.setNetTonnage(Float.valueOf(info.get(22)));
			System.out.println("净（吨）:"+info.get(22));
			shipInfosList.add(shipInfo);
			System.out.println();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}

	public ArrayList<NecessaryShipInfo> getShipInfosList() {
		return shipInfosList;
	}
}
