package org.salawat.samples.homeaway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AccountPage extends AbstractPage {

	private static final String DETAILS = "Your Details";
	private static final String FIRST_NAME_ID = "wpsc_checkout_form_2";
	private static final String LOG_OUT_TEXT = "Log out";
	private static final String LOG_IN = "login";
	private static final String LOG_IN_TEXT = "Log in";
	private static final String XPATH_SAVE_PROFILE = ".//*[@value='Save Profile']";
	private static final String WORKAROUND = "wpsc_checkout_form_11";

	private static final String USERNAME_ID = "log";
	private static final String PASSWORD_ID = "pwd";
	// You will not be taken to the login page you're looking for...
	private static final String THERES_NO_PLACE_LIKE_HOME = "← Back to ONLINE STORE";

	public AccountPage() {
		super();
	}

	public void clickYourDetails() {
		horribleWait();
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.linkText(DETAILS));
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}

	public void fillOutFirstName(String name) {
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.id(FIRST_NAME_ID));
		actions.moveToElement(element);
		actions.perform();
		findElementById(FIRST_NAME_ID).sendKeys(name);
	}

	public void clickLogoutLink() {
		WebElement element = findElementbyLinkText(LOG_OUT_TEXT);
		Actions action = new Actions(selenium);
		// Once again, annoying menubar
		action.moveToElement(element).moveByOffset(0, -200);
		action.perform();
		element.click();
	}

	public void clickLoginLink() {
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.linkText(LOG_IN_TEXT));
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}

	public void clickSaveProfile() {
		WebElement element = selenium.findElement(By.xpath(XPATH_SAVE_PROFILE));
		WebElement element2 = selenium.findElement(By.id(WORKAROUND));
		Actions actions = new Actions(selenium);
		// Maybe this will get us past this popupBar?
		actions.moveToElement(element2).moveByOffset(57, -200);

		actions.perform();
		element.click();
	}

	public void enterUserName(String username) {
		findElementById(USERNAME_ID).sendKeys(username);
	}

	public void enterPassword(String password) {
		findElementById(PASSWORD_ID).sendKeys(password);
	}

	public void clickLogInButton() {
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.id(LOG_IN));
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}

	public String getValueOfFirstNameField() {
		String result = findElementById(FIRST_NAME_ID).getAttribute("value");
		return result;
	}

	public StoreHomePage goHome() {
		WebElement element = findElementbyLinkText(THERES_NO_PLACE_LIKE_HOME);
		Actions action = new Actions(selenium);
		action.moveToElement(element).click().perform();
		return new StoreHomePage();
	}

}
