package com.gsoeller.truereview.crawler;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.inject.Inject;

public class PageFetcher {
	
	WebClient client;
	
	@Inject
	public PageFetcher(WebClient client) {
		this.client = client;
	}
	
	public HtmlPage fetch(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		return client.getPage(url);
	}
}
