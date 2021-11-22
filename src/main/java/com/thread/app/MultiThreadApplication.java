package com.thread.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MultiThreadApplication {
    //submit은 fiture라는 데이터로 리턴

   //static int threadPool = Runtime.getRuntime().availableProcessors();
    //static int maxThreadCnt = 4; //최대 쓰레드 수
    static int maxThreadCnt = Runtime.getRuntime().availableProcessors(); //최대 쓰레드 수

    static int inPutRowCnt = 1000; //쓰레드별 입력행수
    static boolean threadState = true; //쓰레드 그룹개수제한
    //쓰레드 그룹을 컨트롤 할 서비스 생성
    //그룹으로 묶어서 효율적으로 관리
    private static ExecutorService ex = Executors.newCachedThreadPool(new ThreadFactory(){
        @Override
        public Thread newThread(Runnable r){
            return new Thread(r);
        }
    });
    public static void main(String[] args){

        //long initSystem.currentTimeMillis();
        String fPath = "C:\\Users\\82105\\Desktop\\fastcampus-spring-batch-example\\2020년_11월_일별_주문_금액.csv";
        try {
            FileReader rd = new FileReader(fPath);
            BufferedReader br = new BufferedReader(rd);
            String readLine;
            int rowcount = 0;
            ArrayList<String> list = new ArrayList<String>();
            while ((readLine= br.readLine()) != null) {
                rowcount++;
                list.add(rowcount+","+readLine);
                if(rowcount%inPutRowCnt == 0){
                    //설정된 파일 행 수 만큼 분할하여 개별 쓰레드에 적재
                    Runnable r = new Testsub((ArrayList<String>) list.clone());
                    threadManage(r);
                    list.clear();
                }
            }
            //미처리 된 데이터에 대해 처리
            if(list.isEmpty()){
                System.out.println("파일 처리 완료 ");
            }else{
                Runnable r = new Testsub((ArrayList<String>) list.clone());
                r.run();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //쓰레드 관리를 위한 쓰레드 관리함수
    private static void threadManage(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);

        //활성중인 쓰레드의 개수가 최대 쓰레드 개수보다 작을 경우 EXECUTOR사용
        if(t.activeCount() < maxThreadCnt && threadState){
            System.out.println("Active Thread count is : "+t.activeCount());
            ex.execute(t);
        }else{
            //최대 쓰레드 개수에 도달 시 executor에게 작업완료 후 종료명령
            if(ex.isTerminated()){
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
                    System.out.println("Thread group Shutdown");
                    ex.shutdown();
                }
                t.run(); //쓰레드 종료 명령이 완료될 때까진 메인 클래스가 직접수행
            }
        }
    }
}
