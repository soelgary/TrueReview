package com.gsoeller.truereview.crawler;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class CrawlerModule extends AbstractModule{

	@Override
	protected void configure() {
		bind(Crawler.class).in(Singleton.class);
	}

}
