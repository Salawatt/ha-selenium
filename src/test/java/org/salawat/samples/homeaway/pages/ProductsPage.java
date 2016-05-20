package org.salawat.samples.homeaway.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductsPage extends AbstractPage {

	private static final String BUY = "Buy";
	private static final String FANCY_POP_UP_CHECK_OUT = "go_to_checkout";
	private static final String FANCY_POP_UP_CONTINUE = "continue_shopping";
	private static final String PRODUCT_ID="product_id";
	private static final String XPATH_PRODUCT_ID_PREFIX= ".//*/a[text()=\'";
	private static final String XPATH_PRODUCT_ID_SUFFIX= "']/ancestor-or-self::h2[1]/following-sibling::form/child::input[@name='product_id']";
	private static final String XPATH_COST_FROM_TITLE_PREFIX=".//*/a[text()='";;
	private static final String XPATH_COST_FROM_TITLE_SUFFIX="']/ancestor-or-self::h2[1]/following-sibling::form/child::div/child::p/span[starts-with(@class,\"currentprice\")]";
	private static final String QUICK_BUY_CLASS="wpsc_buy_button";
	
	private String buildProductIdFromProductTitleXPath(String productTitle) {
		StringBuilder sb=new StringBuilder();
		sb.append(XPATH_PRODUCT_ID_PREFIX).append(productTitle).append(XPATH_PRODUCT_ID_SUFFIX);
		return sb.toString();
	}
	
	private String buildPriceFromProductTitleXPath(String productTitle) {
		StringBuilder sb=new StringBuilder();
		sb.append(XPATH_COST_FROM_TITLE_PREFIX).append(productTitle).append(XPATH_COST_FROM_TITLE_SUFFIX);
		return sb.toString();
	}
	
	
	public ProductsPage() {
		super();
	}

	public void clickProduct(String productTitle) {
		WebElement element=findElementbyLinkText(productTitle);
		Actions action=new Actions(selenium);
		action.moveToElement(element).moveByOffset(0, -200);
		action.perform();
		element.click();
	}
	
	public String getProductIdForProductWithTitle(String productTitle) {
		WebElement result=null;
		String xPath=buildProductIdFromProductTitleXPath(productTitle);

		result=selenium.findElement(By.xpath(xPath));
		String value=null;
		value=result.getAttribute("value");
		return value;
	}

	
	public CheckoutPage clickBuyButtonAndGoToCheckout() {
		CheckoutPage checckout = null;
		findElementByName(BUY).click();
		horribleWait();
		WebElement fancyPopup = null;
		
		fancyPopup = selenium.findElement(By.className(FANCY_POP_UP_CHECK_OUT));
		fancyPopup.click();
		horribleWait();
		
		return new CheckoutPage();
	}

	public void clickBuyButtonAndContinueShopping() {
		findElementByName(BUY).click();
		Actions actions = new Actions(selenium);
		WebElement fancyPopup = null;
		fancyPopup = selenium.findElement(By.className(FANCY_POP_UP_CONTINUE));
		actions.moveToElement(fancyPopup);
		actions.perform();
	}
	
	public String getPriceForItemWithTitle(String productTitle) {
		WebElement result=null;
		String xPath=buildPriceFromProductTitleXPath(productTitle);

		result=selenium.findElement(By.xpath(xPath));
		String value=null;
		value=result.getText();
		return value;
	}
	/**
	 * Pay attention to your indicies.  There's no safety here.  
	 * @param index
	 */

	public void doQuickBuy(int index) {
		List<WebElement> elements=null;
		Actions actions = new Actions(selenium);
		elements=findElementsByClass(QUICK_BUY_CLASS);
		actions.moveToElement(elements.get(index));
		actions.perform();
		elements.get(index).click();
		WebElement fancyPopup = null;
		horribleWait();
		fancyPopup = selenium.findElement(By.className(FANCY_POP_UP_CONTINUE));
		actions.moveToElement(fancyPopup);
		actions.perform();
		fancyPopup.click();
	}

}
