package com.tao.mqtttest.Bean;


public class MQSoftVersionUpdata {


    String devId;// : String 设备编号String ，
    String imei;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    Data data;

    public Data getData() {
        return data;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setData(Data data) {
        this.data = data;
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
 
    public class Data {
         String softId;
         String softCode;
         String softName;
         String softUrl;

        public String getSoftCode() {
            return softCode;
        }

        public void setSoftCode(String softCode) {
            this.softCode = softCode;
        }

        public String getSoftName() {
            return softName;
        }

        public void setSoftName(String softName) {
            this.softName = softName;
        }

        public String getSoftUrl() {
            return softUrl;
        }

        public void setSoftUrl(String softUrl) {
            this.softUrl = softUrl;
        }

        public String getSoftId() {
            return softId;
        }

        public void setSoftId(String softId) {
            this.softId = softId;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "softId='" + softId + '\'' +
                    ", softCode='" + softCode + '\'' +
                    ", softName='" + softName + '\'' +
                    ", softUrl='" + softUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MQSoftVersionUpdata{" +
                "devId='" + devId + '\'' +
                ", imei='" + imei + '\'' +
                ", msgid='" + msgid + '\'' +
                ", data=" + data +
                '}';
    }
}
