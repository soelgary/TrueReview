package com.gsoeller.truereview.data;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.gsoeller.truereview.managers.WebpageManager;

public class DataModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(WebpageManager.class).in(Singleton.class);
	}

}
