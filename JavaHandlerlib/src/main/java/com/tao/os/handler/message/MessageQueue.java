package com.tao.os.handler.message;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * MessageQueue
 *
 * @author xl
 * @version V1.0
 * @since 17/03/2017
 */
public class MessageQueue {
    private final Queue<Message> mQueue = new ArrayDeque<>();

    public void post(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.add(message);
        }
    }

    public void removeAll() {
        synchronized (mQueue) {
            mQueue.clear();
        }
    }

    public void remove(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.remove(message);
        }
    }

    public Message next() {
        try {
            while (true) {
                synchronized (mQueue) {
                    if (!mQueue.isEmpty()) {
                        Message poll = mQueue.poll();
                        long l = System.currentTimeMillis() - poll.getCreateTime();
                        if (l >= poll.getDeleyTime())
                            return poll;
                        else
                            mQueue.add(poll);
                    }
                    if (mQueue.size() <= 0)
                        mQueue.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public synchronized void removeCallbacks(Runnable autoConnectRun) {
       if ( mQueue.contains(autoConnectRun))
           mQueue.remove(autoConnectRun);
    }
}
