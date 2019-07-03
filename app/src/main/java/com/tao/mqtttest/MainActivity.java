package com.tao.mqtttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tao.mqlibrary.mqsun.Message.MqMssage;
import com.tao.mqlibrary.mqsun.iml.IHandlerCreate;
import com.tao.mqlibrary.mqsun.iml.MqStatueCall;
import com.tao.mqlibrary.mqsun.mq.MqHelper;
import com.tao.mqlibrary.utils.o;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    String tag = getClass().getSimpleName();
    private MqHelper mqHelper;
    private ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = Executors.newSingleThreadExecutor();
        final long t = 50;

//        service.submit(new Runnable() {
//
//            private long aLong = System.currentTimeMillis();
//
//            @Override
//            public void run() {
//                boolean b =true ;
//                while (b) {
//                     create(null);
//                    if (System.currentTimeMillis() - aLong >10*1000 ){
//                        o.e("退出===============================================\n" +
//                                "================================================");
//                        b=false;
//                    }
//                }
//            }
//        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        mq();
                        try {
                            Thread.sleep(t);
                        } catch (InterruptedException e) {
                        }

                        while (mqHelper.isPrepare()){
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                disconnect(null);
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                }
                                reconnect(null);
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {

                                }
                                release(null);
                                try {
                                    Thread.sleep(5*10);
                                } catch (InterruptedException e) {

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    } finally {

                    }
                }

            }
        });
    }


    int id = 1;

    boolean aa = false;

    private void mq() {
        try {
            final String clientid = (++id) + "";
//            final String clientid = id + "";
            mqHelper = new MqHelper.Build(getApplicationContext())
                    .config("tcp://", "tobacco.sun-hyt.com", "1883", clientid, "admin", "123456")
                    .sub(new String[]{"1"})
                    .setAutoReconnect(true)
                    .setAutoReconnectTime(1)
                    .setConnectTimeout(20)
                    .setKeepAliveTime(20)
                    .setPrepareCall(new IHandlerCreate() {
                        @Override
                        public void OnHandlerLooper(boolean prepare) {
                            o.e("OnHandlerLooper " + prepare);
                        }
                    })
                    .setMqStatueCall(new MqStatueCall() {
                        @Override
                        public void OnConnectStatueChange(boolean conn) {
                            o.e(tag, "OnConnectStatueChange " + conn + " " + clientid);
                            if (conn) {
                                sedmessage();
                            }
                        }

                        @Override
                        public void sendDataStatue(boolean send, MqMssage message) {
                            o.e(tag, "sendDataStatue " + message.toString());
                        }

                        @Override
                        public void OnMessage(MqMssage mqMessage) {
                            o.e(tag, "OnMessage " + mqMessage.toString());
                        }
                    }).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sedmessage() {
        if (mqHelper == null || !mqHelper.isConnect())
            return;
        try {
            MqMssage mssage = new MqMssage();
            mssage.message = id + "";
            mssage.themeS = new String[]{"test_234"};
            mssage.messageId = mqHelper.getMsgId(this, 0);
            o.e(tag, "sedmessage " + mssage.toString());
            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(View view) {
        sedmessage();
    }

    public void reconnect(View view) {
        if (mqHelper == null)
            return;
        try {
            mqHelper.reConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(View view) {
        if (mqHelper != null) {
            try {
                mqHelper.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void create(View view) {
        release(null);
        mq();
    }

    public void release(View view) {
        if (mqHelper != null)
            mqHelper.destoryMq();
             mqHelper = null;
    }
}
