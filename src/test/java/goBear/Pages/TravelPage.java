package goBear.Pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import common.functions.WebSupport;
import goBear.Selectors.Travel_Selector;

public class TravelPage {
	private WebDriver driver;
	private WebSupport webSupport;
	WebDriverWait wait;
	
	public TravelPage(WebDriver driver) {
		this.driver = driver;
		webSupport = new WebSupport(driver);
		this.wait = new WebDriverWait(this.driver, 10);
	}
	
	public List<WebElement> getListCards() {
		webSupport.waitForElementToBeGone(Travel_Selector.lb_loading, 3);
		List<WebElement> ls_card = webSupport.waitForListElementDisplayed(Travel_Selector.ls_card);
		return ls_card;
	}
	
	public void selectFilter(String filterName){
		webSupport.clickOnElement(Travel_Selector.getFilterXpath(filterName));
	}
	
	public int getNoOfCardByInsurer(String insurerType){
		webSupport.waitForElementToBeGone(Travel_Selector.lb_loading, 3);
		List<WebElement> lsCard = webSupport.waitForListElementDisplayed(Travel_Selector.getCardByInsurersXpath(insurerType));
		return lsCard.size();
	}
	
	public List<Double> getListCardMoneyBy(String moneyType){
		webSupport.waitForElementToBeGone(Travel_Selector.lb_loading, 3);
		List<WebElement> lsCard = webSupport.waitForListElementDisplayed(Travel_Selector.getMoneyByTypeXpath(moneyType));
		List<Double> ls_price= new ArrayList<Double>();
		String str_amount="";
		for (WebElement webElement : lsCard) {
			//str_amount = webElement.getText().replaceAll("[₱,]", "");
			//String str_personalAcc = getCardPersonalAccidentByIndex(i).split(".+₱")[1].split(" ")[0].replaceAll(",", "");
			str_amount = webElement.getText().split("₱")[1];
			if(str_amount.contains(" ")){
				str_amount = str_amount.split("\t")[0].replaceAll(",", "");
			}else{
				str_amount = str_amount.replaceAll(",", "");;
			}
			ls_price.add(Double.parseDouble(str_amount));
			System.out.println("Amount money " + str_amount);
		}
		return ls_price;
	}
	
	public boolean isAllCardAboveAmountOfMoney(String str_amount, List<Double> lsCard){
		Double amount = Double.parseDouble(str_amount);
		for (Double card : lsCard) {
			if(card<amount){
				return false;
			}
		}
		return true;
	}
	
	public List<Double> getListCardPrice(){
		webSupport.waitForElementToBeGone(Travel_Selector.lb_loading, 3);
		List<WebElement> lsPrice_ele = webSupport.waitForListElementDisplayed(Travel_Selector.lb_Price_xpath);
		List<Double> lsPrice = new ArrayList<Double>();
		String str_amount="";
		for (WebElement webEle : lsPrice_ele) {
			str_amount = webEle.getText().replaceAll(",", "");
			lsPrice.add(Double.parseDouble(str_amount));
			System.out.println("Amount money " + str_amount);
		}
		return lsPrice;
	}
	
	public void selectSortOption(String visibleSortText){
		webSupport.clickOnElement(Travel_Selector.getSortOptionXpath(visibleSortText));
	}
	
	public void sortDescListPrice(List<Double> lsPrice){
		Collections.sort(lsPrice, Collections.reverseOrder()); 
	}
	
	public static int GetPixelsToMove(WebElement Slider, float Amount, float SliderMax, float SliderMin)
    {
        int pixels = 0;
        float tempPixels = Slider.getSize().getWidth();
        tempPixels = tempPixels / (SliderMax - SliderMin);
        tempPixels = tempPixels * (Amount - SliderMin);
        pixels = (int)(tempPixels);
        return pixels;
    }
	
	public void selectSlider(String sliderType, String amount, float sliderMax, float sliderMin){
		float sliderAmount = Float.parseFloat(amount);
		WebElement Slider = webSupport.waitForElementDisplayed(Travel_Selector.getSliderXpath(sliderType));
		WebElement minSlider = webSupport.waitForElementDisplayed(Travel_Selector.slider_min);
		int pixels=GetPixelsToMove(Slider, sliderAmount, sliderMax, sliderMin);
		System.out.println("start slider from " + minSlider.getLocation().x);
		System.out.println("stop moving x " + pixels);
		Actions SliderAction = new Actions(driver);   
		SliderAction.moveToElement(minSlider).click().dragAndDropBy(minSlider,pixels, 0).build().perform();
	}
	
	public void selectShowMore(){
		webSupport.clickOnElement(Travel_Selector.lnk_showMore);
	}
	
	public void selectDestinaiton(String visibleText){
		webSupport.clickOnElement(Travel_Selector.select_Destination);
		webSupport.clickOnElement("//li[a[span[text()='"+visibleText+"']]]");
	}
	
	public String getSearchConditions(){
		return webSupport.GetText(Travel_Selector.lb_searchConditions);
	}
		
	public void selectTravelStartDate(String year, String month, String day){
		webSupport.clickOnElement(Travel_Selector.dtp_startDate);
		webSupport.clickOnElement(Travel_Selector.getMonthSwitcherXpath());
		webSupport.clickOnElement(Travel_Selector.getYearSwitcherXpath());
		webSupport.clickOnElement(Travel_Selector.getYearXpath(year));
		webSupport.clickOnElement(Travel_Selector.getMonthXpath(month));
		webSupport.clickOnElement(Travel_Selector.getDayXPath(day));
	}
}

