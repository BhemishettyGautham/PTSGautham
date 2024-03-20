package com.qa.pts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ElementUtil;

public class UsersPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public UsersPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By createNewUserBtn = By.xpath("//button[text()=\"Create New User\"]");
	private By firstName = By.xpath("//input[@placeholder=\"First Name*\"]");
	private By lastName = By.xpath("//input[@placeholder=\"Last Name*\"]");
	private By phoneNumber = By.name("phoneNumber");
	private By email = By.xpath("//input[@placeholder=\"Email Address*\"]");
	private By dobBtn = By.xpath("//label[text()=\"Date Of Birth\"]/parent::div//button");
	private By dojBtn = By.xpath("//label[text()=\"Date Of Joining\"]/parent::div//button");
	private By jobTitleBtn = By.xpath("//label[text()=\"Job Title\"]/parent::div//div[@role=\"button\"]");
	private By deptBtn = By.xpath("//label[text()=\"Department\"]/parent::div//div[@role=\"button\"]");
	private By roleIdBtn = By.xpath("//label[text()=\"Roles Ids*\"]/parent::div//div[@role=\"button\"]");
	private By empID = By.xpath("//input[@placeholder=\"Employee ID*\"]");
	private By createBtn = By.xpath("//button[text()=\"Create\"]");
	private By resetBtn = By.xpath("//button[text()=\"Reset\"]");
	private By calenderSwitchYear = By.xpath("//button[@aria-label='calendar view is open, switch to year view']");
	private By nextMonthBtn = By.xpath("//button[@title='Next month']");
	private By getDate = By.xpath("(//div[@class=\"justify-center w-full\"])[position()=1]");
	private By prmptCfmOk = By.xpath("//button[text()=\"Ok\"]");
	
	private By body = By.tagName("body");
	
	private By search = By.xpath("//input[@placeholder='Search Employee']");
	private By searchUserResult = By.cssSelector("div.text-sm");
	private By userName = By.xpath("//tbody//td[2]");
	
	
	public String getUsersPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.USERS_PAGE_TITLE_VALUE);
		System.out.println("Users Page Title is: " + title);
		return title;
	}
	
	public String getUsersPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.USERS_PAGE_URL_FRACTION_VALUE);
		System.out.println("uSERS Page Url is: " + url);
		return url;
	}
	
	public boolean isSearchExists() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	
	public void performSearch(String searchValue) {
		if(isSearchExists()) {
			eleUtil.doSendKeys(search, searchValue);			
		}
		else {
			System.out.println("Search field is not present on the page.....");
		}
	}
	
	public int getSearchUsersCount() {
			int value =	eleUtil.waitForElementsVisible(searchUserResult, AppConstants.DEFAULT_SHORT_TIME_OUT).size();
			System.out.println("Search results count is: "+ value);
		return value;
	}
	
	public void selectSearchUser(String userName) {
		By userLocator = By.xpath("//div[text()='"+ userName +"']/ancestor::li");
		eleUtil.waitForElementVisible(userLocator, AppConstants.DEFAULT_SHORT_TIME_OUT).click();
		//eleUtil.doClick(body);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	public String getUserName() {
		String value = eleUtil.doElementGetText(userName);
		System.out.println("User Full Name: " + value);
		driver.navigate().refresh();
		return value;
	}
	
	public void createUser(String fName, String lName, String phNum, String emailId, String dobY, String dobM, String dobD, String dojY, String dojM, String dojD,
			String jobTitle, String dept, String role, String empCode) {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, createNewUserBtn).click();
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName).sendKeys(fName);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(phoneNumber, phNum);
		eleUtil.doSendKeys(email, emailId);
		eleUtil.doClick(dobBtn);
//		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, calenderSwitchYear).click();
//		eleUtil.doActionsCick(selectYear1992);
//		eleUtil.doClick(selectYear1992);
//		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, nextMonthBtn).click();
//		eleUtil.doClick(dateSelect15);
//		String text = eleUtil.doElementGetText(getDate);
//		System.out.println(text);
		
		dateSelection(dobY, dobM, dobD);
		eleUtil.doClick(dojBtn);
		dateSelection(dojY, dojM, dojD);
		eleUtil.doClick(jobTitleBtn);
		By jobTitleSelect = By.xpath("//ul[@role=\"listbox\"]/li[text()='"+jobTitle+"']");
		eleUtil.doClick(jobTitleSelect);
		By deptSelect = By.xpath("//ul[@role=\"listbox\"]/li[text()='"+dept+"']");
		eleUtil.doClick(deptBtn);
		eleUtil.doClick(deptSelect);
		eleUtil.doClick(roleIdBtn);
		By roleSelection = By.xpath("//span[text()='"+role+"']/ancestor::li");
		//eleUtil.doActionsCick(roleSelection);
		eleUtil.doClick(roleSelection);
		eleUtil.doClick(body);
		eleUtil.doSendKeys(empID, empCode);
		eleUtil.doClick(createBtn);
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, prmptCfmOk).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void dateSelection(String year, String month, String day) {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, calenderSwitchYear).click();
		By selectYear = By.xpath("//button[text()='"+year+"']");
		//eleUtil.doActionsCick(selectYear);
		eleUtil.doClick(selectYear);
		By getMonth = By.xpath("//button[@aria-label='calendar view is open, switch to year view']/preceding-sibling::div/div");
		By daySelect = By.xpath("//button[not(contains(@class,\"MuiPickersDay-dayOutsideMonth\"))] [text()='"+day+"']");
		for(int i=0; i<12; i++) {
			if(eleUtil.doElementGetText(getMonth).contains(month)) {
				eleUtil.doClick(daySelect);
				break;
			}
			else{
				eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, nextMonthBtn).click();
			}
		}
//		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, nextMonthBtn).click();
//		eleUtil.doClick(dateSelect15);
		String text = eleUtil.doElementGetText(getDate);
		System.out.println(text);
		
	}
	
	

}
