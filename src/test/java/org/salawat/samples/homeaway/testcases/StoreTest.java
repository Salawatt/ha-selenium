package org.salawat.samples.homeaway.testcases;

import java.math.BigDecimal;

import org.salawat.samples.homeaway.commons.BeforeAfterSuiteClass;
import org.salawat.samples.homeaway.enums.Actors;
import org.salawat.samples.homeaway.pages.AbstractPage.NavbarPage;
import org.salawat.samples.homeaway.pages.AbstractPage.ProductCategory;
import org.salawat.samples.homeaway.pages.AccountPage;
import org.salawat.samples.homeaway.pages.CheckoutPage;
import org.salawat.samples.homeaway.pages.ProductsPage;
import org.salawat.samples.homeaway.pages.StoreHomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class StoreTest {

	private static final String productTitle = "Apple iPhone 4S 16GB SIM-Free – Black";
	private static final String country = "USA";
	StoreHomePage storeHomePage;

	@BeforeClass
	public void setUpClass() {
		BeforeAfterSuiteClass.loadProperties();
		storeHomePage = new StoreHomePage();
	}

	/*
	 * @BeforeMethod public void beforeMethod() { storeHomePage.go(); }
	 */

	/**
	 * Lets get a Black iPhone.
	 */
	@Test(priority=1)
	public void testPurchase() {
		NavbarPage nb = null;
		ProductsPage pPage = null;
		CheckoutPage checkout = null;
		int productId = 0;
		BigDecimal statedPrice = null;
		BigDecimal bd = null;
		// Do some navbar magic
		nb = storeHomePage.new NavbarPage();
		// We want an iPhone
		ProductCategory pc = ProductCategory.IPHONES;

		// Click the iPhone category.
		pPage = nb.clickProductCategorySubLink(pc.getLinkText());
		// Now that we're on the products page, lets home in on the one we
		// want
		productId = Integer.valueOf(pPage.getProductIdForProductWithTitle(productTitle));
		String dollarAmount = pPage.getPriceForItemWithTitle(productTitle);
		statedPrice = new BigDecimal(dollarAmount.substring(1, dollarAmount.length()));
		pPage.clickProduct(productTitle);
		// And finally, lets go ahead, click buy, and go to checkout.
		checkout = pPage.clickBuyButtonAndGoToCheckout();
		checkout.clickContinue();
		checkout.selectCountry(country);
		checkout.clickCalculate();
		BigDecimal tax = null;
		BigDecimal total = null;
		BigDecimal shipping = null;
		dollarAmount = checkout.getShipping();
		shipping = new BigDecimal(dollarAmount.substring(1, dollarAmount.length()));
		;
		dollarAmount = checkout.getTax();
		tax = new BigDecimal(dollarAmount.substring(1, dollarAmount.length()));
		dollarAmount = checkout.getTotal();
		total = new BigDecimal(dollarAmount.substring(1, dollarAmount.length()));
		BigDecimal expected = BigDecimal.ZERO.add(statedPrice).add(shipping).add(tax);

		Assert.assertEquals(expected, total);
	}

	@Test(priority=3)
	public void testAccountUpdate() {
		NavbarPage nb = storeHomePage.new NavbarPage();
		// Navigate to login
		AccountPage ap = nb.clickAccountLink();
		// enter credentials
		ap.enterUserName(Actors.JSCHMOE.getUsername());
		ap.enterPassword(Actors.JSCHMOE.getPassword());
		ap.clickLogInButton();
		// Click details link, update some information, and save
		ap.clickYourDetails();
		ap.fillOutFirstName("Jeffrey");
		ap.clickSaveProfile();
		// logout
		ap.clickLogoutLink();
		// this step required since the login prompt the site returns you to on
		// logout
		// is NOT the login prompt we're looking for.
		storeHomePage = ap.goHome();
		nb.clickAccountLink();
		// Login again, check details
		ap.enterUserName(Actors.JSCHMOE.getUsername());
		ap.enterPassword(Actors.JSCHMOE.getPassword());
		ap.clickLogInButton();
		ap.clickYourDetails();
		// Assert the info is there.
		Assert.assertTrue(ap.getValueOfFirstNameField().equals("Jeffrey"));
	}

	@Test(priority=2)
	public void verifyEmptyCartNotification() {
		// Add a couple things in the cart

		NavbarPage navBar = storeHomePage.new NavbarPage();
		ProductsPage productsPage = null;
		ProductCategory pc = ProductCategory.IPHONES;

		// Click the iPhone category.
		productsPage = navBar.clickProductCategorySubLink(pc.getLinkText());
		// Arbitrary purchase...
		productsPage.doQuickBuy(2);
		// And the other, now to checkout
		productsPage.doQuickBuy(2);
		CheckoutPage checkout = navBar.clickCheckoutLink();
		// Should not see a message after removing one.
		checkout.removeTopProductFromCart();
		Assert.assertTrue(checkout.isErrorMessagePresent());
		// Should see a message after this one.
		checkout.removeTopProductFromCart();
		Assert.assertFalse(checkout.isErrorMessagePresent());

	}

	@AfterMethod
	public void teardown() {
		storeHomePage.go();
	}

}
