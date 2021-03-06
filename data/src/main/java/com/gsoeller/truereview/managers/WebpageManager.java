package com.gsoeller.truereview.managers;

import java.net.UnknownHostException;
import java.util.List;

import org.joda.time.DateTime;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.gsoeller.truereview.data.Webpage;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class WebpageManager {
	
	private JacksonDBCollection<Webpage, String> collection;
	
	@Inject
	public WebpageManager() throws UnknownHostException, MongoException {
		Mongo mongo = new Mongo("127.0.0.1", 27017);
		DB db = mongo.getDB("TrueReview");		
        collection = JacksonDBCollection.wrap(db.getCollection("webpages"), Webpage.class, String.class);
	}
	
	public List<Webpage> getAll() {
		DBCursor<Webpage> dbCursor = collection.find();
        return Lists.newArrayList(dbCursor.iterator());
	}
	
	
	public Webpage createWebpage(Webpage webpage) {
		DateTime dt = DateTime.now();
		DateTime today = new DateTime(dt.getYear(), dt.getMonthOfYear(), dt.getDayOfMonth(), 0, 0);
		webpage.setCreatedAt(today);
		collection.insert(webpage);
		return webpage;
	}
}
