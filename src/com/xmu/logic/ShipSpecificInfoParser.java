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
import com.xmu.javaBean.ShipBodyInfo;
import com.xmu.javaBean.ShipMainInfo;
import com.xmu.javaBean.ShipTonnage;

public class ShipSpecificInfoParser {
	int count = 0;
	final int threadCount = 6;
	ArrayList<String> shipInfoLinkList;
	/**
	 * 保存所有船舶主要资料
	 */
	private ArrayList<ShipMainInfo> shipMainInfosList = new ArrayList<>();
	/**
	 * 保存所有船舶基本资料
	 */
	private ArrayList<ShipBaseInfo> shipBaseInfosList = new ArrayList<>();
	/**
	 * 保存所有船舶船体信息
	 */
	private ArrayList<ShipBodyInfo> shipBodyInfosList = new ArrayList<>();
	/**
	 * 保存吨位信息
	 */
	private ArrayList<ShipTonnage> shipTonnagesList = new ArrayList<>();
	Parser parser;
	/**
	 * 船舶信息解析
	 */
	public void extractAllShipInfo() {
		shipInfoLinkList = ShipDynamicInfoParser.shipInfoLinkList;
//		//System.out.println(shipInfoLinkList);
//		for(String url:shipInfoLinkList) {		
//			shipInfoParser(url);
//		}
		begin();
	}
	private void begin() {
		Thread t1 =new Thread(new Runnable() {				
				@Override
				public void run() {
					while(true) {
						String link = getUrl();
						if(link!=null) {
							shipInfoParser(link);
						}else {	
							break;
						}
					}
					
				}
			},"线程1");
		
		Thread t2 =new Thread(new Runnable() {				
			@Override
			public void run() {
				while(true) {
					String link = getUrl();
					if(link!=null) {
						shipInfoParser(link);
					}else {
						break;
					}
				}
				
			}
		},"线程2");
		Thread t3 =new Thread(new Runnable() {				
			@Override
			public void run() {
				while(true) {
					String link = getUrl();
					if(link!=null) {
						shipInfoParser(link);
					}else {					
						break;
					}
				}
				
			}
		},"线程3");
		Thread t4 =new Thread(new Runnable() {				
			@Override
			public void run() {
				while(true) {
					String link = getUrl();
					if(link!=null) {
						shipInfoParser(link);
					}else {	
						break;
					}
				}
				
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
	public synchronized String getUrl() {
		if(count>=shipInfoLinkList.size())
			return null;
		String url;
		url = shipInfoLinkList.get(count++);
		
		return url;
	}
	public void shipInfoParser(String link) {	
		try {
			System.out.println("开始由"+Thread.currentThread().getName()+"爬取"+link+"的船舶具体信息...");
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
				if (divNode.getAttribute("class") != null
						&& divNode.getAttribute("class").equals("con-nav")) {
					NodeFilter bulletFilter = new NodeClassFilter(Bullet.class);
					NodeList bulletList = divNode.getChildren()
							.extractAllNodesThatMatch(bulletFilter, true);
					// 匹配船名
					Pattern pattern = Pattern
							.compile("[a-zA-Z]+[\\w\\s]*[\\w]$");

					for (int j = 0; j < bulletList.size(); j++) {
						Bullet bulletTag = (Bullet) bulletList.elementAt(j);
						NodeFilter spanFilter = new TagNameFilter("span");
						NodeList spanList = bulletTag.getChildren()
								.extractAllNodesThatMatch(spanFilter, true);
						// 提取船舶主要信息和基本信息保存到ShipMainInfo和ShipBaseInfo对象中
						if (spanList.size() == 2) {
							if (spanList.elementAt(0).toPlainTextString()
									.equals("船名：")) {
								Matcher matcher = pattern.matcher(spanList
										.elementAt(1).toPlainTextString());
								while (matcher.find()) {
									mainInfo.setShipName(matcher.group());
									baseInfo.setShipName(matcher.group());
			//						System.out.println("船名：" + matcher.group());
								}
								// System.out.println(spanList.elementAt(1).toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("中文船名：")) {
								mainInfo.setShipChineseName(spanList.elementAt(
										1).toPlainTextString());
								baseInfo.setShipChineseName(spanList.elementAt(
										1).toPlainTextString());
//								System.out.println("中文船名："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("呼号：")) {
								mainInfo.setCallSign(spanList.elementAt(1)
										.toPlainTextString());
								baseInfo.setCallSign(spanList.elementAt(1)
										.toPlainTextString());
//								System.out.println("呼号："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("国籍：")) {
								mainInfo.setNationality(spanList.elementAt(1)
										.toPlainTextString());
//								System.out.println("国籍："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船东：")) {
								mainInfo.setShipOwner(spanList.elementAt(1)
										.toPlainTextString());
//								System.out.println("船东："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船舶类型：")) {
								mainInfo.setShipType(spanList.elementAt(1)
										.toPlainTextString());
//								System.out.println("船舶类型："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船长：")) {
								double length = Double.valueOf(spanList
										.elementAt(1).toPlainTextString());
								mainInfo.setShipLength(length);
//								System.out.println("船长："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船宽：")) {
								double width = Double.valueOf(spanList
										.elementAt(1).toPlainTextString());
								mainInfo.setShipWidth(width);
//								System.out.print("船宽："
//										+ spanList.elementAt(1)
//												.toPlainTextString()+"\t\t");
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("吃水：")) {
								Pattern patt = Pattern.compile("\\d+\\.?\\d*");
								Matcher mat = patt.matcher(spanList
										.elementAt(1).toPlainTextString());
								while (mat.find()) {
									double draft = Double.valueOf(mat.group());
									mainInfo.setDraft(draft);
//									System.out.print("吃水：" + mat.group()+"\t");
								}
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("MMSI：")) {
								mainInfo.setMMSI(Integer.valueOf(spanList
										.elementAt(1).toPlainTextString()));
								baseInfo.setMMSI(Integer.valueOf(spanList
										.elementAt(1).toPlainTextString()));
//								System.out.println("MMSI："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("最后时间：")) {
								mainInfo.setLastTime(spanList.elementAt(1)
										.toPlainTextString());
//								System.out.println("最后时间："
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("IMO：")) {
								mainInfo.setIMO(Integer.valueOf(spanList
										.elementAt(1).toPlainTextString()));
								baseInfo.setIMO(Integer.valueOf(spanList
										.elementAt(1).toPlainTextString()));
//								System.out.println("IMO:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("原船名：")) {
								baseInfo.setOriginShipName(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("原船名:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("原船籍：")) {
								baseInfo.setOriginNationality(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("原船籍籍:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船速：")) {
								Pattern patt = Pattern.compile("\\d+\\.?\\d*");
								Matcher mat = patt.matcher(spanList
										.elementAt(1).toPlainTextString());
								while (mat.find()) {
									baseInfo.setShipSpeed(Double.valueOf(mat.group()));
								//	System.out.println("船速:" + mat.group());
								}
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("造船厂：")) {
								baseInfo.setCreateShipFactory(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("造船厂:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("造船地点：")) {
								baseInfo.setCreateShipPlace(spanList.elementAt(
										1).toPlainTextString());
//								System.out.println("造船地点:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("造船日期：")) {
								baseInfo.setCreateShipTime(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("造船日期:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船级社：")) {
								baseInfo.setShipClassificationSociety(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("船级社:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("船籍港：")) {
								baseInfo.setShipHomePort(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("船籍港:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							} else if (spanList.elementAt(0)
									.toPlainTextString().equals("安放龙骨日期：")) {
								baseInfo.setLayKeelTime(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("安放龙骨日期:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							}else if (spanList.elementAt(0)
									.toPlainTextString().equals("下水日期：")) {
								baseInfo.setLaunchingTime(spanList
										.elementAt(1).toPlainTextString());
//								System.out.println("下水日期:"
//										+ spanList.elementAt(1)
//												.toPlainTextString());
							}
						}
					}
					shipMainInfosList.add(mainInfo);
					shipBaseInfosList.add(baseInfo);
					
					NodeFilter iFrameFilter = new TagNameFilter("iframe");
					NodeList iFrameList = divNode.getChildren()
							.extractAllNodesThatMatch(iFrameFilter, true);
					// System.out.println("frameList.size()="+frameList.size());
					TagNode iFrameNode = (TagNode) iFrameList.elementAt(0);

					//System.out.println(iFrameNode.getAttribute("src"));

					ExtractIframeInfo iFrameInfo = new ExtractIframeInfo();
					iFrameInfo.extractIframeInfo("http://www.chinaports.com/"
							+ iFrameNode.getAttribute("src"));
					shipBodyInfosList.add(iFrameInfo.getBodyInfo());
					//System.out.println("MMSI="+iFrameInfo.getBodyInfo().getMMSI());
					shipTonnagesList.add(iFrameInfo.getTonnage());
				}
			
//				System.out.println("船舶主要资料"+mainInfo.getMMSI()+"\n"+
//						"船名:"+mainInfo.getShipName()+"\n"
//						+"中文船名："+mainInfo.getShipChineseName()+"\n"
//						+"呼号："+mainInfo.getCallSign()+"\n"
//						+"国籍："+mainInfo.getNationality()+"\n"
//						+"船东："+mainInfo.getShipOwner()+"\n"+
//						"船舶类型："+mainInfo.getShipType()+"\n"+
//						"船长："+mainInfo.getShipLength()+"\n"+
//						"船宽："+mainInfo.getShipWidth()+"\t\t"+"最后时间"+mainInfo.getLastTime()+"\n"+
//						"吃水："+mainInfo.getDraft()+"\t\t"+"IMO:"+mainInfo.getIMO()+"\n"+
//						"MMSI："+mainInfo.getMMSI());
//				System.out.println("船舶基本资料"+baseInfo.getMMSI()+"\n"+
//						"船名:"+baseInfo.getShipName()+"\t\t"+"国籍："+baseInfo.getNationality()+"\n"+
//						"原船名："+baseInfo.getOriginShipName()+"\t\t"+"原船籍："+baseInfo.getOriginNationality()+"\n"+
//						"中文船名："+baseInfo.getShipChineseName()+"\t\t"+"船速："+baseInfo.getShipSpeed()+"\n"+
//						"MMSI："+baseInfo.getMMSI()+"\t\t"+"造船厂："+baseInfo.getCreateShipFactory()+"\n"+
//						"IMO："+baseInfo.getIMO()+"\t\t"+"造船地点："+baseInfo.getCreateShipPlace()+"\n"+
//						"呼号："+baseInfo.getCallSign()+"\t\t"+"造船日期："+baseInfo.getCreateShipTime()+"\n"+
//						"船级社："+baseInfo.getShipChineseName()+"\t\t"+"安放龙骨时间："+baseInfo.getLayKeelTime()+"\n"+
//						"船籍港："+baseInfo.getShipHomePort()+"\t\t"+"下水时间："+baseInfo.getLaunchingTime()+"\n");
			}
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ShipMainInfo> getShipMainInfosList() {
		return shipMainInfosList;
	}
	public ArrayList<ShipBaseInfo> getShipBaseInfosList() {
		return shipBaseInfosList;
	}
	public ArrayList<ShipBodyInfo> getShipBodyInfosList() {
		return shipBodyInfosList;
	}
	public ArrayList<ShipTonnage> getShipTonnagesList() {
		return shipTonnagesList;
	}
}
