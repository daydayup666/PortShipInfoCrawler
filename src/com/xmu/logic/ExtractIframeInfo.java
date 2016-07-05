package com.xmu.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.xmu.javaBean.ShipBodyInfo;
import com.xmu.javaBean.ShipTonnage;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ExtractIframeInfo {
	private ShipBodyInfo bodyInfo = new ShipBodyInfo();
	private ShipTonnage tonnage = new ShipTonnage();
	
	public ShipBodyInfo getBodyInfo() {
		return bodyInfo;
	}

	public ShipTonnage getTonnage() {
		return tonnage;
	}

	public void extractIframeInfo(String url) {
		try {		
			Parser parser = new Parser(url);
			NodeFilter divFilter = new NodeClassFilter(Div.class);
			NodeList divList = parser.extractAllNodesThatMatch(divFilter);
			Pattern pat = Pattern.compile("\\d{9}");
			Matcher mat = pat.matcher(url);
			
			while(mat.find()) {
				bodyInfo.setMMSI(Integer.valueOf(mat.group()));
				tonnage.setMMSI(Integer.valueOf(mat.group()));
				System.out.println("MMSI="+mat.group());
			}
			
			for(int i=0;i<divList.size();i++) {
				Div divNode =(Div)divList.elementAt(i);
				if(divNode.getAttribute("class")!=null&&divNode.getAttribute("class").equals("box-table")) {
					NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
					NodeList tableList = divNode.getChildren().extractAllNodesThatMatch(tableFilter,true);
					
					NodeList tableRowList = tableList.elementAt(0).getChildren().extractAllNodesThatMatch(
							new NodeClassFilter(TableRow.class),true);
					for(int j=0;j<tableRowList.size();j++) {
						TableRow row = (TableRow)tableRowList.elementAt(j);						
						NodeList tableColumnList = row.getChildren().extractAllNodesThatMatch(
								new NodeClassFilter(TableColumn.class),true);
						
						if(tableColumnList.elementAt(0).toPlainTextString().equals("船舱数：")){
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setCabinNum(Integer
											.valueOf(matcher.group()));
									System.out.println("船舱数："
											+ matcher.group());
								}
							}
							
							if(tableColumnList.size()==5&&tableColumnList.elementAt(3).toPlainTextString().equals("冷藏集装箱：")){
								Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern.matcher(tableColumnList.elementAt(4).toPlainTextString());
								
								while(matcher.find()) {
								//if(!tableColumnList.elementAt(1).toPlainTextString().isEmpty())	
									bodyInfo.setContainers(Double.valueOf(matcher.group()));
									System.out.println("冷藏集装箱："+matcher.group());
								}
							}
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("甲板数：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									bodyInfo.setDeckNum(Integer.valueOf(matcher.group()));
									System.out.println("甲板数："+matcher.group());
								}
							}						
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("总长：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									bodyInfo.setTotalLength(Double.valueOf(matcher.group()));
									System.out.println("总长："+matcher.group());
								}
							}						
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("型深：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									bodyInfo.setMolededDepth(Double.valueOf(matcher.group()));
									System.out.println("型深："+matcher.group());
								}
							}						
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("型宽：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									bodyInfo.setMolededWidth(Double.valueOf(matcher.group()));
									System.out.println("型宽："+matcher.group());
								}
							}						
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("吃水：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									bodyInfo.setDraft(Double.valueOf(matcher.group()));
									System.out.println("吃水："+matcher.group());
								}
							}						
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("总吨位：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									tonnage.setTotalTonnage(Double.valueOf(matcher.group()));
									System.out.println("总吨位："+matcher.group());
								}
							}
							if(tableColumnList.elementAt(3).toPlainTextString().equals("载重吨：")){
								if(!tableColumnList.elementAt(4).toPlainTextString().equals("&nbsp;")) {
									Pattern pattern = Pattern
											.compile("\\d+\\.?\\d*");
									Matcher matcher = pattern
											.matcher(tableColumnList.elementAt(4)
													.toPlainTextString());
									while(matcher.find()) {
										tonnage.setLoadingTonnage(Double.valueOf(matcher.group()));
										System.out.println("载重吨："+matcher.group());
									}
								}
							}
						}else if(tableColumnList.elementAt(0).toPlainTextString().equals("载重吨：")){
							if(!tableColumnList.elementAt(1).toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while(matcher.find()) {
									tonnage.setNetTonnage(Double.valueOf(matcher.group()));
									System.out.println("载重吨："+matcher.group());
								}
							}
						}
						if(tableColumnList.size()==5) {
							if(tableColumnList.elementAt(3).equals("是否配置原油洗舱：")) {
								if(!tableColumnList.elementAt(4).equals("&nbsp;")&&
										tableColumnList.elementAt(4).toPlainTextString().equals("有&nbsp;")) {
									bodyInfo.setConfiguredWashing(true);
									System.out.println("是否配置原油洗舱："+tableColumnList.elementAt(4).toPlainTextString());
								}
							}else if(tableColumnList.elementAt(3).equals("是否装有惰性气体：")) {
								if(!tableColumnList.elementAt(4).equals("&nbsp;")&&
										tableColumnList.elementAt(4).toPlainTextString().equals("有&nbsp;")) {
									bodyInfo.setEquippedInertGas(true);
									System.out.println("是否装有惰性气体："+tableColumnList.elementAt(4).toPlainTextString());
								}
							}
						}
					}
					//System.out.println(tableList.size());
				}
			}
		} catch (ParserException e) {		
			e.printStackTrace();
		}
	}
}
