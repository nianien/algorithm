package com.lining;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : liyifei
 * @created : 2026/1/13, Tuesday
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class ProducerConsumerV2 {

    private final Deque<Integer> q = new ArrayDeque<>();

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();
    private ExecutorService es;
    private final int capacity;
    private final int batchSize;
    private int totalTask;

    ProducerConsumerV2(int capacity, int batchSize, int producers) {
        this.capacity = capacity;
        this.batchSize = batchSize;
        this.es = Executors.newFixedThreadPool(producers);
    }


    private void doProduce(int item) {
        lock.lock();
        try {
            while (q.size() == capacity) {
                notFull.await();
            }
            System.out.println("producing data:" + item);
            q.addLast(item);
            notEmpty.signal(); // wake one consumer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }


    public boolean consume() {
        lock.lock();
        try {
            while (q.isEmpty() && totalTask != 0) {
                notEmpty.await();
            }
            if (totalTask == 0) {
                return false;
            }
            int n = Math.min(batchSize, q.size());
            List<Integer> batch = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                batch.add(q.removeFirst());
            }
            totalTask -= n;
            // freed space for producers
            notFull.signalAll();
            es.submit(() -> doConsume(batch));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
        return true;
    }

    private boolean doConsume(List<Integer> items) {
        System.out.println("consume data:" + items);
        return true;
    }


    public void start(List<Integer> items) throws InterruptedException {
        this.totalTask = items.size();
        for (Integer item : items) {
            es.execute(() -> doProduce(item));
        }
        while (consume()) ;
    }

    public void stop() throws InterruptedException {
        if (es != null) {
            es.shutdown();
            es.wait(TimeUnit.SECONDS.toMillis(10));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerV2 pc = new ProducerConsumerV2(20, 5, 5);
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            items.add(i);
        }
        pc.start(items);
        pc.stop();

    }

}
