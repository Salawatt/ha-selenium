package org.salawat.samples.homeaway.enums;

import org.salawat.samples.homeaway.commons.BeforeAfterSuiteClass;

/**
 * enum to represent actor invariants.
 * 
 * @author salawat
 *
 */
public enum Actors {

	JSCHMOE(BeforeAfterSuiteClass.getActorProperties().getProperty("actors.jschmoe.username"),
			BeforeAfterSuiteClass.getActorProperties().getProperty("actors.jschmoe.password"));

	private String userName;
	private String password;

	private Actors(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUsername() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	void setPassword(String password) {
		this.password = password;
	}
}
