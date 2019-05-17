package com.tao.mqtttest.doing;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import com.google.gson.Gson;
import com.tao.mqlibrary.utils.o;
import com.tao.mqtttest.Bean.MQ4GOutStaue;
import com.tao.mqtttest.Bean.MQCloseOrder;
import com.tao.mqtttest.Bean.MQDeviceActivate;
import com.tao.mqtttest.Bean.MQDeviceInfo;
import com.tao.mqtttest.Bean.MQDevicesHeartbeat;
import com.tao.mqtttest.Bean.MQGoodsSyncPush;
import com.tao.mqtttest.Bean.MQPayOrder;
import com.tao.mqlibrary.mqsun.Message.MqMssage;
import com.tao.mqlibrary.mqsun.Message.MqOperate;
import com.tao.mqlibrary.mqsun.Message.ServerMsg;
import com.tao.mqlibrary.mqsun.iml.IHandlerCreate;
import com.tao.mqlibrary.mqsun.iml.IMq;
import com.tao.mqlibrary.mqsun.iml.MqStatueCall;
import com.tao.mqlibrary.mqsun.mq.MqHelper;
import com.tao.mqtttest.Bean.MQPayQuery;
import com.tao.mqtttest.Bean.MQPrepareGoods;
import com.tao.mqtttest.Bean.MQRefoundPush;
import com.tao.mqtttest.Bean.MQRequestAdvData;
import com.tao.mqtttest.Bean.MQSendDoorOpen;
import com.tao.mqtttest.Bean.MQSoftVersionUpdata;
import com.tao.mqtttest.Bean.MQUpdataAdv;
import com.tao.mqtttest.Bean.MQUpdataUI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Mqtt_client_server extends Service implements IMq {

    private String serverUrl = "tcp://2p172088f2.iok.la:10000";
    private String ip = "tcp://2p172088f2.iok.la:10000";
    private String port = "tcp://2p172088f2.iok.la:10000";
    private String clientid = "id1";
    private String userName = "usr1";
    private String password = "usr1";
    private String[] theme;
    String[] topis = new String[]{"usr2"};
    String TAG = getClass().getSimpleName();
    private MqHelper mqHelper;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    int messageId = 0;

    @Override
    public void send(MqMssage mssage) throws Exception {
        if (!mqHelper.isConnect()) {
            sengUnConnect();
            return;
        }
        // 向指定主题发送消息
        mssage.themeS = getsubString(mssage.themeS[0]);
        mqHelper.send(mssage);

    }

    private void sengUnConnect() {

        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq  un connect!";
        EventBus.getDefault().post(serverMsg);

    }


    boolean isConnect = false;

    @Override
    public void connect() throws Exception {

        if (mqHelper != null && mqHelper.isConnect()) {
            sendAleradyConnect();
            return;
        }

        try {
            if (mqHelper != null) {
                mqHelper.reConnect();
                return;
            }

            Log.e(TAG, "create_mq");
            mqHelper = new MqHelper.Build(getApplicationContext())
                    .ip(ip)
                    .port(port)
                    .clientid(clientid)
                    .setAutoReconnect(true)
                    .setAutoReconnectTime(5)
                    .usr(userName)
                    .pss(password)
                    .theme(theme)
                    .sub(topis)
                    .setMqStatueCall(new MqStatueCall() {
                        @Override
                        public void OnConnectStatueChange(boolean conn) {
                            Log.e(TAG, "OnConnectStatueChange " + conn);
                            if (conn) {
                                sendCennect();
                            } else {
                                sendUnCennect();
                            }
                        }

                        @Override
                        public void sendDataStatue(boolean send, MqMssage message) {
                            if (send) {
                                sendComplete(message);
                            } else {
                                sendFailed(message);
                            }
                        }

                        @Override
                        public void OnMessage(MqMssage mqMessage) {
                            Log.e(TAG, "OnMessage " + mqMessage.toString());
                            Log.e(TAG, "mqMessage.message " + mqMessage.message);
                            sendReceiver(mqMessage);
                        }
                    })
                    .setPrepareCall(new IHandlerCreate() {
                        @Override
                        public void OnHandlerLooper() {
                            try {
                                mqHelper.connect();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .build();
//            mqHelper.connect();
        } catch (Exception e) {
            e.printStackTrace();
            sendUnCennect();
        }

    }

    private void sendReceiver(MqMssage s) {
//        ServerMsg serverMsg = new ServerMsg();
//        serverMsg.from = ServerMsg.Type.server;
//        serverMsg.msg =  s;
//        EventBus.getDefault().post(s);
        try {
            MqMessageHelper.typeMessage(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendAleradyConnect() {

        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq alerady connect!";
        EventBus.getDefault().post(serverMsg);

    }

    @Override
    public void disconnect() throws Exception {

        if (mqHelper != null)
            mqHelper.disconnect();

    }

    @Override
    public void reConnect() throws Exception {
        if (mqHelper != null)
            mqHelper.reConnect();

    }

    @Subscribe()
    public void onMessageSend(MqMssage mqMssage) {
        Log.e(TAG, " 收到消息:" + mqMssage.toString());
        try {
            send(mqMssage);
        } catch (Exception e) {
            e.printStackTrace();
            sendFailed(mqMssage);
        }
    }

    private void sendFailed(MqMssage message) {
        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq send data failed! " + " \n" + message;
        serverMsg.obj = new Gson().toJson(message);
        serverMsg.send = false;
        EventBus.getDefault().post(serverMsg);
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onOperate(MqOperate mqOperate) {
        Log.e(TAG, " 收到操作:" + mqOperate.toString());
        switch (mqOperate.type) {

            case connect:
                serverUrl = "tcp://" + mqOperate.msg.ip + ":" + mqOperate.msg.port;
                ip = mqOperate.msg.ip;
                port = mqOperate.msg.port;
                clientid = mqOperate.msg.clientId;
                theme = getsubString(mqOperate.msg.theme);
                userName = mqOperate.msg.userName;
                password = mqOperate.msg.password;
                topis = getsubString(mqOperate.msg.sub);
                try {
                    connect();
                } catch (Exception e) {
                    e.printStackTrace();
                    sendUnCennect();
                }
                break;
            case reconnect:

                try {
                    reConnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case close:

                try {
                    disconnect();
                    isConnect = false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sendClose();
                }
                break;
        }

    }

    private String[] getsubString(String theme) {

        String[] split = theme.split("\\|");
        if (split.length > 0)
            return split;
        return new String[0];
    }

    private void sendUnCennect() {

        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq connect failed !";
        EventBus.getDefault().post(serverMsg);

    }

    private void sendComplete(MqMssage message) {
        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq send data complete!" + " \n" + message;
        serverMsg.obj = new Gson().toJson(message);
        serverMsg.send = true;
        EventBus.getDefault().post(serverMsg);
    }

    private void sendSub(String[] theme) {
        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "订阅!" + theme;
        EventBus.getDefault().post(serverMsg);
    }

    private void sendCennect() {
        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq connect success!";
        EventBus.getDefault().post(serverMsg);
    }

    private void sendClose() {
        ServerMsg serverMsg = new ServerMsg();
        serverMsg.from = ServerMsg.Type.server;
        serverMsg.msg = "mq connect closed !";
        EventBus.getDefault().post(serverMsg);
    }

    @Subscribe()
    public void goodSyn(MQGoodsSyncPush goodsSync) {
        o.e(TAG,"sync_Goods:"+goodsSync.toString());
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(goodsSync.getMsgid());
        mssage.message = new Gson().toJson(goodsSync);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"goodsyn"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void payOrder(MQPayOrder payorder) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(payorder.getMsgid());
        mssage.message = new Gson().toJson(payorder);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"createpayorder"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void payQuery(MQPayQuery mqPayQuery) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqPayQuery.getMsgid());
        mssage.message = new Gson().toJson(mqPayQuery);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"payState"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void refund(MQRefoundPush mqRefoundPush) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqRefoundPush.getMsgid());
        mssage.message = new Gson().toJson(mqRefoundPush);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"refund"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void goodsOut(MQ4GOutStaue goodsOut) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(goodsOut.getMsgid());
        mssage.message = new Gson().toJson(goodsOut);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"tobacco"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void heart(MQDevicesHeartbeat mqDevicesHeartbeat) {

        o.e(TAG,"heart:"+mqDevicesHeartbeat.toString());
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqDevicesHeartbeat.getMsgid());
        mssage.message = new Gson().toJson(mqDevicesHeartbeat);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"heartbeat"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void soft(MQSoftVersionUpdata softVersionUpdata) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(softVersionUpdata.getMsgid());
        mssage.message = new Gson().toJson(softVersionUpdata);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"upgradeApk"};
        try {
//            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void upgradeUI(MQUpdataUI updataUI) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(updataUI.getMsgid());
        mssage.message = new Gson().toJson(updataUI);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"upgradeUI"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void upgradeAdv(MQUpdataAdv updataAdv) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(updataAdv.getMsgid());
        mssage.message = new Gson().toJson(updataAdv);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{""};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void closeOrder(MQCloseOrder closeOrder) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(closeOrder.getMsgid());
        mssage.message = new Gson().toJson(closeOrder);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"closeOrder"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void pushDeviceInfo(MQDeviceInfo mqDeviceInfo) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqDeviceInfo.getMsgid());
        mssage.message = new Gson().toJson(mqDeviceInfo);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"version"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void pushPrepareGoods(MQPrepareGoods mqPrepareGoods) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqPrepareGoods.getMsgid());
        mssage.message = new Gson().toJson(mqPrepareGoods);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"shipment"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void requestAdv(MQRequestAdvData mqAdvData) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqAdvData.getMsgid());
        mssage.message = new Gson().toJson(mqAdvData);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"advertPlan"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void registerDevices(MQDeviceActivate mqDeviceActivate) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(mqDeviceActivate.getMsgid());
        mssage.message = new Gson().toJson(mqDeviceActivate);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"activation"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void sendOpenDoor(MQSendDoorOpen doorOpen) {
        MqMssage mssage = new MqMssage();
        mssage.messageId = Integer.parseInt(doorOpen.getMsgid());
        mssage.message = new Gson().toJson(doorOpen);
        mssage.type = MqMssage.Type.send;
        mssage.themeS = new String[]{"opendoor"};
        try {
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
