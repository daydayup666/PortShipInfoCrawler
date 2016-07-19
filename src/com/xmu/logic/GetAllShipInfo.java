package com.xmu.logic;

import java.util.ArrayList;

import com.xmu.javaBean.PortShipInfo;
import com.xmu.javaBean.ShipBaseInfo;
import com.xmu.javaBean.ShipBodyInfo;
import com.xmu.javaBean.ShipMainInfo;
import com.xmu.javaBean.ShipTonnage;

public class GetAllShipInfo {
	/**
	 * 厦门港船舶动态信息
	 */
	ArrayList<PortShipInfo> shipInfos;
	/**
	 * 船舶主要信息
	 */
	ArrayList<ShipMainInfo> mainInfos;
	/**
	 * 船舶基本信息
	 */
	ArrayList<ShipBaseInfo> baseInfos;
	/**
	 * 船体信息
	 */
	ArrayList<ShipBodyInfo> bodyInfos;
	/**
	 * 吨位
	 */
	ArrayList<ShipTonnage> tonnages;
	
	int count = 0;
	
	public void getShipInfo() {
		long start = System.currentTimeMillis();
        ShipDynamicInfoParser parser = new ShipDynamicInfoParser(
                "http://www.chinaports.com/shipline/1/20/26/shipstatView");
        parser.parseUrl();
       
		
        ShipSpecificInfoParser shipSpecificInfoParser = new ShipSpecificInfoParser();
        shipSpecificInfoParser.extractAllShipInfo();;
        
        shipInfos = parser.getPortShipInfoList();
        mainInfos = shipSpecificInfoParser.getShipMainInfosList();
        baseInfos = shipSpecificInfoParser.getShipBaseInfosList();
        bodyInfos = shipSpecificInfoParser.getShipBodyInfosList();
        tonnages = shipSpecificInfoParser.getShipTonnagesList();
        
        
        for(int i=0;i<shipInfos.size();i++) {
			
			
				System.out.println(shipInfos.get(i).getShipName() + "\t\t" + 
						shipInfos.get(i).getDynamicInfo() + "\t\t" +
						shipInfos.get(i).getPortName()+ "\t\t" + 
						shipInfos.get(i).getDate());	
				System.out.println("船舶主要资料："+mainInfos.get(i).getMMSI()+"\n"+
						"船名:"+mainInfos.get(i).getShipName()+"\n"
						+"中文船名："+mainInfos.get(i).getShipChineseName()+"\n"
						+"呼号："+mainInfos.get(i).getCallSign()+"\n"
						+"国籍："+mainInfos.get(i).getNationality()+"\n"
						+"船东："+mainInfos.get(i).getShipOwner()+"\n"+
						"船舶类型："+mainInfos.get(i).getShipType()+"\n"+
						"船长："+mainInfos.get(i).getShipLength()+"\n"+
						"船宽："+mainInfos.get(i).getShipWidth()+"\t\t"+"最后时间"+mainInfos.get(i).getLastTime()+"\n"+
						"吃水："+mainInfos.get(i).getDraft()+"\t\t"+"IMO:"+mainInfos.get(i).getIMO()+"\n"+
						"MMSI："+mainInfos.get(i).getMMSI());
				System.out.println("船舶基本资料："+baseInfos.get(i).getMMSI()+"\n"+
						"船名:"+baseInfos.get(i).getShipName()+"\t\t"+"国籍："+baseInfos.get(i).getNationality()+"\n"+
						"原船名："+baseInfos.get(i).getOriginShipName()+"\t\t"+"原船籍："+baseInfos.get(i).getOriginNationality()+"\n"+
						"中文船名："+baseInfos.get(i).getShipChineseName()+"\t\t"+"船速："+baseInfos.get(i).getShipSpeed()+"\n"+
						"MMSI："+baseInfos.get(i).getMMSI()+"\t\t"+"造船厂："+baseInfos.get(i).getCreateShipFactory()+"\n"+
						"IMO："+baseInfos.get(i).getIMO()+"\t\t"+"造船地点："+baseInfos.get(i).getCreateShipPlace()+"\n"+
						"呼号："+baseInfos.get(i).getCallSign()+"\t\t"+"造船日期："+baseInfos.get(i).getCreateShipTime()+"\n"+
						"船级社："+baseInfos.get(i).getShipChineseName()+"\t\t"+"安放龙骨时间："+baseInfos.get(i).getLayKeelTime()+"\n"+
						"船籍港："+baseInfos.get(i).getShipHomePort()+"\t\t"+"下水时间："+baseInfos.get(i).getLaunchingTime());
				System.out.println("船体信息:"+bodyInfos.get(i).getMMSI()+"\n"+
						"船舱数："+bodyInfos.get(i).getCabinNum()+"\t\t"+"冷藏集装箱:"+bodyInfos.get(i).getContainers()+"m2"+"\n"+
						"甲板数："+bodyInfos.get(i).getDeckNum()+"\t\t"+"翼液货舱:"+bodyInfos.get(i).getWingTank()+"\n"+
						"总长："+bodyInfos.get(i).getTotalLength()+"\t\t"+"液货舱:"+bodyInfos.get(i).getCargoTank()+"\n"+
						"型深："+bodyInfos.get(i).getMolededDepth()+"\t\t"+"中心液货舱:"+bodyInfos.get(i).getCenterCargoTank()+"\n"+
						"型宽："+bodyInfos.get(i).getMolededWidth()+"\t\t"+"甲板液货舱:"+bodyInfos.get(i).getDeckCargoTank()+"\n"+
						"吃水："+bodyInfos.get(i).getDraft()+"\t\t"+"污水舱:"+bodyInfos.get(i).getBilgeTank()+"\n"+
						"排水量："+bodyInfos.get(i).getDisplacement()+"\t\t"+"是否配置原油洗舱:"+bodyInfos.get(i).isConfiguredWashing()+"\n"+
						"冷藏总容量："+bodyInfos.get(i).getTotalRefrerate()+"\t\t"+"是否装有惰性气体:"+bodyInfos.get(i).isEquippedInertGas());
				System.out.println("吨位:"+tonnages.get(i).getMMSI()+"\n"+
						"总吨位："+tonnages.get(i).getTotalTonnage()+"\t\t"+"载重吨："+tonnages.get(i).getLoadingTonnage()+"\n"+
						"净吨："+tonnages.get(i).getNetTonnage()+"\n");
				
        }
        System.out.println("总共爬取网页："+shipInfos.size());
        long end = System.currentTimeMillis();
		System.out.println("爬取完成，共花费时间"+(end-start)/1000+"s\n");
	}
}
