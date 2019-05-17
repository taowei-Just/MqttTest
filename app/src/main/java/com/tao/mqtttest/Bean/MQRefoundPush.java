package com.tao.mqtttest.Bean;

public class MQRefoundPush {


    String imei;// String 设备唯一标识String ,
    String devId;// String 设备编号String ，
    String msgid;// String 消息编号String ,
    Data data;//


    class Data {

        String payType;//”: “退款类型”  wx/ali,
        String price;//": "退款金额"

        String orderNo;// String 商户IdString


        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "payType='" + payType + '\'' +
                    ", price='" + price + '\'' +
                    ", orderNo='" + orderNo + '\'' +
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
