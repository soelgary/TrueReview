package com.gsoeller.truereview.data;

import java.util.List;

import net.vz.mongodb.jackson.ObjectId;

import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;


public class Webpage {
	
	private String domain;
	private DateTime createdAt;
	private List<String> comments;
	private String id;
	
	@ObjectId
	@JsonProperty("_id")
	public String getId() {
		return id;
	}
	
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}
}
