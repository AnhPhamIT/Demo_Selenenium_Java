package goBear.Pages;

import org.openqa.selenium.WebDriver;

import common.functions.WebSupport;
import goBear.Selectors.Home_Selector;

public class HomePage {
	private WebDriver driver;
	private WebSupport webSupport;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		webSupport = new WebSupport(driver);
	}
	
	public void showMyResult() {
		webSupport.clickOnElement(Home_Selector.lnk_Insurance);
		webSupport.clickOnElement(Home_Selector.lnk_Travel);
		webSupport.clickOnElement(Home_Selector.btn_ShowMyResult);
	}
}
