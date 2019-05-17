package com.tao.os.handler;


import com.tao.os.handler.message.Handler;
import com.tao.os.handler.message.Looper;

/**
 * MessageMain
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class MessageMain {

    private static Handler handler;

    public static void main(String[] args) {
//        t1();
        t2();
    }

    private static void t2() {
        System.err.println("0 "+Thread.currentThread().toString());

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler(Looper.myLooper());
                System.err.println("1 "+Thread.currentThread().toString());
                Looper.loop();
            }
        }).start();

        System.err.println("++++");

       while (true) {

           if (handler == null ||! handler.isPerpare())
               continue;
               System.err.println(" 2  post time " + Thread.currentThread().toString());

               handler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   System.err.println(" 2 " + Thread.currentThread());
               }
           } , 5*1000);

           try {
               Thread.sleep(3*1000);
           } catch ( Exception e) {
               e.printStackTrace();
           }

       }



    }

    private static void t1() {
        Handler handler = new Handler();
        System.out.println("1、创建Runnable的线程id = " + Thread.currentThread().getId());
        handler.post(new Runnable() {
            @Override
            public void run() {
                System.out.println("1、执行Runnable的线程id = " + Thread.currentThread().getId());
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("2、创建Runnable的线程id = " + Thread.currentThread().getId());
                Handler main = new Handler(Looper.getMainLooper());
                main.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("2、执行Runnable的线程id = " + Thread.currentThread().getId());
                        System.exit(0);
                    }
                });
            }
        });
        Looper.loop();
    }
}
