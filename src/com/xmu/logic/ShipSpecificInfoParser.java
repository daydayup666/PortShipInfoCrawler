package com.xmu.logic;

import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
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
				
				for(int i=0;i<divNodeList.size();i++) {
					Div divNode = (Div)divNodeList.elementAt(i);
					if(divNode.getAttribute("class")!=null&&
							divNode.getAttribute("class").equals("table-div")) {
						NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);
						NodeList bulletList = divNode.getChildren().extractAllNodesThatMatch(bulletFilter, true);
						for(int j=0;j<bulletList.size();j++) {
							Bullet bulletTag = (Bullet)bulletList.elementAt(j);
							NodeFilter spanFilter = new TagNameFilter("span");
							NodeList spanList = bulletTag.getChildren().extractAllNodesThatMatch(spanFilter,true);
							for(int k=0;k<spanList.size();k++) {
								System.out.println(spanList.elementAt(k).toHtml());
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
