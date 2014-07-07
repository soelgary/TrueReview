package com.gsoeller.truereview.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.LogManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.inject.Inject;

public class PageFetcher {
	
	WebClient client;
	WebDriver driver;
	
	@Inject
	public PageFetcher(WebClient client) {
		this.client = client;
		this.client.getOptions().setCssEnabled(false);
		this.client.getOptions().setThrowExceptionOnScriptError(false);
		DesiredCapabilities dCaps = new DesiredCapabilities();
		dCaps.setJavascriptEnabled(true);
		dCaps.setCapability("takesScreenshot", false);
		driver = new PhantomJSDriver(dCaps);
		
	}
	
	public String fetch(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		driver.get(url);
		return driver.getPageSource();
		//return client.getPage(url);
	}
}
