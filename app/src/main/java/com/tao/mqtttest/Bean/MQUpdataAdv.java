package com.tao.mqtttest.Bean;

import java.util.List;

public class MQUpdataAdv {


             String devId ;// : String 设备编号String ，
            String msgid ;// : String 消息编号String ,
            String msgtype ;// : String 1014String ,

    List<Data> data;

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

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    class Data {
 String location;//”出货货道”,
                String num;//”出货数量”

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "location='" + location + '\'' +
                    ", num='" + num + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MQServerOutState{" +
                "devId='" + devId + '\'' +
                ", msgid='" + msgid + '\'' +
                ", msgtype='" + msgtype + '\'' +
                ", data=" + data +
                '}';
    }
}
