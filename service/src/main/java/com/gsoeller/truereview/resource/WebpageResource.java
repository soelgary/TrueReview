package com.gsoeller.truereview.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gsoeller.truereview.data.Webpage;
import com.gsoeller.truereview.managers.WebpageManager;


@Path("/website")
@Produces(MediaType.APPLICATION_JSON)
public class WebpageResource {
	
	private WebpageManager manager;
	
	public WebpageResource(WebpageManager manager) {
        this.manager = manager;
	}
	
	@GET
	public List<Webpage> getAll() {
		return manager.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Webpage createWebpage(Webpage webpage) {
		return manager.createWebpage(webpage);
	}
}
