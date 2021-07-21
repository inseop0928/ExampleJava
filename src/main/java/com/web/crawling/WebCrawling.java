package com.web.crawling;


import com.web.crawling.vo.MovieVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebCrawling {

    //jsoup를 이용한 웹크롤링
    public List<MovieVO> naverMovieCrawling(){

        List<MovieVO> list=new ArrayList<MovieVO>();
        try{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String nowDate = sdf.format(new Date());

            for(int i=1;i<=40;i++)
            {
                Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date="+nowDate+"&page="+i).get();

                Elements link=doc.select("td.title div.tit5 a");

                for(int j=0;j<link.size();j++)
                {
                    String url="https://movie.naver.com"+link.get(j).attr("href");
                    Document doc2=Jsoup.connect(url).get();

                    Element title=doc2.selectFirst("h3.h_movie a");

                    Element genre=doc2.select("p.info_spec span").get(0);
                    Element regdate=doc2.select("p.info_spec span").get(3);
                    Element grade=doc2.select("p.info_spec span").get(4);
                    Element director=doc2.selectFirst("div.info_spec2 dl.step1 dd a");
                    Element actor=doc2.selectFirst("div.info_spec2 dl.step2 dd");

                    Element poster=doc2.selectFirst("div.poster a img");

                    Element story=doc2.selectFirst("div.story_area p.con_tx");

                    System.out.println((j+1)+"."+title.text());
                    System.out.println("장르:"+genre.text());
                    System.out.println("개봉일:"+regdate.text());
                    System.out.println("등급:"+grade.text().substring(4,grade.text().indexOf("가")+1));
                    System.out.println("감독:"+director.text());
                    System.out.println("출연:"+actor.text());
                    System.out.println(poster.attr("src"));
                    System.out.println(story.text());
                    System.out.println("============================================================");
                    MovieVO vo=new MovieVO();
                    vo.setMno((j+1));
                    vo.setTitle(title.text());
                    vo.setPoster(poster.attr("src"));
                    vo.setDirector(director.text());
                    vo.setActor(actor.text());
                    vo.setGenre(genre.text());
                    vo.setGrade(grade.text().substring(4,grade.text().indexOf("가")+1));
                    vo.setRegdate(regdate.text());
                    vo.setStory(story.text());

                    list.add(vo);

                }
            }
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
