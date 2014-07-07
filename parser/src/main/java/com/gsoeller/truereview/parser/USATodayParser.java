package com.gsoeller.truereview.parser;

import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.collect.Lists;

public class USATodayParser {

	public List<String> parseComments(Document doc) {
		Elements elements = doc.select("div.postText");
		List<String> comments = Lists.newArrayList();
		for(Element element: Lists.newArrayList(elements.iterator())) {
			comments.add(element.ownText());
		}
		return comments;
	}
}
