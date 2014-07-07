package com.gsoeller.truereview.resource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gsoeller.truereview.crawler.Crawler;

@Path("/crawler")
@Produces(MediaType.APPLICATION_JSON)
public class CrawlerResource {

	private Crawler crawler;
	
	public CrawlerResource(Crawler crawler) {
		this.crawler = crawler;
	}
	
	@GET
	public boolean crawl() throws FailingHttpStatusCodeException, IOException, URISyntaxException {
		crawler.crawl(new URI("http://www.usatoday.com/story/sports/nba/heat/2014/07/06/lebron-james-agent-meet-with-pat-riley-this-week/12278619/"));
		return true;
	}
}
