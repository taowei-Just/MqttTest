package com.tao.mqtttest.Bean;

/**
 * 4g
 * 发布出货状态到服务器出货状态
 */
public class MQPrepareGoods {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String imei;//": "设备唯一标识",
    Data data;

    public static class Data {
        String orderNo;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "orderNo='" + orderNo + '\'' +
                    '}';
        }
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

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MQPrepareGoods{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", imei='" + imei + '\'' +
                ", data=" + data +
                '}';
    }
}
