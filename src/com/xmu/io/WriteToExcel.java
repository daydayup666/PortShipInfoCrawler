package com.xmu.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.xmu.javaBean.PortShipInfo;
import com.xmu.logic.ShipDynamicInfoParser;
import com.xmu.logic.ShipSpecificInfoParser;
/**
 * Created by Administrator on 2016/6/21.
 */
public class WriteToExcel {
	String[] title ={"船舶信息","动态信息","口岸名称","时间"};
	ArrayList<PortShipInfo> shipInfos;
    public void writeExcel() {
    	long start = System.currentTimeMillis();
        ShipDynamicInfoParser parser = new ShipDynamicInfoParser(
                "http://www.chinaports.com/shipline/1/20/26/shipstatView");
        parser.parseUrl();
        ShipSpecificInfoParser shipSpecificInfoParser = new ShipSpecificInfoParser();
        shipSpecificInfoParser.shipInfoParser();
        shipInfos = parser.getPortShipInfoList();
        try {
        	String filePath ="PortShipInfo.xls";
        	OutputStream os = new FileOutputStream(filePath);
			WritableWorkbook book =Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("厦门港船舶动态信息", 0);
			Label label;
			for(int i=0;i<title.length;i++) {
				// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
				// 在Label对象的子对象中指明单元格的位置和内容
				label = new Label(i,0,title[i]);
				label = new Label(i,0,title[i],getHeader());
				sheet.addCell(label);
			}
			
			for(int i=0;i<shipInfos.size();i++) {
				
					label = new Label(0,i+1,shipInfos.get(i).getShipName());
					sheet.addCell(label);
					
					label = new Label(1,i+1,shipInfos.get(i).getDynamicInfo());
					sheet.addCell(label);
					
					label = new Label(2,i+1,shipInfos.get(i).getPortName());
					sheet.addCell(label);
					
					label = new Label(3,i+1,shipInfos.get(i).getDate());
					
					
					
					sheet.addCell(label);				
			}
			book.write();
			book.close();
			long end = System.currentTimeMillis();
			System.out.println("提取完成，共花费时间"+(end-start)/1000+"s");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
		}
        
    }
	public static WritableCellFormat getHeader() {
		WritableFont font = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD);
		try {
			font.setColour(Colour.BLUE);
		} catch (WriteException e1) {
	
			e1.printStackTrace();
		}
		WritableCellFormat format = new WritableCellFormat(font);
		try {
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setBackground(Colour.YELLOW);
		} catch (WriteException e) {
			
			e.printStackTrace();
		}
		return format;
	}
}
