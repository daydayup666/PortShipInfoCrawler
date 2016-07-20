package com.xmu.logic;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

import com.xmu.javaBean.NecessaryShipInfo;


public class ShipInfoExtraction {
	private int count = 0;
	private int threadCount = 4;
	private ArrayList<NecessaryShipInfo> shipInfosList = new ArrayList<>();
	private ArrayList<String> linkList;
	public ShipInfoExtraction() {
		linkList = ShipDynamicInfoParser.shipInfoLinkList;
	}
	
	public void extractAllShipInfo() {
		begin();
	}
	private void begin() {
		for(int i=0;i<threadCount;i++) {
			new Thread(new Runnable() {			
				@Override
				public void run() {
					while(true) {
						String link = getUrl();
						if(link!=null) {
							extract(link);
						}else {
							break;
						}
					}				
				}
			},"线程"+i).start();
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
			//匹配MMSI
			Pattern pat = Pattern.compile("\\d{9}");
			Matcher mat = pat.matcher(url);
			while(mat.find()) {
				System.out.println(mat.group());
				shipInfo.setMMSI(Integer.valueOf(mat.group()));
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
			shipInfo.setTotalTonnage(Double.valueOf(info.get(20)));
			System.out.println("总（吨）："+info.get(20));
			shipInfo.setLoadingTonnage(Double.valueOf(info.get(21)));
			System.out.println("载重（吨）："+info.get(21));
			if(!info.get(22).equals(" "))
				shipInfo.setNetTonnage(Double.valueOf(info.get(22)));
			System.out.println("净（吨）:"+info.get(22));
			shipInfosList.add(shipInfo);
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
