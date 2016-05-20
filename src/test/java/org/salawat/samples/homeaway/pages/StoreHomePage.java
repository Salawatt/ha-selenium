package org.salawat.samples.homeaway.pages;

public class StoreHomePage extends AbstractPage {

	private static final String CART_CLASS = "cart_icon";
	private static final String MY_ACCOUNT = "account_icon";
	private static final String BUYNOW_CLASS="buynow";
	// private static final String ;

	public StoreHomePage() {
		super();
		selenium.get(baseUrl);
	}

	public void go() {
		selenium.get(baseUrl);
	}

	public void doBuyNow(){
		findElementByClass(BUYNOW_CLASS).click();
	}
	
	

}
