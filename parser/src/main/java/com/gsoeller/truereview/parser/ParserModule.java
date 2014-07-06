package com.gsoeller.truereview.parser;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ParserModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Parser.class).in(Singleton.class);
	}
}
