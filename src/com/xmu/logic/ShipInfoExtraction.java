package com.xmu.logic;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;

import com.xmu.javaBean.NecessaryShipInfo;


public class ShipInfoExtraction {
	NecessaryShipInfo shipInfo;
	
	public void extract(String url) {
		try {
			Parser parser = new Parser(url);
			TextExtractingVisitor visitor = new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			String textInPage = visitor.getExtractedText();
			
			Pattern pattern = Pattern.compile("\".*?\"");
			Matcher matcher = pattern.matcher(textInPage);
			ArrayList<String> info = new ArrayList<>();
			while(matcher.find()) {
				info.add(matcher.group());
			}
			//String[] info = textInPage.split(", ");
			
			System.out.println("船名："+info.get(0));
			System.out.println("船舶类型："+info.get(8));
			System.out.println("经度:"+info.get(6));
			System.out.println("纬度:"+info.get(4));
			System.out.println("总（吨）："+info.get(20));
			System.out.println("载重（吨）："+info.get(21));
			System.out.println("净（吨）:"+info.get(22));
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
