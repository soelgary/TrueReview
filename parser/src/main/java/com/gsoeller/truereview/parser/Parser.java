package com.gsoeller.truereview.parser;

import com.google.inject.Inject;
import com.gsoeller.truereview.data.Webpage;
import com.gsoeller.truereview.managers.WebpageManager;

public class Parser {

	private WebpageManager webpageManager;
	
	@Inject
	public Parser(WebpageManager webpageManager) {
		this.webpageManager = webpageManager;
	}
	
	public void parse(String url) {
		Webpage webpage = new Webpage();
		webpage.setDomain(url);
		webpageManager.createWebpage(webpage);
	}
}
