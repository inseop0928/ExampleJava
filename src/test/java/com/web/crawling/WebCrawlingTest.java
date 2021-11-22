package com.web.crawling;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static org.junit.Assert.*;

public class WebCrawlingTest {

    Logger logger = Logger.getLogger(this.getClass());
    static int maxThreadCnt = Runtime.getRuntime().availableProcessors(); //최대 쓰레드 수
    static boolean threadState = true; //쓰레드 그룹개수제한

    private static ExecutorService ex = Executors.newCachedThreadPool(new ThreadFactory(){
        @Override
        public Thread newThread(Runnable r){
            return new Thread(r);
        }
    });

    WebCrawling webCrawling;

    @Before
    public void beforeTest(){
        if(webCrawling == null){
            webCrawling = new WebCrawling();
        }
    }

    @Test
    public void naverMovieCrawling(){

        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadCnt); //스레드풀 생성
        try {

            Runnable runnable = new WebCrawling();

            for(int i =0;i<maxThreadCnt ;i++){

                //Thread t = new Thread(runnable);
                threadManage(runnable);
                //executorService.execute(runnable);
                //Thread.sleep(10);

                logger.info("스레드 실행" +(i+1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //executorService.execute(t);
        //webCrawling.run();
        //webCrawling.naverMovieCrawling();
    }

    //쓰레드 관리를 위한 쓰레드 관리함수
    private static void threadManage(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        System.out.println(t.getName());
        //System.out.println(Thread.currentThread().getName());
        //활성중인 쓰레드의 개수가 최대 쓰레드 개수보다 작을 경우 EXECUTOR사용
        if(t.activeCount() < maxThreadCnt && threadState){

            System.out.println("Active Thread count is : "+t.activeCount() + " : " + Thread.currentThread().getName());
            ex.execute(t);
        }else{
            //최대 쓰레드 개수에 도달 시 executor에게 작업완료 후 종료명령
            if(ex.isTerminated()){
                System.out.println("Thread group Shutdown x");
                //활성 쓰레드가 모두 종료되었을 경우 새 Executor 할당 후 실행
                threadState = true;
                ex = Executors.newCachedThreadPool(new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r){
                        return new Thread(r);
                    }
                });
                ex.execute(t);
            }else{
                if(threadState){  //쓰레드 종료 명령이 호출되지 않은 경우만 수행
                    threadState = false;
                    System.out.println("Thread group Shutdown" +  " : " + Thread.currentThread().getName());
                    ex.shutdown();
                }
                t.run(); //쓰레드 종료 명령이 완료될 때까진 메인 클래스가 직접수행
            }
        }
    }

}