package com.tao.mqtttest.Bean;

public class MQPayStatueCAll {


    String imei;// String 设备唯一标识String ,
    String devId;// String 设备编号String ，
    String msgid;// String 消息编号String ,
    Data data;//

    public class Data {
        


        String orderNo;// String 商户IdString
        String status;// String 商户IdString

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
            return "Data{" +
                    "orderNo='" + orderNo + '\'' +
                    ", status='" + status + '\'' +
                    '}';
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
