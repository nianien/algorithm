package io.lining;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @author : liyifei
 * @created : 2026/1/13, Tuesday
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class ProducerConsumer {

    private BlockingQueue<Integer> buffer;

    private ExecutorService executors;

    public ProducerConsumer(int bufferSize, int producers) {
        this.buffer = new ArrayBlockingQueue<>(bufferSize);
        this.executors = Executors.newFixedThreadPool(producers);
    }

    public void produce(int data) {
        try {
            this.buffer.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void consume(Function<Integer, Boolean> task) {
        try {
            int data = this.buffer.take();
            executors.submit(() -> task.apply(data));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer(20, 5);
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("producing data:" + i);
                pc.produce(i);
            }
        }).start();
        new Thread(() -> pc.consume((Integer data) -> {
            System.out.println("consume data:" + data);
            return true;
        })).start();

    }

}
