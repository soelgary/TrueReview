package com.gsoeller.truereview.service;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;


public class TrueReviewConfiguration extends Configuration {

	@JsonProperty
    @NotEmpty
    public String mongohost = "127.0.0.1";
 
    @JsonProperty
    public int mongoport = 27017;
 
    @JsonProperty
    @NotEmpty
    public String mongodb = "TrueReview";
}
