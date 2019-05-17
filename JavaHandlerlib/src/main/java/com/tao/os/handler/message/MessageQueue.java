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
            for (Message message : mQueue) {
                mQueue.remove(message);
            }
        }
    }

    public void remove(Message message) {
        synchronized (mQueue) {
            mQueue.notify();
            mQueue.remove(message);
        }
    }

    public Message next() {
        while (true) {
            synchronized (mQueue) {
                try {
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public  synchronized void removeCallbacks(Runnable autoConnectRun) {
        for (Message message : mQueue) {
            if (message == null || message.getRunnable() != autoConnectRun)
                continue;
            mQueue.remove(message);
            break;
        }

    }
}
