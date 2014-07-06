package com.gsoeller.truereview.service;

import org.skife.jdbi.v2.DBI;

import com.gsoeller.truereview.dao.WebsiteDao;
import com.gsoeller.truereview.resource.WebsiteResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TrueReviewApplication extends Application<TrueReviewConfiguration>{

	public static void main(String[] args) throws Exception {
		new TrueReviewApplication().run(args);
	}
	
	@Override
	public void initialize(Bootstrap<TrueReviewConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<TrueReviewConfiguration>() {
	        public DataSourceFactory getDataSourceFactory(TrueReviewConfiguration configuration) {
	                return configuration.getDataSourceFactory();
	            }
	    });
		
	}

	@Override
	public void run(TrueReviewConfiguration configuration, Environment environment)
			throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
		final WebsiteDao dao = jdbi.onDemand(WebsiteDao.class);
		environment.jersey().register(new WebsiteResource(dao));
		
	}

}
