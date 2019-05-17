package com.tao.mqtttest.Bean;

public class MQPayOrderQR {
    
    String imei;// String 设备唯一标识String ,
    String devId;// String 设备编号String ，
    String msgid;// String 消息编号String ,
    Data data;//

    public class Data {
        
//        String wxUrl;//":"微信二维码"
//        String aliUrl;//":"支付宝二维码"
        String payUrl;//":"支付二维码"
        String goodName;//":String 商品名称",
        String price;//":String 订单总额",
        String goodCode;//":String 商品编码",
        String goodNum;//":String 商品数量"
        String preferentialPrice;//":"优惠金额"
        String realPrice;//":"实付金额"
        String orderNo;//"订单号",d

        public String getPayUrl() {
            return payUrl;
        }

        public void setPayUrl(String payUrl) {
            this.payUrl = payUrl;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
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

        public String getPreferentialPrice() {
            return preferentialPrice;
        }

        public void setPreferentialPrice(String preferentialPrice) {
            this.preferentialPrice = preferentialPrice;
        }

        public String getRealPrice() {
            return realPrice;
        }

        public void setRealPrice(String realPrice) {
            this.realPrice = realPrice;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "payUrl='" + payUrl + '\'' +
                    ", goodName='" + goodName + '\'' +
                    ", price='" + price + '\'' +
                    ", goodCode='" + goodCode + '\'' +
                    ", goodNum='" + goodNum + '\'' +
                    ", preferentialPrice='" + preferentialPrice + '\'' +
                    ", realPrice='" + realPrice + '\'' +
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
