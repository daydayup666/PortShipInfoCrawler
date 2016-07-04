package com.xmu.logic;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		for (String link : shipInfoLinkList) {
			try {
				System.out.println("开始提取船舶具体信息...");
				parser = new Parser(link);

				NodeFilter divFilter = new NodeClassFilter(Div.class);
				NodeList divNodeList = parser.extractAllNodesThatMatch(divFilter);
				// 船舶主要信息
				ShipMainInfo mainInfo = new ShipMainInfo();
				// 船舶基本信息
				ShipBaseInfo baseInfo = new ShipBaseInfo();
			    
				for (int i = 0; i < divNodeList.size(); i++) {
					// 具有Div标签的结点
					Div divNode = (Div) divNodeList.elementAt(i);
					// 处理对class=table-div的结点
					if (divNode.getAttribute("class") != null && divNode.getAttribute("class").equals("table-div")) {
						NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);
						NodeList bulletList = divNode.getChildren().extractAllNodesThatMatch(bulletFilter, true);
						// 匹配船名
						Pattern pattern = Pattern.compile("[a-zA-Z]+[\\w\\s]*[\\w]$");

						for (int j = 0; j < bulletList.size(); j++) {
							Bullet bulletTag = (Bullet) bulletList.elementAt(j);
							NodeFilter spanFilter = new TagNameFilter("span");
							NodeList spanList = bulletTag.getChildren().extractAllNodesThatMatch(spanFilter, true);
							//提取船舶主要信息和基本信息保存到ShipMainInfo和ShipBaseInfo对象中
							if (spanList.size() == 2) {
								if (spanList.elementAt(0).toPlainTextString().equals("船名：")) {
									Matcher matcher = pattern.matcher(spanList.elementAt(1).toPlainTextString());
									while (matcher.find()) {
										mainInfo.setShipName(matcher.group());
										baseInfo.setShipName(matcher.group());
										System.out.println("船名：" + matcher.group());
									}
									// System.out.println(spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("中文船名：")) {
									mainInfo.setShipChineseName(spanList.elementAt(1).toPlainTextString());
									baseInfo.setShipChineseName(spanList.elementAt(1).toPlainTextString());
									System.out.println("中文船名：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("呼号：")) {
									mainInfo.setCallLetter(spanList.elementAt(1).toPlainTextString());
									baseInfo.setCallLetter(spanList.elementAt(1).toPlainTextString());
									System.out.println("呼号：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("国籍：")) {
									mainInfo.setNationality(spanList.elementAt(1).toPlainTextString());
									System.out.println("国籍：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("船东：")) {
									mainInfo.setShipOwner(spanList.elementAt(1).toPlainTextString());
									System.out.println("船东：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("船舶类型：")) {
									mainInfo.setShipType(spanList.elementAt(1).toPlainTextString());
									System.out.println("船舶类型：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("船长：")) {
									double length = Double.valueOf(spanList.elementAt(1).toPlainTextString());
									mainInfo.setShipLength(length);
									System.out.println("船长：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("船宽：")) {
									double width = Double.valueOf(spanList.elementAt(1).toPlainTextString());
									mainInfo.setShipWidth(width);
									System.out.println("船宽：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("吃水：")) {
									Pattern patt = Pattern.compile("\\d+\\.?\\d*");
									Matcher mat = patt.matcher(spanList.elementAt(1).toPlainTextString());
									while(mat.find()) {
										double draft = Double.valueOf(mat.group());
										mainInfo.setDraft(draft);
										System.out.println("吃水：" + mat.group());
									}		
								} else if (spanList.elementAt(0).toPlainTextString().equals("MMSI：")) {
									mainInfo.setMMSI(Integer.valueOf(spanList.elementAt(1).toPlainTextString()));
									baseInfo.setMMSI(Integer.valueOf(spanList.elementAt(1).toPlainTextString()));
									System.out.println("MMSI：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("最后时间：")) {
									mainInfo.setLastTime(spanList.elementAt(1).toPlainTextString());
									System.out.println("最后时间：" + spanList.elementAt(1).toPlainTextString());
								} else if (spanList.elementAt(0).toPlainTextString().equals("IMO：")) {
									mainInfo.setIMO(Integer.valueOf(spanList.elementAt(1).toPlainTextString()));
									baseInfo.setIMO(Integer.valueOf(spanList.elementAt(1).toPlainTextString()));
									System.out.println("IMO:" + spanList.elementAt(1).toPlainTextString());
								}else if (spanList.elementAt(0).toPlainTextString().equals("原船名：")) {
									baseInfo.setOriginShipName(spanList.elementAt(1).toPlainTextString());
									System.out.println("原船名:" + spanList.elementAt(1).toPlainTextString());
								}else if (spanList.elementAt(0).toPlainTextString().equals("原国籍：")) {
									baseInfo.setOriginNationality(spanList.elementAt(1).toPlainTextString());
									System.out.println("原国籍:" + spanList.elementAt(1).toPlainTextString());
								}else if (spanList.elementAt(0).toPlainTextString().equals("船速：")) {
									Pattern patt = Pattern.compile("\\d+\\.?\\d*");
									Matcher mat = patt.matcher(spanList.elementAt(1).toPlainTextString());
									while(mat.find()){
										baseInfo.setOriginNationality(mat.group());
										System.out.println("船速:" + mat.group());
									}
								}else if (spanList.elementAt(0).toPlainTextString().equals("造船厂：")) {
									baseInfo.setCreateShipFactory(spanList.elementAt(1).toPlainTextString());
									System.out.println("造船厂:" + spanList.elementAt(1).toPlainTextString());
								}else if (spanList.elementAt(0).toPlainTextString().equals("造船地点：")) {
									baseInfo.setCreateShipPlace(spanList.elementAt(1).toPlainTextString());
									System.out.println("造船地点:" + spanList.elementAt(1).toPlainTextString());
								}else if (spanList.elementAt(0).toPlainTextString().equals("造船时间：")) {
									baseInfo.setCreateShipTime(spanList.elementAt(1).toPlainTextString());
									System.out.println("造船时间:" + spanList.elementAt(1).toPlainTextString());
								}
							}
						}
					}
				
					
					if(divNode.getAttribute("class")!=null&&divNode.getAttribute("class").equals("con-nav")) {	
						NodeFilter iFrameFilter = new TagNameFilter("iframe");
						NodeList iFrameList = divNode.getChildren().extractAllNodesThatMatch(iFrameFilter,true);
						//System.out.println("frameList.size()="+frameList.size());
						TagNode iFrameNode = (TagNode) iFrameList.elementAt(0);
						
						System.out.println(iFrameNode.getAttribute("src"));
						
						ExtractIframeInfo iFrameInfo = new ExtractIframeInfo();
						iFrameInfo.extractIframeInfo("http://www.chinaports.com/"+iFrameNode.getAttribute("src"));
					}
				}
							
				System.out.println("\n");			
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}

	}
}
