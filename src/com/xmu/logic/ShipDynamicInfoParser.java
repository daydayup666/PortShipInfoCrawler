package com.xmu.logic;

import java.util.ArrayList;
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
	public static ArrayList<String> shipInfoLinkSet = new ArrayList<>();
	private ArrayList<PortShipInfo> portShipInfoList = new ArrayList<>();

	public ShipDynamicInfoParser() {
		
	}
	public ShipDynamicInfoParser(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public ArrayList<PortShipInfo> getPortShipInfoList() {
		return portShipInfoList;
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
						NodeList childNodeList = node.getChildren().extractAllNodesThatMatch(childNodeFilter, true);
						//匹配9位数字的MMSI
						Pattern pattern = Pattern.compile("\\d{9}");
						
						for (int j = 0; j < childNodeList.size(); j++) {
							if (childNodeList.elementAt(j) instanceof TableRow) {
								TableRow rowNode = (TableRow) childNodeList.elementAt(j);

								PortShipInfo info = new PortShipInfo();
								int count = 0;
								boolean update = false;

								NodeFilter filter = new TagNameFilter("td");
								NodeList list = rowNode.getChildren().extractAllNodesThatMatch(filter, true);
								for (int k = 0; k < list.size(); k++) {
									update = true;
									// System.out.println(list.elementAt(k).toHtml());
									// if (rowNode.getChildren().elementAt(k)
									// instanceof TableColumn) {
									TableColumn columnNode = (TableColumn) list.elementAt(k);

									if (count == 0) {
										LinkTag link = (LinkTag)columnNode.getChild(0);
										Matcher matcher = pattern.matcher(link.getLink());
										while(matcher.find()) {
											info.setMMSI(Integer.valueOf(matcher.group()));
											System.out.println("MMSI="+matcher.group());
										}
										shipInfoLinkSet.add(link.getLink());
										System.out.println(link.getLink());
										info.setShipName(columnNode.toPlainTextString());
									}

									if (count == 1) {
										// System.out.println(columnNode.toHtml());
										info.setDynamicInfo(columnNode.toPlainTextString());
									}

									if (count == 2) {
										// System.out.println(columnNode.toHtml());
										info.setPortName(columnNode.toPlainTextString());
									}

									if (count == 3) {
										// System.out.println(columnNode.toHtml());
										info.setDate(columnNode.toPlainTextString());
									}
									count++;
									// }
								}

								if (update)
									portShipInfoList.add(info);
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
			System.out.println(portShipInfoList.size());
			for (PortShipInfo info : portShipInfoList) {
				System.out.println(info.getShipName() + "\t\t" + info.getDynamicInfo() + "\t\t" + info.getPortName()
						+ "\t\t" + info.getDate());
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}

	}
}
