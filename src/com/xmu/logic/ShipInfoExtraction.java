package com.xmu.logic;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

import com.xmu.javaBean.NecessaryShipInfo;


public class ShipInfoExtraction {
	NecessaryShipInfo shipInfo = new NecessaryShipInfo();
	
	public void extract(String url) {
		try {
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
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
