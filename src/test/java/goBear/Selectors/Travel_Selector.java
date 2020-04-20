package goBear.Selectors;

public class Travel_Selector {
	
	public static String ls_card = "//div[@id='travel-quote-list']/descendant::div[@class='grid-row']/div";
	public static String chb_FPGInsurance = "//div[@data-filter-name='FPG Insurance']";
	public static String lnk_showMore = "//div[@id='collapseFilter']/descendant::a[@class='btn-ripple more']";
	public static String select_Destination = "//div[label[normalize-space(.)='DESTINATION']]/descendant::div[contains(@class,'select-component')]";
	public static String lb_searchConditions = "//div[@data-gb-name='travel-nav-data']/p/small";
	public static String dtp_startDate ="//div[label[text()='TRAVEL START DATE']]/descendant::input[@name='dates-startdate']";
	public static String lb_loading = "//div[@data-gb-name='loading-status']";
	public static String slider_min = "//div[@id='collapseFilter']/descendant::div[@class='slider-handle min-slider-handle round']";
	public static String lb_Price_xpath ="//div[@class='policy-price']/span[@class='value']";
	
	public static String getCardByInsurersXpath(String insurerType){
		return "//div[@class='card-brand']/descendant::h4[text()='"+insurerType+"']";
	}
	
	public static String getFilterXpath(String filterName){
		return "//label[normalize-space(text())='"+ filterName+"']";
	}
	
	public static String getMoneyByTypeXpath(String type){
		return "//div[p[text()='"+type+"']]/p[contains(@class,'detail-value')]/strong/span";
	}
	
	public static String getSortOptionXpath(String visibleSortText){
		return "//label[normalize-space(.)='"+visibleSortText+"']";
	}
	
	public static String getListCardPriceXpath(int cardIndex){
		return "//div[@id='travel-quote-list']/descendant::div[@class='grid-row']/div["+cardIndex+"]/descendant::div[@class='policy-price']/span[@class='value']";
	}
	
	public static String getMonthXpath(String month){
		return "//div[@class='datepicker-months']/descendant::span[contains(@class,'month')][text()='"+month+"']";
	}
	
	public static String getYearXpath(String year){
		return "//div[@class='datepicker-years']/descendant::span[contains(@class,'year')][text()='"+year+"']";
	}

	public static String getMonthSwitcherXpath(){
		return "//div[@class='datepicker-days']/descendant::th[@class='datepicker-switch']";
	}
	
	public static String getYearSwitcherXpath(){
		return "//div[@class='datepicker-months']/descendant::th[@class='datepicker-switch']";
	}
	
	public static String getDayXPath(String day){
		return "//div[@class='datepicker-days']/descendant::td[@class='day'][text()='"+day+"']";
	}
	public static String getSliderXpath(String sliderType){
		return "//div[label[normalize-space(.)='"+sliderType+"']]/descendant::div[@class='slider-track']";
	}
}
