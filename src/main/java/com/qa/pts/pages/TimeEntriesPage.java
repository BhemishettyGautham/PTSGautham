package com.qa.pts.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ElementUtil;

public class TimeEntriesPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By addProjectBtn = By.xpath("//*[text()=\"Add Project\"]");
	private By projectsList = By.xpath("//div[@id=\"projects-list\"]//li");
	private By selectActivityBtn = By.xpath("//input[@placeholder=\"Select Activity\"]");
	private By activities = By.xpath("//div[@role=\"presentation\"]//li");
	private By timeEntry = By.xpath("//input[@type=\"text\"]");
	private By addNotesBtn = By.xpath("//span[text()=\"Add Notes\"]//parent::button");
	private By notesText = By.xpath("//textarea[@placeholder]");
	private By saveBtn = By.xpath("//button[text()=\"Save\"]");
	private By submitBtn = By.xpath("//button[text()=\"Submit\"]");

	public TimeEntriesPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getTimeEntryPageTitle() {
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.TIMEENTRY_PAGE_TITLE_VALUE);
		System.out.println("Time Entry Page Title is: " + title);
		return title;
	}
	
	public String getTimeEntryPageUrl() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.TIMEENTRY_PAGE_URL_FRACTION_VALUE);
		System.out.println("Time entry Page Url is: " + url);
		return url;
	}
	
	public int getProjectsListCount() {
		int count = eleUtil.waitForElementsVisible(projectsList, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Count of Projects are: " + count);
		return count;
	}
	
	public void clickProjectBtn() {
		eleUtil.waitForElementPresence(addProjectBtn, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
	}
	
	public void selectProject(String projectName) {
		By projectLocator = By.xpath("//h6[text()='"+projectName+"']/parent::div/parent::li");
		eleUtil.waitForElementVisible(projectLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
	}
	
	public void clickActivity() {
		eleUtil.waitForElementPresence(selectActivityBtn, AppConstants.DEFAULT_SHORT_TIME_OUT).click();
	}
	
	public int getActivitesListCount() {
		int count = eleUtil.waitForElementsVisible(activities, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Count of Activites are: " + count);
		return count;
	}
	
	
	public void selectActivity(String activityName) {
		By activityLocator = By.xpath("//li[text()='"+activityName+"']");
		eleUtil.waitForElementVisible(activityLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
	}
	
	public void enterTimeEntry() {
		for(WebElement e:eleUtil.getElements(timeEntry)) {
			if(e.isEnabled()) {
				e.click();
				e.sendKeys("8");
			}
		}
	}
	
	public void saveTimeEntry() {
		eleUtil.doClick(saveBtn);
	}

}
