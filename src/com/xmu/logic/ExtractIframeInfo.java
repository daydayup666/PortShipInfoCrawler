package com.xmu.logic;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.Div;
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
			
			
			for(int i=0;i<divList.size();i++) {
				Div divNode =(Div)divList.elementAt(i);
				if(divNode.getAttribute("class")!=null&&divNode.getAttribute("class").equals("box-table")) {
					NodeFilter tableFilter = new NodeClassFilter(TableTag.class);
					NodeList tableList = divNode.getChildren().extractAllNodesThatMatch(tableFilter,true);
					
					
					System.out.println(tableList.size());
				}
			}
		} catch (ParserException e) {
		
			e.printStackTrace();
		}
	}
}
