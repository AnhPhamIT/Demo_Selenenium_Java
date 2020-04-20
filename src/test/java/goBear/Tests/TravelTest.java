package goBear.Tests;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.functions.DriverFactory;
import common.functions.ReadExcel;
import goBear.Pages.HomePage;
import goBear.Pages.TravelPage;

public class TravelTest {
	public static final Logger logger = LogManager.getLogger("DEMO");
	HomePage homePage;
	TravelPage travelPage;
	WebDriver driver;
	ReadExcel dataBuilder;
	String filePath =System.getProperty("user.dir") + File.separator + "data" + File.separator + "data.xls";
	
	String sheetName = "Travel";
	
	@BeforeMethod
	public void beforeTest() throws Exception{
		driver = DriverFactory.createDriver();
		homePage = new HomePage(driver);
		travelPage = new TravelPage(driver);
	}

	@DataProvider(name="TravelTest")
    public Object[][] getDataFromDataprovider() throws IOException{
    	return	ReadExcel.getExcelData(filePath, sheetName);

    }
	@Test(dataProvider = "TravelTest")
	public void Challenge2(String personalAccident, String destination) throws Exception {
		logger.info("STEP 1: Select Show My Result");
		homePage.showMyResult();
		
		logger.info("Make sure at least 3 cards are being displayed ");
		int noOfCard = travelPage.getListCards().size();
		Assert.assertTrue(noOfCard >= 3);
		
		logger.info("STEP 2: Filter by FPG Insurance");
		int noOfNotFilterCard = travelPage.getNoOfCardByInsurer("FPG Insurance");
		logger.info("Before filer - no of FPG Insurance card " + noOfNotFilterCard);
		travelPage.selectFilter("FPG Insurance");
		int noOfFiltedCard = travelPage.getListCards().size();
		logger.info("After filter - no of FPG Insurance card " + noOfFiltedCard);
		Assert.assertTrue(noOfNotFilterCard == noOfFiltedCard, "Filter as FPG Insurance work as expected");
		
		logger.info("STEP 3: Sorting by Price: High to low");
		List<Double> lsPrice= travelPage.getListCardPrice();
		travelPage.sortDescListPrice(lsPrice);
		travelPage.selectSortOption("Price: High to low");
		logger.info("List Price after sorting");
		List<Double> lsSortedPrice= travelPage.getListCardPrice();
		Assert.assertTrue(lsSortedPrice.equals(lsPrice), "Sort High to low function work correctly");

		logger.info("STEP 4: Filter with Personal Accident");
		logger.info("Select Slider to " + personalAccident);
		travelPage.selectShowMore();
		travelPage.selectSlider("Personal Accident", personalAccident, 3000000, 0);
		List<Double> lsCardMoney = travelPage.getListCardMoneyBy("Personal Accident");
		Assert.assertTrue(travelPage.isAllCardAboveAmountOfMoney(personalAccident, lsCardMoney), "Slider Personal Account work correctly");
	
		logger.info("STEP 5: Select Destination to " + destination);
		travelPage.selectDestinaiton(destination);
		Assert.assertTrue(travelPage.getSearchConditions().contains(destination), "Destination has been applied");
		
		logger.info("STEP 6: Select Travel Start Date");
		travelPage.selectTravelStartDate("2021", "Oct", "29");
		Assert.assertTrue(travelPage.getSearchConditions().contains("29 Oct 2021"), "Travel StartDate has been applied");
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException{
		driver.quit();
	}
}
