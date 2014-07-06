package com.gsoeller.truereview.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.gsoeller.truereview.data.Website;

public class WebsiteMapper implements ResultSetMapper<Website> {

	public Website map(int index, ResultSet r, StatementContext ctx)
			throws SQLException {
		return new Website(r.getString("domain"));
	}
}
