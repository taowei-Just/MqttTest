package com.tao.mqtttest.Bean;

/**
 * 4g
 * 发布出货状态到服务器出货状态
 */
public class MQDeviceInfo {
    
    String devId;// : String 设备编号String ，
    String msgid;// : String 消息编号String ,
    String imei;
    Data data ;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static   class Data{
        String softCode;
        String softName;

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

        @Override
        public String toString() {
            return "Data{" +
                    "softCode='" + softCode + '\'' +
                    ", softName='" + softName + '\'' +
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

    @Override
    public String toString() {
        return "MQDeviceInfo{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
