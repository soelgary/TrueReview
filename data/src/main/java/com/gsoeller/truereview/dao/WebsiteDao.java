package com.gsoeller.truereview.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.gsoeller.truereview.data.Website;
import com.gsoeller.truereview.mappers.WebsiteMapper;

@RegisterMapper(WebsiteMapper.class)
public interface WebsiteDao {
	
	@SqlUpdate("INSERT INTO Website (domain) values (:domain)")
	public void insert(@Bind("domain")String domain);
	
	@SqlQuery("SELECT domain FROM Website")
	public List<Website> getAll();
}