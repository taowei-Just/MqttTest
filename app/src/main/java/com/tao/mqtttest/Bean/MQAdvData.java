package com.tao.mqtttest.Bean;

import java.util.List;

/**
 *  
 *  
 */
public class MQAdvData {

    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String imei;//": "设备唯一标识",
    List <Data>data ;
    public  static class Data {
        String advId; //广告位Id",
        String advUrl;
        String advType;  // 广告类型 0:文字,1:图片 2:视频
        String md5 ;
        String advTime ;

        public String getAdvTime() {
            return advTime;
        }

        public void setAdvTime(String advTime) {
            this.advTime = advTime;
        }

        public String getAdvId() {
            return advId;
        }

        public void setAdvId(String advId) {
            this.advId = advId;
        }

        public String getAdvUrl() {
            return advUrl;
        }

        public void setAdvUrl(String advUrl) {
            this.advUrl = advUrl;
        }

        public String getAdvType() {
            return advType;
        }

        public void setAdvType(String advType) {
            this.advType = advType;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "advId='" + advId + '\'' +
                    ", advUrl='" + advUrl + '\'' +
                    ", advType='" + advType + '\'' +
                    ", md5='" + md5 + '\'' +
                    ", advTime='" + advTime + '\'' +
                    '}';
        }
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
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

    @Override
    public String toString() {
        return "MQAdvData{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", imei='" + imei + '\'' +
                ", data=" + data +
                '}';
    }
}
