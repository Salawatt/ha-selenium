package org.salawat.samples.homeaway.pages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import junit.framework.Assert;

public class AbstractPage {

	protected static WebDriver selenium;
	protected static final String baseUrl = "http://store.demoqa.com";

	public AbstractPage() {

		File profileDir = new File("./src/test/resources/testAutomation_profile");
		FirefoxProfile ffp = new FirefoxProfile(profileDir);
		
		if(selenium==null){
			System.out.println("Detecting uninitialized driver. Instantiating.");
			selenium = new FirefoxDriver();			
		}
		//driver control has not been established.  Lets get started.
		if(selenium.getCurrentUrl()==null){			
			selenium.get(baseUrl);
		}
	}
	
	protected static WebDriver getDriver() {
		return selenium;
	}
	
	/**
	 * I feel disgusted even having this in here, but unfortunately,
	 * the timing god's still need appeasement time and again.
	 * 
	 */
	protected void horribleWait() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Convenience method, wraps findElement(By.name)
	 * 
	 * @param name
	 * @return
	 */

	public WebElement findElementByName(String name) {
		WebElement result = null;
		try {
			result = selenium.findElement(By.name(name));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convenience method, wraps findElement(By.id)
	 * 
	 * @param id
	 * @return
	 */
	public WebElement findElementById(String id) {
		WebElement result = null;
		try {
			result = selenium.findElement(By.id(id));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convenience method, wraps findElement(By.className)
	 * 
	 * @param className
	 * @return
	 */
	public WebElement findElementByClass(String className) {
		WebElement result = null;
		try {
			result = selenium.findElement(By.className(className));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convenience method, wraps findElement(By.linkText)
	 * 
	 * @param text
	 * @return
	 */
	public WebElement findElementbyLinkText(String text) {
		WebElement result = null;
		try {
			result = selenium.findElement(By.linkText(text));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convenience method, wraps findElement(By.partialLinkText())
	 * 
	 * @param pText
	 * @return WebElement result, null if no Element's matching the criteria
	 *         could be found.
	 */
	public WebElement findElementbyPartialLinkText(String pText) {
		WebElement result = null;
		try {
			result = selenium.findElement(By.partialLinkText(pText));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Convenience method, wraps findElements(By.name)
	 * 
	 * @param name
	 * @return List of all WebElements matching criteria, or an empty List
	 *         if none found
	 */

	public List<WebElement> findElementsByName(String name) {
		List<WebElement> results = null;
		results = selenium.findElements(By.name(name));
		return results;
	}

	/**
	 * Convenience method, wraps findElement(By.id)
	 * 
	 * @param id
	 * @return List of all WebElements matching criteria, or empty List if
	 *         none found
	 */
	public List<WebElement> findElementsById(String id) {
		List<WebElement> results = null;
		results = selenium.findElements(By.id(id));
		return results;
	}

	/**
	 * Convenience method, wraps findElement(By.className)
	 * 
	 * @param className
	 * @return List of all WebElements matching criteria, or an empty List
	 *         if none found
	 */
	public List<WebElement> findElementsByClass(String className) {
		List<WebElement> results = null;
		results = selenium.findElements(By.className(className));
		return results;
	}

	/**
	 * Convenience method, wraps findElements(By.linkText)
	 * 
	 * @param text
	 * @return List of all WebElements matching criteria, or an empty List
	 *         if none found
	 */
	public List<WebElement> findElementsbyLinkText(String text) {
		List<WebElement> results = null;
		results = selenium.findElements(By.linkText(text));
		return results;
	}

	/**
	 * Convenience method, wraps findElement(By.partialLinkText())
	 * 
	 * @param pText
	 * @return WebElement result, null if no Element's matching the criteria
	 *         could be found.
	 */
	public List<WebElement> findElementsbyPartialLinkText(String pText) {
		List<WebElement> results = null;
		results = selenium.findElements(By.partialLinkText(pText));
		return results;
	}

	/**
	 * Pretty much any page object has the potential to interact 
	 * with the navbar somehow.  Not 100% sure it needs to be so widely spread though.
	 * 
	 * @author salawat
	 *
	 */
	public class NavbarPage extends AbstractPage {

		/*
		 * Some convenient but unused tags.
		 * 
		 */
		private static final String HOME_TEXT = "Home";
		private static final String PRODUCT_TEXT = "Product Category";
		private static final String ALL = "All Product";
		private static final String ACCOUNT = "My Account";
		private static final String CART = "Checkout";
		private static final String MY_ACCOUNT = "account_icon";
		private static final String CART_CLASS = "cart_icon";

		public NavbarPage() {
			super();
		}

		public StoreHomePage clickHomeLink() {
			this.findElementByClass(CART_CLASS).click();
			return new StoreHomePage();
		}

		public ProductsPage clickProductCategorySubLink(String text) {
			//These new actions are pretty nifty
			Actions actions = new Actions(selenium);
			WebElement menuHover = selenium.findElement(By.linkText("Product Category"));
			actions.moveToElement(menuHover);
			actions.perform();
			try {
				//Horrid.  Truly Horrid
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebElement subLink = selenium.findElement(By.linkText(text));
			subLink.click();
			return new ProductsPage();
		}
		
		public void clickProductUnfilterLink() {

		}

		public AccountPage clickAccountLink() {
			AccountPage newPage = new AccountPage();
			try {
				findElementByClass(MY_ACCOUNT).click();
			} catch (StaleElementReferenceException e) {
				Assert.fail("Could not click due to stale reference. Dynamic content?");
			}
			return newPage;
		}
		
		public CheckoutPage clickCheckoutLink() {
			Actions actions = new Actions(selenium);
			WebElement element = selenium.findElement(By.className(CART_CLASS));
			actions.moveToElement(element);
			actions.perform();
			findElementByClass(CART_CLASS).click();
			return new CheckoutPage();
		}
	}

	/**
	 * Convenience enum.
	 * 
	 * @author salawat
	 *
	 */
	public enum ProductCategory {
		ACCESSORIES("Accessories"), IMACS("iMacs"), IPADS("iPads"), IPHONES("iPhones"), IPODS("iPods"), MACBOOKS(
				"MacBooks");

		private String linkText;

		ProductCategory(String s) {
			this.linkText = s;
		}

		public String getLinkText() {
			return linkText;
		}
	}

}
