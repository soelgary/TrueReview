package com.gsoeller.truereview.parser;

import java.util.List;

import org.jsoup.nodes.Document;

import com.google.inject.Inject;
import com.gsoeller.truereview.data.Webpage;
import com.gsoeller.truereview.managers.WebpageManager;

public class Parser {

	private WebpageManager webpageManager;
	
	@Inject
	public Parser(WebpageManager webpageManager) {
		this.webpageManager = webpageManager;
	}
	
	public void parse(String url, Document doc) {
		USATodayParser p = new USATodayParser();
		List<String> comments = p.parseComments(doc);
		System.out.println(comments);
		Webpage webpage = new Webpage();
		webpage.setDomain(url);
		webpageManager.createWebpage(webpage);
	}
}
