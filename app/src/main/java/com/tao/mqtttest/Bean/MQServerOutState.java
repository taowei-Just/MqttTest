package com.tao.mqtttest.Bean;

/**
 * 服务器返回出货状态
 * 
 */
public class MQServerOutState {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String ssid;//": "设备唯一标识",
    String orderNo ;//": " ",
     String status ;

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

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MQServerOutState{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", imei='" + ssid + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
