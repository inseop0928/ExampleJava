package com.web.crawling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebCrawlingTest {

    WebCrawling webCrawling;

    @Before
    public void beforeTest(){
        if(webCrawling == null){
            webCrawling = new WebCrawling();
        }
    }

    @Test
    public void naverMovieCrawling(){
        webCrawling.naverMovieCrawling();
    }

}