package com.tao.mqtttest.Bean;

/**
 * 4g
 * 发布出货状态到服务器出货状态
 */
public class MQSendDoorOpen {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String ssid;//": "设备唯一标识",
    String imei;//": "设备唯一标识",
    Data data;

    public static class Data {
        String card_no;

        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        @Override
        public String toString() {
            return "Date{" +
                    "card_no='" + card_no + '\'' +
                    '}';
        }
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

    @Override
    public String toString() {
        return "MQSendDoorOpen{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", ssid='" + ssid + '\'' +
                '}';
    }
}
