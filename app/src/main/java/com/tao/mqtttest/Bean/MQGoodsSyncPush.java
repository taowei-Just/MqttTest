package com.tao.mqtttest.Bean;

import java.util.List;

public class MQGoodsSyncPush {

    String imei;
    String devId;
    String msgid;
    String   data;
 
 

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }


    @Override
    public String toString() {
        return "MQGoodsSyncPush{" +
                "ssid='" + imei + '\'' +
                ", devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
