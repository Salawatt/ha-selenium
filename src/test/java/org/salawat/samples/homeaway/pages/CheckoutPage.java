package org.salawat.samples.homeaway.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends AbstractPage {
	
	private static final String CALCULATE_NAME="wpsc_submit_zipcode";
	private static final String CONTINUE_CLASS="step2";
	private static final String COUNTRY_DROP_DOWN="current_country";
	
	private static final String XPATH_UPDATE_QTY_BUTTON_FOR_ITEM_ON_ROW_PREFIX=".//*[@class='adjustform remove']{";
	private static final String XPATH_UPDATE_QTY_BUTTON_FOR_ITEM_ON_ROW_SUFFIX="}/child::input[@value=\"Remove\"]";
	
	private static final String XPATH_REMOVE_BUTTON_FOR_ITEM_ON_ROW_PREFIX=".//*[@class='adjustform remove'][";
	private static final String XPATH_REMOVE_BUTTON_FOR_ITEM_ON_ROW_SUFFIX="]/child::input[@value=\"Remove\"]";
	
	private static final String ERROR_STRING_XPATH="//div/text()[contains(.,'Oops')]";
	/**
	 * There are 3 nodes on this page that match this XPath.
	 * The text value of the first is $ prefixed shipping charge
	 * The text value of the second SHOULD be match the catalog price.  
	 * The text value of the third is the tax.  
	 * 
	 */
	private static final String XPATH_FOR_SUBTOTALS_PREFIX=".//*[@class='pricedisplay checkout-shipping']/child::span[@class='pricedisplay']";
	
	private static final String XPATH_FOR_TOTAL=".//*[@id='checkout_total']/span";
	
	/**
	 * Use row number of column.  Topmost row=1.
	 * I accept no responsibility for not remembering that XPath is indexed from 1.
	 * 
	 * @param row
	 * @return
	 */
	
	private String buildXPathToUpdateQty(int row) {
		StringBuilder sb=new StringBuilder();
		sb.append(XPATH_UPDATE_QTY_BUTTON_FOR_ITEM_ON_ROW_PREFIX).append(String.valueOf(row)).append(XPATH_UPDATE_QTY_BUTTON_FOR_ITEM_ON_ROW_SUFFIX);
		return sb.toString();
	}
	
	/**
	 * Use row number of item to be removed.  Topmost row=1.
	 * I accept no responsibility for not remembering that XPath is indexed from 1.
	 * 
	 * @param row
	 * @return
	 */
	private String buildXPathToRemove(int row) {
		StringBuilder sb=new StringBuilder();
		sb.append(XPATH_REMOVE_BUTTON_FOR_ITEM_ON_ROW_PREFIX).append(String.valueOf(row)).append(XPATH_REMOVE_BUTTON_FOR_ITEM_ON_ROW_SUFFIX);
		return sb.toString();
	}
	
	private String buildXPathToSubTotals(int index) {
		StringBuilder sb=new StringBuilder();
		sb.append(XPATH_FOR_SUBTOTALS_PREFIX);
		return sb.toString();
	}
	
	public CheckoutPage() {
		super();
	}
	
	/**
	 * hardcoded to one for brevity.
	 * Not a normal habit, but it suffices for here.
	 * 
	 */
	public void removeTopProductFromCart() {
		WebElement result=null;
		String xpath=buildXPathToRemove(1);
		result = selenium.findElement(By.xpath(xpath));
		result.click();
	}
	
	public void clickContinue() {
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.className(CONTINUE_CLASS));
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}
	
	public void selectCountry(String country) {
		horribleWait();
		Select countryDropDown = new Select(findElementById(COUNTRY_DROP_DOWN));
		countryDropDown.selectByVisibleText(country);
	}
	
	public void clickCalculate() {
		Actions actions = new Actions(selenium);
		WebElement element = selenium.findElement(By.name(CALCULATE_NAME));
		actions.moveToElement(element);
		actions.perform();
		element.click();
	}
	
	public String getShipping() {
		return selenium.findElements(By.xpath(buildXPathToSubTotals(1))).get(0).getText();	
	}
	
	public String getSubTotal() {
		return selenium.findElements(By.xpath(buildXPathToSubTotals(2))).get(1).getText();
	}
	
	public String getTax() {
		return selenium.findElements(By.xpath(buildXPathToSubTotals(3))).get(2).getText();
	}
	
	public String getTotal() {
		return selenium.findElement(By.xpath(XPATH_FOR_TOTAL)).getText();
	}
	
	public boolean isErrorMessagePresent(){
		WebElement element=selenium.findElement(By.xpath(ERROR_STRING_XPATH));
		return element==null;
	}
}
