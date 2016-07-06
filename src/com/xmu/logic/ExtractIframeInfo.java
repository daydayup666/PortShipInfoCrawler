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

			while (mat.find()) {
				bodyInfo.setMMSI(Integer.valueOf(mat.group()));
				tonnage.setMMSI(Integer.valueOf(mat.group()));
//				System.out.println("MMSI=" + mat.group());
			}

			for (int i = 0; i < divList.size(); i++) {
				Div divNode = (Div) divList.elementAt(i);
				if (divNode.getAttribute("class") != null
						&& divNode.getAttribute("class").equals("box-table")) {
					NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
					NodeList tableList = divNode.getChildren()
							.extractAllNodesThatMatch(tableFilter, true);

					NodeList tableRowList = tableList
							.elementAt(0)
							.getChildren()
							.extractAllNodesThatMatch(
									new NodeClassFilter(TableRow.class), true);
					for (int j = 0; j < tableRowList.size(); j++) {
						TableRow row = (TableRow) tableRowList.elementAt(j);
						NodeList tableColumnList = row.getChildren()
								.extractAllNodesThatMatch(
										new NodeClassFilter(TableColumn.class),
										true);

						if (tableColumnList.elementAt(0).toPlainTextString()
								.equals("船舱数：")) {
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
//									System.out
//											.println("船舱数：" + matcher.group());
								}
							}

							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("冷藏集装箱：")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(4)
												.toPlainTextString());

								while (matcher.find()) {
									// if(!tableColumnList.elementAt(1).toPlainTextString().isEmpty())
									bodyInfo.setContainers(Double
											.valueOf(matcher.group()));
//									System.out.println("冷藏集装箱："
//											+ matcher.group());
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("甲板数：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setDeckNum(Integer.valueOf(matcher
											.group()));
//									System.out
//											.println("甲板数：" + matcher.group());
								}
							}
							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("翼液货舱：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									bodyInfo.setWingTank(tableColumnList
											.elementAt(4).toPlainTextString()
											.replace("&nbsp", ""));
//									System.out.println("翼液货舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString()
//													.replace("&nbsp", ""));
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("总长：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setTotalLength(Double
											.valueOf(matcher.group()));
//									System.out.println("总长：" + matcher.group());
								}
							}
							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("液货舱：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									bodyInfo.setCargoTank(tableColumnList
											.elementAt(4).toPlainTextString()
											.replace("&nbsp", ""));
//									System.out.println("液货舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString()
//													.replace("&nbsp", ""));
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("型深：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setMolededDepth(Double
											.valueOf(matcher.group()));
//									System.out.println("型深：" + matcher.group());
								}
							}
							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("中心液货舱：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									bodyInfo.setCenterCargoTank(tableColumnList
											.elementAt(4).toPlainTextString()
											.replace("&nbsp", ""));
//									System.out.println("中心液货舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString()
//													.replace("&nbsp", ""));
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("型宽：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setMolededWidth(Double
											.valueOf(matcher.group()));
//									System.out.println("型宽：" + matcher.group());
								}
							}
							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("甲板液货舱：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									bodyInfo.setDeckCargoTank(tableColumnList
											.elementAt(4).toPlainTextString()
											.replace("&nbsp", ""));
//									System.out.println("甲板液货舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString()
//													.replace("&nbsp", ""));
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("吃水：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setDraft(Double.valueOf(matcher
											.group()));
//									System.out.println("吃水：" + matcher.group());
								}
							}

							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("污水舱：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									bodyInfo.setBilgeTank(tableColumnList
											.elementAt(4).toPlainTextString()
											.replace("&nbsp", ""));
//									System.out.println("污水舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString()
//													.replace("&nbsp", ""));
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("排水量：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setDisplacement(Double
											.valueOf(matcher.group()));
//									System.out
//											.println("排水量：" + matcher.group());
								}
							}

							if (tableColumnList.elementAt(3)
									.equals("是否配置原油洗舱：")) {
								if (!tableColumnList.elementAt(4).equals(
										"&nbsp;")
										&& tableColumnList.elementAt(4)
												.toPlainTextString()
												.equals("有&nbsp;")) {
									bodyInfo.setConfiguredWashing(true);
//									System.out.println("是否配置原油洗舱："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString());
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("冷藏总容量：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									bodyInfo.setTotalRefrerate(Double
											.valueOf(matcher.group()));
//									System.out.println("冷藏总容量："
//											+ matcher.group());
								}
							}

							if (tableColumnList.elementAt(3)
									.equals("是否装有惰性气体：")) {
								if (!tableColumnList.elementAt(4).equals(
										"&nbsp;")
										&& tableColumnList.elementAt(4)
												.toPlainTextString()
												.equals("有&nbsp;")) {
									bodyInfo.setEquippedInertGas(true);
//									System.out.println("是否装有惰性气体："
//											+ tableColumnList.elementAt(4)
//													.toPlainTextString());
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("总吨位：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									tonnage.setTotalTonnage(Double
											.valueOf(matcher.group()));
//									System.out
//											.println("总吨位：" + matcher.group());
								}
							}
							if (tableColumnList.elementAt(3)
									.toPlainTextString().equals("载重吨：")) {
								if (!tableColumnList.elementAt(4)
										.toPlainTextString().equals("&nbsp;")) {
									Pattern pattern = Pattern
											.compile("\\d+\\.?\\d*");
									Matcher matcher = pattern
											.matcher(tableColumnList.elementAt(
													4).toPlainTextString());
									while (matcher.find()) {
										tonnage.setLoadingTonnage(Double
												.valueOf(matcher.group()));
//										System.out.println("载重吨："
//												+ matcher.group());
									}
								}
							}
						} else if (tableColumnList.elementAt(0)
								.toPlainTextString().equals("载重吨：")) {
							if (!tableColumnList.elementAt(1)
									.toPlainTextString().equals("&nbsp;")) {
								Pattern pattern = Pattern
										.compile("\\d+\\.?\\d*");
								Matcher matcher = pattern
										.matcher(tableColumnList.elementAt(1)
												.toPlainTextString());
								while (matcher.find()) {
									tonnage.setNetTonnage(Double
											.valueOf(matcher.group()));
//									System.out
//											.println("载重吨：" + matcher.group());
								}
							}
						}
					}
					// System.out.println(tableList.size());
				}
			}
			
//			System.out.println("船体信息:"+bodyInfo.getMMSI()+"\n"+
//					"船舱数："+bodyInfo.getCabinNum()+"\t\t"+"冷藏集装箱:"+bodyInfo.getContainers()+"m2"+"\n"+
//					"甲板数："+bodyInfo.getDeckNum()+"\t\t"+"翼液货舱:"+bodyInfo.getWingTank()+"\n"+
//					"总长："+bodyInfo.getTotalLength()+"\t\t"+"液货舱:"+bodyInfo.getCargoTank()+"\n"+
//					"型深："+bodyInfo.getMolededDepth()+"\t\t"+"中心液货舱:"+bodyInfo.getCenterCargoTank()+"\n"+
//					"型宽："+bodyInfo.getMolededWidth()+"\t\t"+"甲板液货舱:"+bodyInfo.getDeckCargoTank()+"\n"+
//					"吃水："+bodyInfo.getDraft()+"\t\t"+"污水舱:"+bodyInfo.getBilgeTank()+"\n"+
//					"排水量："+bodyInfo.getDisplacement()+"\t\t"+"是否配置原油洗舱:"+bodyInfo.isConfiguredWashing()+"\n"+
//					"冷藏总容量："+bodyInfo.getTotalRefrerate()+"\t\t"+"是否装有惰性气体:"+bodyInfo.isEquippedInertGas()+"\n");
//			System.out.println("吨位"+tonnage.getMMSI()+"\n"+
//					"总吨位："+tonnage.getTotalTonnage()+"\t\t"+"载重吨："+tonnage.getLoadingTonnage()+"\n"+
//					"净吨："+tonnage.getNetTonnage()+"\n");
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
