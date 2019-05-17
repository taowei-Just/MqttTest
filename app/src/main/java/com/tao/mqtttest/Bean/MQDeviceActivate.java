package com.tao.mqtttest.Bean;

/**
 * 4g
 * 发布出货状态到服务器出货状态
 */
public class MQDeviceActivate {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String imei;
    Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "MQDeviceActivate{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", data=" + data +
                ", imei='" + imei + '\'' +
                '}';
    }

    public static class Data {

    }
}
