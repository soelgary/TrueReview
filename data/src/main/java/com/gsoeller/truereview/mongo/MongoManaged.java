package com.gsoeller.truereview.mongo;

import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;
 
public class MongoManaged implements Managed {
 
    private Mongo mongo;
 
    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }
 
    public void start() throws Exception {
    }
 
    public void stop() throws Exception {
        mongo.close();
    }
 
}
