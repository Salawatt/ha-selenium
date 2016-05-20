package org.salawat.samples.homeaway.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.salawat.samples.homeaway.enums.Actors;

public class BeforeAfterSuiteClass {

	public static final String SELENIUM_PROPS_PATH="./src/test/resources/selenium.properties";
	public static final String ACTOR_PROPS_PATH="./src/test/resources/actors.properties";
	private static Properties sysProps = new Properties();
	private static Properties actorProps = new Properties();
	
	public static void loadProperties() {

		File seleniumProps = new File(SELENIUM_PROPS_PATH);
		File actorFile = new File(ACTOR_PROPS_PATH);
		InputStream is = null;
		try {
			is = new FileInputStream(seleniumProps);
			sysProps.load(is);
			is = new FileInputStream(actorFile);
			actorProps.load(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verifySystemProps();
	}

	static void verifySystemProps() {
		String seleniumBaseUrl = sysProps.getProperty("selenium.base.url");
		System.out.println("SeleniumBaseUrl is " + seleniumBaseUrl);
		String jschmoeCreds = actorProps.getProperty("actors.jschmoe.username")+" "+actorProps.getProperty("actors.jschmoe.password");
		System.out.println(jschmoeCreds);
	}

	public static Properties getSysProperties() {
		return sysProps;
	}
	
	public static Properties getActorProperties() {
		return actorProps;
	}
	
}
