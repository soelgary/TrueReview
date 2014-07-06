package com.gsoeller.truereview.crawler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class App 
{
	
    public static void main( String[] args ) throws IOException, URISyntaxException {
    	Injector injector = Guice.createInjector(new CrawlerModule());
    	Crawler crawler = injector.getInstance(Crawler.class);
    	crawler.crawl(new URI("http://www.nytimes.com"));
    }
}
