package com.tao.mqtttest.Bean;

/**
 * 4g
 * 发布出货状态到服务器出货状态
 */
public class MQ4GOutStaue {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String ssid;//": "设备唯一标识",
    String msgdata;
    String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
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

    public String getMsgdata() {
        return msgdata;
    }

    public void setMsgdata(String msgdata) {
        this.msgdata = msgdata;
    }

    @Override
    public String toString() {
        return "MQ4GOutStaue{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", imei='" + ssid + '\'' +
                ", msgdata='" + msgdata + '\'' +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
