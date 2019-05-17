package com.tao.mqtttest.doing;

import com.google.gson.Gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tao.mqlibrary.utils.o;
import com.tao.mqtttest.Bean.MQ4GOutStaue;
import com.tao.mqtttest.Bean.MQAdvData;
import com.tao.mqtttest.Bean.MQCloseOrder;
import com.tao.mqtttest.Bean.MQDeviceActivate;
import com.tao.mqtttest.Bean.MQDeviceActivateServer;
import com.tao.mqtttest.Bean.MQDeviceInfo;
import com.tao.mqtttest.Bean.MQDevicesHeartbeat;
import com.tao.mqtttest.Bean.MQGoodsOutCommand;
import com.tao.mqtttest.Bean.MQGoodsSync;
import com.tao.mqtttest.Bean.MQGoodsSyncPush;
import com.tao.mqtttest.Bean.MQPayOrder;
import com.tao.mqlibrary.mqsun.Message.MqMssage;
import com.tao.mqlibrary.mqsun.Message.MqOperate;
import com.tao.mqlibrary.mqsun.Message.ServerMsg;
import com.tao.mqlibrary.mqsun.iml.IHandlerCreate;
import com.tao.mqlibrary.mqsun.iml.IMq;
import com.tao.mqlibrary.mqsun.iml.MqStatueCall;
import com.tao.mqlibrary.mqsun.mq.MqHelper;
import com.tao.mqtttest.Bean.MQPayOrderQR;
import com.tao.mqtttest.Bean.MQPayQuery;
import com.tao.mqtttest.Bean.MQPayStatueCAll;
import com.tao.mqtttest.Bean.MQPrepareGoods;
import com.tao.mqtttest.Bean.MQReceiverDoorOpen;
import com.tao.mqtttest.Bean.MQRefoundPush;
import com.tao.mqtttest.Bean.MQRefoundStatue;
import com.tao.mqtttest.Bean.MQRequestAdvData;
import com.tao.mqtttest.Bean.MQSendDoorOpen;
import com.tao.mqtttest.Bean.MQServerOutState;
import com.tao.mqtttest.Bean.MQSoftVersionUpdata;
import com.tao.mqtttest.Bean.MQUpdataAdv;
import com.tao.mqtttest.Bean.MQUpdataUI;
import com.tao.mqlibrary.mqsun.Message.MqMssage;
 

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Tao on 2019/1/10.
 */

class MqMessageHelper {
    static String TAG = "MqMessageHelper";

    public static void typeMessage(MqMssage s) throws Exception {

        switch (s.themeS[0].split("/")[1]) {
            case "goodsyn":
                goodsyn(s);
                break;
            case "createpayorder":
                createpayorder(s);
                break;
            case "payState":
                payState(s);
                break;
            case "refund":
                refund(s);
                break;
            case "outCallback":
                outCallback(s);
                break;
            case "shipmentState":
                shipmentState(s);
                break;
            case "heartbeat":
                break;
            case "version":
                version(s);
                break;
            case "upgradeUI":
                upgradeUI(s);
                break;
            case "advertPlan":
                advertPlan(s);
                break;
            case "activation":
                activation(s);
                break; 
                case "opendoor":
                    opendoor(s);
                break;
        }

        if (s.themeS[0].split("/")[0].equals("tobacco")) {
            command(s);
        }
    }

    private static void opendoor(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQReceiverDoorOpen.class));
    }
 private static void activation(MqMssage s) {
        EventBus.getDefault().postSticky(new Gson().fromJson(s.message, MQDeviceActivateServer.class));
    }

    private static void advertPlan(MqMssage s) {
        java.lang.reflect.Type type = new TypeToken<MQAdvData>() { }.getType();
        MQAdvData mqAdvData = new Gson().fromJson(s.message, type);
        MQAdvData event = new Gson().fromJson(new Gson().toJson(mqAdvData), type);
        o.e(TAG ," " +event.getData().get(0));
        EventBus.getDefault().postSticky(event);
    }

    private static void shipmentState(MqMssage s) {
//        o.e(TAG,"shipmentState :" +s.toString());
        EventBus.getDefault().postSticky(new Gson().fromJson(s.message, MQServerOutState.class));
    }

    private static void outCallback(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQ4GOutStaue.class));
    }

    private static void refund(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQRefoundStatue.class));
    }

    private static void upgradeUI(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQUpdataUI.class));
    }

    private static void command(MqMssage s) {


        EventBus.getDefault().postSticky(new Gson().fromJson(s.message, MQGoodsOutCommand.class));
    }

    private static void version(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQSoftVersionUpdata.class));
    }

    private static void payState(MqMssage s) {

        EventBus.getDefault().post(new Gson().fromJson(s.message, MQPayStatueCAll.class));
    }

    private static void createpayorder(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQPayOrderQR.class));
    }

    private static void goodsyn(MqMssage s) {
        EventBus.getDefault().post(new Gson().fromJson(s.message, MQGoodsSync.class));
    }
}
