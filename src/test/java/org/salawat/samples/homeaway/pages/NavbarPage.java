/*package org.salawat.samples.homeaway.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class NavbarPage extends AbstractPage {

	
	private static final String HOME_TEXT="Home";
	private static final String PRODUCT_TEXT="Product Category";
	private static final String ALL="All Product";
	private static final String CART_CLASS="cart_icon";
	private static final String MY_ACCOUNT="account_icon";
	private static final String ACCOUNT="My Account";
	private static final String CART="Checkout";
	
	public NavbarPage() {
		super();
	}
	
	public NavbarPage(WebDriver wd){
		super(wd);
	}
	
	public StoreHomePage clickHomeLink() {
		this.findElementbyLinkText(HOME_TEXT).click();
		return new StoreHomePage();
	}
	
	public ProductsPage clickProductCategoryLink(String text) {
		this.findElementbyLinkText(text).click();
		return new ProductsPage(selenium);
	}
	
	public void clickProductUnfilterLink() {
		
	}
	
	public AccountPage clickAccountLink() {
		AccountPage newPage = new AccountPage(selenium);
		try {
			findElementbyLinkText(ACCOUNT).click();			
		} catch(StaleElementReferenceException e) {
			Assert.fail("Could not click due to stale reference. Dynamic content?");
		}
		return newPage; 
	}
	
	
	
	enum ProductCategory{
		ACCESSORIES("Accessories"),
		IMACS("iMacs"),		
		IPADS("iPads"),
		IPHONES("iPhones"),
		IPODS("iPods"),
		MACBOOKS("MacBooks");
		
		private String linkText;
		
		ProductCategory(String s) {
			this.linkText=s;
		}
		
		public String getLinkText(ProductCategory pc) {
			return pc.linkText;
		}
	}
}
*/