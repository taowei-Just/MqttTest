package com.tao.os.handler.message;

/**
 * Handler
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class Handler {
    private Looper mLooper;

    public Handler() {
        Looper.prepareMainThread();
        HandlerThread handlerThread = new HandlerThread();
        handlerThread.start();
        mLooper =handlerThread.getLooper();
    }

    public Handler(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("looper can't be null.  HandlerThread is running?");
        }
        mLooper = looper;
    }
    public  boolean isPerpare(){
        return mLooper.isPerpare();
    }

    public void remove(Message message) {
        mLooper.getMessageQueue().remove(message);
    }

    public void post(Message message) {
        mLooper.getMessageQueue().post(message);
    }

    public void post(Runnable runnable) {
        Message message = new Message();
        message.setRunnable(runnable);
        post(message);
    }

    int tag =0 ;
    public void postDelayed(Runnable runnable, int timeMill) {
        Message message = new Message();
        message.setRunnable(runnable);
        message.setCreateTime(System.currentTimeMillis());
        message.setDeleyTime(timeMill);
        message.setArg(tag++);
        post(message);
    }

    public synchronized void removeCallbacks(Runnable autoConnectRun) {
        mLooper.getMessageQueue().removeCallbacks(autoConnectRun);
    }

    public void removeAllCallbackS() {
        mLooper.getMessageQueue().removeAll();
    }
}
