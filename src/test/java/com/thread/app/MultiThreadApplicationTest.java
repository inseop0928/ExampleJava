package com.thread.app;


import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.*;

public class MultiThreadApplicationTest {

    Logger logger = Logger.getLogger(this.getClass());


    @Test
    public void test1(){

        int threadPool = Runtime.getRuntime().availableProcessors();

        logger.info("cnt " + threadPool);

    }
    @Test
    public void Test1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // �ִ� �����尡 2���� ������Ǯ ����

        for(int i=0; i<10; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // ������ �� ���� �� �۾� ������ �̸� ���
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[�� ������ ���� : " + poolSize + "] �۾� ������ �̸� : " + threadName);

                    // ���� �߻���Ŵ
                    int value = Integer.parseInt("��");
                }
            };

            //executorService.execute(runnable);
            executorService.submit(runnable);

            Thread.sleep(10);
        }
        executorService.shutdown();
    }

}