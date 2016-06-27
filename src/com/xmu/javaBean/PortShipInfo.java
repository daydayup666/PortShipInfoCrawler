package com.xmu.javaBean;

import java.io.Serializable;
import java.text.DateFormat;

/**
 * Created by Administrator on 2016/6/21.
 */
public class PortShipInfo implements Serializable{
    private String shipName;
    private String dynamicInfo;
    private String portName;
    private String  date;

    public PortShipInfo() {

    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getDynamicInfo() {
        return dynamicInfo;
    }

    public void setDynamicInfo(String dynamicInfo) {
        this.dynamicInfo = dynamicInfo;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
