package com.tao.mqtttest.Bean;

public class MQPayOrder {


    String imei;// String 设备唯一标识String ,
    String devId;// String 设备编号String ，
    String msgid;// String 消息编号String ,
    Data data;//



    public static class Data {
        String payType; //“支付类型”  wx/ali
        String goodName;// String 商品名称String ,
        String price;// String 订单总额String ,
        String goodCode;// String 商户IdString
        String goodNum;// String 商户IdString
        String card_no;// String 商户IdString


        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getGoodName() {
            return goodName;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getGoodCode() {
            return goodCode;
        }

        public void setGoodCode(String goodCode) {
            this.goodCode = goodCode;
        }

        public String getGoodNum() {
            return goodNum;
        }

        public void setGoodNum(String goodNum) {
            this.goodNum = goodNum;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "payType='" + payType + '\'' +
                    ", goodName='" + goodName + '\'' +
                    ", price='" + price + '\'' +
                    ", goodCode='" + goodCode + '\'' +
                    ", goodNum='" + goodNum + '\'' +
                    ", card_no='" + card_no + '\'' +
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
