package com.gsoeller.truereview.crawler;

import java.io.IOException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import com.google.inject.Inject;

public class Crawler {

	PageFetcher fetcher;
	
	@Inject
	public Crawler(PageFetcher fetcher) {
		this.fetcher = fetcher;
	}
	
	public void crawl() throws IOException {
		String url = "http://127.0.0.1:8000";
		HtmlPage page = fetcher.fetch(url);
		System.out.println(page.asXml());
	}
}
