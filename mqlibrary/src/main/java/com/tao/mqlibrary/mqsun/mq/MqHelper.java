package com.tao.mqlibrary.mqsun.mq;


import android.content.Context;
import android.content.SharedPreferences;

import com.tao.mqlibrary.mqsun.Message.MqMssage;
import com.tao.mqlibrary.mqsun.iml.IHandlerCreate;
import com.tao.mqlibrary.mqsun.iml.IMq;
import com.tao.mqlibrary.mqsun.iml.MqStatueCall;

import org.eclipse.paho.client.mqttv3.MqttClient;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MqHelper implements IMq {
    String TAG = getClass().getSimpleName();
    Build build;
    private MqttClient mqttClient;
    boolean isConnect = false;
    boolean connectIng = false;
    boolean reconn = false;
    private ThreadPoolExecutor executor;
    private MqRuan mqRun;

    public static int getMsgId(Context context, int i) {
        SharedPreferences shre =  context.getSharedPreferences("config" , Context.MODE_MULTI_PROCESS);
        int msgid = shre.getInt("msgid", i);
        SharedPreferences.Editor edit = shre.edit();
        edit.putInt("msgid" ,msgid==Integer.MAX_VALUE?0: msgid+1);
        edit.commit();
        return msgid;
    }

    public boolean isConnectIng() {
        return connectIng;
    }

    private MqHelper(){
    }
    private MqHelper(Build build) throws Exception {
        this.build = build;
        createLoop();
    }

    private void createLoop() {
        if (executor == null || executor.isShutdown()) {
            executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
            mqRun = new MqRuan(this, new IHandlerCreate() {
                @Override
                public void OnHandlerLooper() {
                    if (build.isAutoReconnect()) {
                        try {
                            connect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    build.prepareCall.OnHandlerLooper();
                }
            });
            executor.submit(mqRun);
        }
    }

    public void  destoryMq(){
        mqRun.destory();
    }

    @Override
    public void send(MqMssage mssage) throws Exception {

        if (mqRun==null || !mqRun.handlepPrepare)
            throw  new Exception( "mq_unRuning");
        mqRun.send(mssage);
    }

    @Override
    public void connect() throws Exception {
        mqRun.connect();
    }

    @Override
    public void disconnect() throws Exception {
        mqRun.disconnect();
    }

    @Override
    public void reConnect() throws Exception {
        mqRun.reConnect();
    }
    public boolean isConnect() {
        return mqRun.isConnect();
    }

    public static class Build implements MqStatueCall {

        public IHandlerCreate prepareCall;
        private int keepAliveTime = 20;
        private int connectTimeout = 5 * 1000;
        private String head = "tcp://";
        private String ip = "id1";
        private String port = "id1";
        private String clientid = "id1";
        private String userName = "usr1";
        private String password = "usr1";
        private String [] theme  ;
        private String[] topis = new String[]{};
        MqStatueCall statueCall;
        private Context context;
        boolean autoReconnect = false;
        int autoReconnectTime = 20;
        int reConnectCount = -1;


        public Build(Context context) {
            this.context = context;
        }

        public Build setPrepareCall(IHandlerCreate prepareCall) {
            this.prepareCall = prepareCall;
            return this;

        }

        public Build setAutoReconnect(boolean autoReconnect) {
            this.autoReconnect = autoReconnect;
            return this;
        }

        public Build setAutoReconnectTime(int autoReconnectTime) {
            this.autoReconnectTime = autoReconnectTime;
            return this;

        }

        public Build setMqStatueCall(MqStatueCall statueCall) {
            this.statueCall = statueCall;
            return this;

        }

        public Build setKeepAliveTime(int keepAliveTime) {
            this.keepAliveTime = keepAliveTime;
            return this;

        }

        public Build setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;

        }

        public Build config(String head ,String ip ,String port , String clientid ,String usr ,String pss) {
            return  head(head).ip(ip)  .port(port) .clientid(clientid)  .usr(usr)  .pss(pss);
        }

        public Build head(String head) {
            this.head = head;
            return this;
        }

        public Build ip(String ip) {
            this.ip = ip;
            return this;
        }

        public Build port(String port) {
            this.port = port;
            return this;
        }

        public Build clientid(String id) {
            this.clientid = id;
            return this;
        }

        public Build usr(String usr) {
            this.userName = usr;
            return this;
        }

        public Build pss(String pss) {
            this.password = pss;
            return this;
        }

        public Build sub(String[] subs) {
            this.topis = subs;
            return this;
        }

        public Build theme(String[] theme) {
            this.theme = theme;
            return this;
        }


        public String getServerUrl() {
            return head + ip + ":" + port;
        }

        public MqHelper build() throws Exception {
            return new MqHelper(this);
        }

        public String getFilesDir() {
            return context.getFilesDir().getAbsolutePath();
        }

        @Override
        public void OnConnectStatueChange(boolean conn) {
            if (statueCall != null)
                statueCall.OnConnectStatueChange(conn);
        }

        @Override
        public void sendDataStatue(boolean send, MqMssage message) {
            if (statueCall != null)
                statueCall.sendDataStatue(send, message);
        }

        @Override
        public void OnMessage(MqMssage mqMessage) {
            if (statueCall != null)
                statueCall.OnMessage(mqMessage);
        }

        public int getKeepAliveTime() {
            return keepAliveTime;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public String getIp() {
            return ip;
        }

        public String getPort() {
            return port;
        }

        public String getClientid() {
            return clientid;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public String[] getTheme() {
            return theme;
        }

        public String[] getTopis() {
            return topis;
        }

        public MqStatueCall getStatueCall() {
            return statueCall;
        }

        public Context getContext() {
            return context;
        }

        public boolean isAutoReconnect() {
            return autoReconnect;
        }

        public int getAutoReconnectTime() {
            return autoReconnectTime;
        }

        public int getReConnectCount() {
            return reConnectCount;
        }

        public String clientid() {
            return clientid ;
        }

        public String[] topis() {
            return topis ;
        }
    }

}
