package com.xmu.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xmu.javaBean.PortShipInfo;

import org.htmlparser.*;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ShipDynamicInfoParser {
	private String sourceUrl;
	// private ArrayList<String> urlList = new ArrayList<>();
	/**
	 * 使用HashSet避免重复URL
	 */
	private  ArrayList<String> shipInfoLinkList = new ArrayList<>();
	

	private HashMap<Integer,PortShipInfo> portShipInfoMap = new HashMap<>();

	public ShipDynamicInfoParser() {
		
	}
	public ShipDynamicInfoParser(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	//返回船舶的动态信息，key:MMSI value:港口船舶动态信息
	public HashMap<Integer,PortShipInfo> getPortShipInfoMap() {
		System.out.println("portShipInfoMap.size()="+portShipInfoMap.size());
		return portShipInfoMap;
	}
	public ArrayList<String> getShipInfoLinkList() {
		return shipInfoLinkList;
	}
	public void parseUrl() {
		// urlList.add(sourceUrl);
		try {
					
			System.out.println("爬取开始...");
			
			Parser parser = new Parser(sourceUrl);
			NodeFilter nodeFilter = new NodeClassFilter(Div.class);			
			NodeList nodeList = parser.extractAllNodesThatMatch(nodeFilter);

			for (int i = 0; i < nodeList.size(); i++) {
				
				Div node = (Div) nodeList.elementAt(i);

				if (node.getAttribute("class") != null) {
					if (node.getAttribute("class").equals("link_f")) {

						NodeFilter childNodeFilter = new TagNameFilter("tr");
						//获得表格的行结点
						NodeList childNodeList = node.getChildren().extractAllNodesThatMatch(childNodeFilter, true);
						//匹配9位数字的MMSI
						Pattern pattern = Pattern.compile("\\d{8,}");
						
						for (int j = 0; j < childNodeList.size(); j++) {
							if (childNodeList.elementAt(j) instanceof TableRow) {
								//行结点
								TableRow rowNode = (TableRow) childNodeList.elementAt(j);
								//保存船舶动态信息
								PortShipInfo info = new PortShipInfo();
								//column表示列
								int column = 1;
								//船舶信息是否更新
								boolean update = false;
								//列过滤器
								NodeFilter filter = new TagNameFilter("td");
								NodeList list = rowNode.getChildren().extractAllNodesThatMatch(filter, true);
								for (int k = 0; k < list.size(); k++) {
									update = true;
									// System.out.println(list.elementAt(k).toHtml());
									// if (rowNode.getChildren().elementAt(k)
									// instanceof TableColumn) {
									//表格列结点
									TableColumn columnNode = (TableColumn) list.elementAt(k);

									if (column == 1) {
										LinkTag link = (LinkTag) columnNode
												.getChild(0);
										Matcher matcher = pattern.matcher(link
												.getLink());
										int MMSI = 0;
										while (matcher.find()) {
											MMSI = Integer.valueOf(matcher
													.group());
											info.setMMSI(MMSI);

											String dataUrl = "http://www.chinaports.com/shiptracker/shipinit.do?"
													+ "method=shipInfo&userid="
													+ MMSI
													+ "&num="
													+ new Date().getTime();
											shipInfoLinkList.add(dataUrl);
											System.out.println(dataUrl);
										}

										info.setShipName(columnNode
												.toPlainTextString());
										System.out.println(columnNode.toPlainTextString());
									}

									if (column == 2) {
										// System.out.println(columnNode.toHtml());
										info.setDynamicInfo(columnNode.toPlainTextString());
									}

									if (column == 3) {
										// System.out.println(columnNode.toHtml());
										info.setPortName(columnNode.toPlainTextString());
									}

									if (column == 4) {
										// System.out.println(columnNode.toHtml());
										info.setDate(columnNode.toPlainTextString());
									}
									column++;
									// }
								}

								if (update)
									portShipInfoMap.put(info.getMMSI(),info);
							}
						}
					}

					if (node.getAttribute("class").equals("page")) {
						NodeFilter childNodeFilter = new NodeClassFilter(LinkTag.class);
						NodeList childNodeList = node.getChildren().extractAllNodesThatMatch(childNodeFilter, true);
						// System.out.print(childNodeList.toHtml());
						for (int j = 0; j < childNodeList.size(); j++) {
							LinkTag linkTag = (LinkTag) childNodeList.elementAt(j);
							if (linkTag.toPlainTextString().equals("下一页")) {
								sourceUrl = linkTag.getLink();
								parseUrl();
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
