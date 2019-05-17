package com.tao.mqtttest.Bean;

/**
 * 收到服务器出货指令
 * 
 */
public class MQGoodsOutCommand {


    String ssid;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String msgtype;// : String 1014String ,
    String msgdata;
    String orderNo;
    String payStatus;  // 0:等待支付,1:已支付
    
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
    public String getMsgtype() {
        return msgtype;
    }
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
    public String getMsgdata() {
        return msgdata;
    }
    public void setMsgdata(String msgdata) {
        this.msgdata = msgdata;
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "MQGoodsOutCommand{" +
                "imei='" + ssid + '\'' +
                ", msgid='" + msgid + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", msgdata='" + msgdata + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", payStatus='" + payStatus + '\'' +
                '}';
    }
}
