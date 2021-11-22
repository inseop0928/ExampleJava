package com.thread.app;


import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class Testsub implements Runnable {


    Logger logger = Logger.getLogger(this.getClass());

    ArrayList<String> pList;

    public Testsub(ArrayList<String> arrayList) {
        pList = arrayList; //전달받은 파라미터를 할당
    }

    @Override
    public void run() {
        ArrayList dataArray = new ArrayList();
        for(int i = 0; i < pList.size(); i++){
            //개별 비즈니스 로직을 탑재
            String[] strArr = pList.get(i).split(",");

            HashMap<String, Object> tmpMap = new HashMap<>();

            for(int j = 0;j<strArr.length;j++){
                tmpMap.put(Integer.toString(j), strArr[j]);
            }

            dataArray.add(tmpMap);
        }
        //DB적재를 위한 코드 작성해서 사용
        System.out.println("work complete dataCnt : " + dataArray.size());
    }
}
