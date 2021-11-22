package com.web.crawling;


import com.web.crawling.vo.MovieVO;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class WebCrawling implements Runnable{

    Logger logger = Logger.getLogger(this.getClass());

    private int pageUnit  = 5;

    private  int poolSize = 0;
    private  int startPage = 0;
    private  int lastPage =0;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String nowDate = sdf.format(new Date());

    public  void addPoolSize() throws Exception{
        poolSize++;
        startPage = (pageUnit * (poolSize-1)) + 1;
        lastPage = pageUnit * poolSize;
        logger.info("af " + poolSize + ":" + startPage + ": " +lastPage +  " : " + Thread.currentThread().getName());

        for(int i=startPage;i<=lastPage;i++) {
            Document doc = Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=" + nowDate + "&page=" + i).get();
            Elements link=doc.select("td.title div.tit5 a");
//            for(int j=0;j<link.size();j++)
//            {
//                String url="https://movie.naver.com"+link.get(j).attr("href");
//                logger.info(url);
//
//            }
            logger.info(i);
        }
    }

    //jsoup를 이용한 웹크롤링
    public  List<MovieVO> naverMovieCrawling() throws Exception{
        logger.info("thread running1");

        List<MovieVO> list=new ArrayList<MovieVO>();
        try{

            //int threadCnt = pageUnit;

            logger.info("startPage " + startPage);
            logger.info("lastPage " + lastPage);

            for(int i=startPage;i<=lastPage;i++)
            {
                Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date="+nowDate+"&page="+i).get();

                Elements link=doc.select("td.title div.tit5 a");

                for(int j=0;j<link.size();j++)
                {
                    String url="https://movie.naver.com"+link.get(j).attr("href");
                    Document doc2=Jsoup.connect(url).get();

                    Element title=doc2.selectFirst("h3.h_movie a");


                    int size = doc2.select("p.info_spec span").size();

                    Element genre = null;
                    Element regdate = null;
                    Element grade = null;
                    if(size > 0){
                        genre=doc2.select("p.info_spec span").get(0);

                        if(size > 3){
                            regdate=doc2.select("p.info_spec span").get(3);
                        }

                        if(size > 4){
                            grade=doc2.select("p.info_spec span").get(4);
                        }
                    }

                    Element director=doc2.selectFirst("div.info_spec2 dl.step1 dd a");
                    Element actor=doc2.selectFirst("div.info_spec2 dl.step2 dd");

                    Element poster=doc2.selectFirst("div.poster a img");

                    Element story=doc2.selectFirst("div.story_area p.con_tx");

                    System.out.println((j+1)+"."+title.text());
                    System.out.println("장르:"+Optional.ofNullable(genre).map(g->g.text()).orElse(""));
                    System.out.println("개봉일:"+Optional.ofNullable(regdate).map(g->g.text()).orElse(""));
                    System.out.println("등급:" + Optional.ofNullable(grade).map(g->g.text()).orElse(""));

                    //System.out.println("개봉일:" + Optional.ofNullable(regdate.text()).orElse(""));
                    //System.out.println("등급:"+grade.text().substring(4,grade.text().indexOf("가")+1));

                    System.out.println("감독:"+(director != null?director.text():""));
                    System.out.println("출연:"+(actor != null?actor.text():""));
                    System.out.println(poster.attr("src"));
                    System.out.println(story.text());
                    System.out.println("============================================================");
                    MovieVO vo=new MovieVO();
                    vo.setMno((j+1));
                    vo.setTitle(title.text());
                    vo.setPoster(poster.attr("src"));
                    vo.setDirector((director != null?director.text():""));
                    vo.setActor((actor != null?actor.text():""));
                    vo.setGenre( genre != null?genre.text():"");
                    //vo.setGrade(grade.text().substring(4,grade.text().indexOf("가")+1));
                    vo.setGrade(grade != null ? genre.text() : "");
                    vo.setRegdate(regdate !=null ? regdate.text():"");
                    vo.setStory(story.text());

                    list.add(vo);

                }
            }
        }catch(Exception ex)
        {
            ex.printStackTrace();
            logger.error(ex);

        }
        return list;
    }

    @Override
    public void run() {
        //ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) ex;//쓰레드 생성

        try{
            addPoolSize();
            //naverMovieCrawling();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
