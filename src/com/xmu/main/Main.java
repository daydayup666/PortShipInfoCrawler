package com.xmu.main;

import java.util.Date;

import com.xmu.logic.GetAllShipInfo;
import com.xmu.logic.ShipInfoExtraction;
public class Main {

	public static void main(String[] args) {
//		GetAllShipInfo  get = new GetAllShipInfo();
//		get.getShipInfo();
		ShipInfoExtraction extraction = new ShipInfoExtraction();
		String url = "http://www.chinaports.com/shiptracker/shipinit.do?method=shipInfo&userid=412703830&num="+new Date().getTime();
		extraction.extract(url);
	}
}
