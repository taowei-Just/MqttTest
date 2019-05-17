package com.tao.os.handler.message;

/**
 * Message
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class Message {
    private Runnable mRunnable;
    long deleyTime  = 0;
    long createTime = 0;
    private int arg;

    public Message(Runnable runnable) {
        mRunnable = runnable;
    }

    public Message(int arg) {
        this.arg = arg;
    }

    public Message(Runnable runnable, int arg) {
        mRunnable = runnable;
        this.arg = arg;
    }

    public Message() {
    }

    public void setRunnable(Runnable runnable) {
        mRunnable = runnable;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }

    public Runnable getRunnable() {
        return mRunnable;
    }



    public long getDeleyTime() {
        return deleyTime;
    }

    public void setDeleyTime(long deleyTime) {
        this.deleyTime = deleyTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mRunnable=" + mRunnable +
                ", deleyTime=" + deleyTime +
                ", createTime=" + createTime +
                ", arg=" + arg +
                '}';
    }
}
