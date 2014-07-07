package com.gsoeller.truereview.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.gsoeller.truereview.parser.Parser;

public class Crawler {

	private PageFetcher fetcher;
	private Parser parser;
	private final int maxDepth = 1;
	private Set<String> links = Sets.newHashSet();
	private Set<String> crawled = Sets.newHashSet();

	private static Logger logger = LogManager.getLogger("Crawler");

	@Inject
	public Crawler(PageFetcher fetcher, Parser parser) {
		this.fetcher = fetcher;
		this.parser = parser;
		System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
		//LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
	    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	}

	public void crawl(URI uri) throws IOException,
			FailingHttpStatusCodeException, URISyntaxException {
		crawl(uri, 0, Sets.<String> newHashSet());
	}

	public void crawl(URI uri, int currentDepth, Set<String> crawled)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, URISyntaxException {
		String currentLink = uri.toString();
		links.add(currentLink);
		while (!links.isEmpty()) {
			if (currentDepth <= maxDepth) {
				logger.info(String.format("Crawling %s adn at depth of %s",
						currentLink, currentDepth));
				if (!crawled.contains(currentLink)) {
					HtmlPage page = fetcher.fetch(currentLink);
					Document doc = new Document(currentLink);
					doc.html(page.asXml());
					Elements tags = doc.select("a[href]");
					List<Element> elements = Lists.newArrayList(tags);
					crawled.add(currentLink);
					links.addAll(getValidLinks(elements, uri));
					parser.parse(currentLink);
					return;
				}
				currentLink = links.iterator().next();
				links.remove(currentLink);
			}
		}
	}

	private List<String> getValidLinks(List<Element> elements, URI uri)
			throws URISyntaxException {
		List<String> acc = Lists.newArrayList();
		for (Element element : elements) {
			Optional<String> host = Optional.fromNullable(new URI(element
					.attr("abs:href")).getHost());
			String host1 = uri.getHost();
			if (host.isPresent() && host.get().equals(host1)
					&& !element.attr("abs:href").contains("#")) {
				acc.add(element.attr("abs:href"));
			}
		}
		return acc;
	}
}
