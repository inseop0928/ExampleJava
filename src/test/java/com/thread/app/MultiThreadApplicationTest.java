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
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 최대 스레드가 2개인 스레드풀 생성

        for(int i=0; i<10; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    // 스레드 총 개수 및 작업 스레드 이름 출력
                    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
                    int poolSize = threadPoolExecutor.getPoolSize();
                    String threadName = Thread.currentThread().getName();
                    System.out.println("[총 스레드 개수 : " + poolSize + "] 작업 스레드 이름 : " + threadName);

                    // 예외 발생시킴
                    int value = Integer.parseInt("삼");
                }
            };

            //executorService.execute(runnable);
            executorService.submit(runnable);

            Thread.sleep(10);
        }
        executorService.shutdown();
    }

}