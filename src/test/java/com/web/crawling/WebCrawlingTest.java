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
    static int maxThreadCnt = Runtime.getRuntime().availableProcessors(); //�ִ� ������ ��
    static boolean threadState = true; //������ �׷찳������

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

        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadCnt); //������Ǯ ����
        try {

            Runnable runnable = new WebCrawling();

            for(int i =0;i<maxThreadCnt ;i++){

                //Thread t = new Thread(runnable);
                threadManage(runnable);
                //executorService.execute(runnable);
                //Thread.sleep(10);

                logger.info("������ ����" +(i+1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //executorService.execute(t);
        //webCrawling.run();
        //webCrawling.naverMovieCrawling();
    }

    //������ ������ ���� ������ �����Լ�
    private static void threadManage(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        System.out.println(t.getName());
        //System.out.println(Thread.currentThread().getName());
        //Ȱ������ �������� ������ �ִ� ������ �������� ���� ��� EXECUTOR���
        if(t.activeCount() < maxThreadCnt && threadState){

            System.out.println("Active Thread count is : "+t.activeCount() + " : " + Thread.currentThread().getName());
            ex.execute(t);
        }else{
            //�ִ� ������ ������ ���� �� executor���� �۾��Ϸ� �� ������
            if(ex.isTerminated()){
                System.out.println("Thread group Shutdown x");
                //Ȱ�� �����尡 ��� ����Ǿ��� ��� �� Executor �Ҵ� �� ����
                threadState = true;
                ex = Executors.newCachedThreadPool(new ThreadFactory(){
                    @Override
                    public Thread newThread(Runnable r){
                        return new Thread(r);
                    }
                });
                ex.execute(t);
            }else{
                if(threadState){  //������ ���� ����� ȣ����� ���� ��츸 ����
                    threadState = false;
                    System.out.println("Thread group Shutdown" +  " : " + Thread.currentThread().getName());
                    ex.shutdown();
                }
                t.run(); //������ ���� ����� �Ϸ�� ������ ���� Ŭ������ ��������
            }
        }
    }

}