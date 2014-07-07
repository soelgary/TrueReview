package com.gsoeller.truereview.service;

import com.gsoeller.truereview.healthchecks.MongoHealthCheck;
import com.gsoeller.truereview.managers.WebpageManager;
import com.gsoeller.truereview.mongo.MongoManaged;
import com.gsoeller.truereview.resource.WebpageResource;
import com.mongodb.DB;
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
		DB db = mongo.getDB(configuration.mongodb);
		MongoManaged mongoManaged = new MongoManaged(mongo);
		environment.manage(mongoManaged);
		environment.addResource(new WebpageResource(new WebpageManager(db)));
		environment.addHealthCheck(new MongoHealthCheck(mongo));
		
		
	}
}
