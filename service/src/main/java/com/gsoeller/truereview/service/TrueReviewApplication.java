package com.gsoeller.truereview.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gsoeller.truereview.crawler.Crawler;
import com.gsoeller.truereview.crawler.CrawlerModule;
import com.gsoeller.truereview.data.DataModule;
import com.gsoeller.truereview.healthchecks.MongoHealthCheck;
import com.gsoeller.truereview.managers.WebpageManager;
import com.gsoeller.truereview.mongo.MongoManaged;
import com.gsoeller.truereview.parser.ParserModule;
import com.gsoeller.truereview.resource.CrawlerResource;
import com.gsoeller.truereview.resource.WebpageResource;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class TrueReviewApplication extends Service<TrueReviewConfiguration>{

	public static void main(String[] args) throws Exception {
		new TrueReviewApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<TrueReviewConfiguration> bootstrap) {}

	@Override
	public void run(TrueReviewConfiguration configuration, Environment environment)
			throws Exception {		
		Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		
		Injector injector = Guice.createInjector(new DataModule(), new CrawlerModule(), new ParserModule());
		
		environment.manage(mongoManaged);
		environment.addResource(new WebpageResource(injector.getInstance(WebpageManager.class)));
		environment.addResource(new CrawlerResource(injector.getInstance(Crawler.class)));
		environment.addHealthCheck(new MongoHealthCheck(mongo));
		
		
	}
}
