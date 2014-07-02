package com.gsoeller.truereview.crawler;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App 
{
	
    public static void main( String[] args ) throws IOException {
    	Injector injector = Guice.createInjector(new CrawlerModule());
    	Crawler crawler = injector.getInstance(Crawler.class);
    	crawler.crawl();
    }
}
