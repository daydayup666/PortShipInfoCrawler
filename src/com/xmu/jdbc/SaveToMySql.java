package com.xmu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.xmu.javaBean.NecessaryShipInfo;
import com.xmu.javaBean.PortShipInfo;
import com.xmu.logic.GetAllShipInfo;

public class SaveToMySql {
	ArrayList<PortShipInfo> dynamicShipInfos;
	ArrayList<NecessaryShipInfo> necessaryShipInfos;
	Connection conn = null;	
	public SaveToMySql() {
		GetAllShipInfo  info = new GetAllShipInfo();
		info.getShipInfo();
		dynamicShipInfos = info.getShipInfos();
		necessaryShipInfos = info.getNecessaryShipInfos();	
		conn = getConn();
	}
	
	private Connection getConn() {		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/shipinfo_db?autoReconect=true&useSSL=false";
		String username = "root";
		String password = "123456";
		
		
		try {
			Class.forName(driver);
			conn =	DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: 捕获SQL异常
		}
		return conn;		
	}
	
	private void insert(NecessaryShipInfo shipInfo) {
		System.out.println("开始保存到MySQL数据库...");
		String sql = "insert into necessaryshipinfo_table "
				+ "(MMSI,shipName,shipStatus,shipType,longitute,"
				+ "latitude,totalTonnage,loadingTonnage,netTonnage,extractTime) "
				+ "values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement)conn.prepareStatement(sql);
			pstmt.setInt(1, shipInfo.getMMSI());
			pstmt.setString(2, shipInfo.getShipName());
			pstmt.setString(3, shipInfo.getShipStatus());
			pstmt.setString(4, shipInfo.getShipType());
			pstmt.setString(5, shipInfo.getLongitude());
			pstmt.setString(6, shipInfo.getLatitude());
			pstmt.setFloat(7, shipInfo.getTotalTonnage());
			pstmt.setFloat(8, shipInfo.getLoadingTonnage());
			pstmt.setFloat(9, shipInfo.getNetTonnage());
			pstmt.setLong(10, shipInfo.getExtractTime());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void batchInsert() {
		System.out.println("necessaryShipInfos.size()="+necessaryShipInfos.size());
		for(NecessaryShipInfo info :necessaryShipInfos) {
			
			insert(info);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
