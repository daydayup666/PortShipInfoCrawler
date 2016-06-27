package com.xmu.logic;

import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.xmu.javaBean.ShipBaseInfo;
import com.xmu.javaBean.ShipMainInfo;



public class ShipSpecificInfoParser {
	ArrayList<String> shipInfoLinkList;
	ArrayList<ShipMainInfo> shipMainInfosList = new ArrayList<>();
	ArrayList<ShipBaseInfo> shipBaseInfosList = new ArrayList<>();
	Parser parser;
	public void shipInfoParser() {		
		shipInfoLinkList = ShipDynamicInfoParser.shipInfoLinkList;
		System.out.println(shipInfoLinkList);
		for(String link : shipInfoLinkList) {
			try {
				System.out.println("开始提取船舶具体信息...");
				parser = new Parser(link);
				
				NodeFilter divFilter = new NodeClassFilter(Div.class);
				NodeList divNodeList = parser.extractAllNodesThatMatch(divFilter);
				//船舶主要信息
				ShipMainInfo mainInfo = new ShipMainInfo();
				//船舶基本信息
				ShipBaseInfo baseInfo = new ShipBaseInfo();
				
				for(int i=0;i<divNodeList.size();i++) {
					//具有Div标签的结点
					Div divNode = (Div)divNodeList.elementAt(i);
					//处理对class=table-div的结点
					if(divNode.getAttribute("class")!=null&&
							divNode.getAttribute("class").equals("table-div")) {
						NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);
						NodeList bulletList = divNode.getChildren().extractAllNodesThatMatch(bulletFilter, true);
						
						for(int j=0;j<bulletList.size();j++) {
							Bullet bulletTag = (Bullet)bulletList.elementAt(j);
							NodeFilter spanFilter = new TagNameFilter("span");
							NodeList spanList = bulletTag.getChildren().extractAllNodesThatMatch(spanFilter,true);
							if(spanList.size()==2) {
								if(spanList.elementAt(0).toPlainTextString().equals("船名：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("中文船名：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("呼号：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("国籍：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("船东：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("船舶类型：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("船长：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("船宽：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("吃水：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("MMSI：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("最后时间：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}else if(spanList.elementAt(0).toPlainTextString().equals("IMO：")){
									System.out.println(spanList.elementAt(1).toPlainTextString());
								}
									
								
							}
						}
						
					}
				}
			} catch (ParserException e) {
			
				e.printStackTrace();
			}
		}
		
	}
}
