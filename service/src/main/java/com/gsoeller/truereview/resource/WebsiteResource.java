package com.gsoeller.truereview.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gsoeller.truereview.dao.WebsiteDao;
import com.gsoeller.truereview.data.Website;


@Path("/website")
@Produces(MediaType.APPLICATION_JSON)
public class WebsiteResource {
	
	private WebsiteDao dao;
	
	public WebsiteResource(WebsiteDao dao) {
		this.dao = dao;
	}
	
	@GET
	public List<Website> testMethod() {
		return dao.getAll();
	}
}
