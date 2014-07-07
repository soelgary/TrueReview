package com.gsoeller.truereview.dao;

import java.util.List;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.gsoeller.truereview.data.Webpage;
import com.mongodb.DB;

public class WebpageDao {
private JacksonDBCollection<Webpage, String> collection;
	
	@Inject
	public WebpageDao(DB db) {
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
