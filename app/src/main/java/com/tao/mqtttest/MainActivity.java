package com.tao.mqtttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.tao.mqlibrary.mqsun.Message.MqMssage;
import com.tao.mqlibrary.mqsun.iml.IHandlerCreate;
import com.tao.mqlibrary.mqsun.iml.MqStatueCall;
import com.tao.mqlibrary.mqsun.mq.MqHelper;
import com.tao.mqlibrary.utils.o;

public class MainActivity extends AppCompatActivity {

    String tag =getClass().getSimpleName() ;
    private MqHelper mqHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mq();
    }

    private void mq() {
        try {
            mqHelper = new MqHelper.Build(getApplicationContext())
                    .config("tcp://","tobacco.sun-hyt.com","10000","1234","admin","123456")
                    .sub(new String[]{"123"})
                    .setAutoReconnect(true)
                    .setAutoReconnectTime(5)
                    .setConnectTimeout(20)
                    .setKeepAliveTime(20)
                    .setPrepareCall(new IHandlerCreate() {
                        @Override
                        public void OnHandlerLooper() {
                            o.e(tag ,"OnHandlerLooper") ;
                        }
                    })
                    .setMqStatueCall(new MqStatueCall() {
                        @Override
                        public void OnConnectStatueChange(boolean conn) {
                            o.e(tag ,"OnConnectStatueChange " +conn) ;
                            if (conn ){
                                sedmessage();
                            }
                        }

                        @Override
                        public void sendDataStatue(boolean send, MqMssage message) {
                            o.e(tag ,"sendDataStatue " +message.toString()) ;

                        }

                        @Override
                        public void OnMessage(MqMssage mqMessage) {
                            o.e(tag ,"OnMessage " +mqMessage.toString()) ;

                        }
                    })  .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sedmessage() {
        if (mqHelper==null||!mqHelper.isConnect())
            return;

        try {
            MqMssage mssage = new MqMssage();
            mssage.message="111111111" ;
            mssage.themeS=new String[]{"234"};
            mssage.messageId=mqHelper.getMsgId(this,0);

            o.e(tag ,"sedmessage " +mssage.toString()) ;

            mqHelper.send(mssage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void send(View view) {
        sedmessage();
    }

    public void reconnect(View view) {
        if (mqHelper==null )
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    o.e(tag, "reconnect:" + Thread.currentThread().toString());
                    try {
                        mqHelper.reConnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    try {
                        Thread.sleep(1 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
