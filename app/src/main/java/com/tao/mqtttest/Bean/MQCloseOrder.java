package com.tao.mqtttest.Bean;

public class MQCloseOrder {


    String imei;// String 设备唯一标识String ,
    String devId;// String 设备编号String ，
    String msgid;// String 消息编号String ,
    Data data;//



    public static class Data {
       
        String orderNo;

        @Override
        public String toString() {
            return "Data{" +
                    "orderNo='" + orderNo + '\'' +
                    '}';
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MQPayOrder{" +
                "ssid='" + imei + '\'' +
                ", devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", data=" + data +
                '}';
    }
}
