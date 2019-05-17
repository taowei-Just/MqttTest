package com.tao.mqtttest.Bean;

public class MQDevicesHeartbeat {

            String devId ;// : String 设备编号String ，
            String msgid ;// : String 消息编号String ,
            String msgtype ;// : String 1014String ,
            String imei;// : String 1014String ,
            Data data ;

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
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    class Data {}
    @Override
    public String toString() {
        return "MQServerOutState{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", data=" + data +
                '}';
    }
}
