package com.qa.pts.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.pts.base.BaseTest;
import com.qa.pts.constants.AppConstants;

public class TimeEntryPageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim());
		timeEntryPage = homePage.goToTimesheets();
	}
	
	@Test(priority = 1)
	public void timeEntryPageTitleTest() {
		String actTitle = timeEntryPage.getTimeEntryPageTitle();
		Assert.assertEquals(actTitle, AppConstants.TIMEENTRY_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void timeEntryPageUrlTest() {
		String actUrl = timeEntryPage.getTimeEntryPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.TIMEENTRY_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void timeEntryPageProjectsCountTest() {
		Assert.assertTrue(timeEntryPage.getProjectsListCount()>0);
	}
	
	@Test(priority = 4)
	public void timeEntryPageActivitiesCountTest() {
		Assert.assertTrue(timeEntryPage.getActivitesListCount(AppConstants.TIMEENTRY_PAGE_PROJECT_NAME)>0);
	}
	
	@Test(priority = 5)
	public void timeEntryPageActivitySelectTest() {
		timeEntryPage.selectActivity(AppConstants.TIMEENTRY_PAGE_PROJECT_NAME, AppConstants.TIMEENTRY_PAGE_ACTIVITY_NAME);
	}
	
	@Test(priority = 6)
	public void timeEntriesAddTest() {
		timeEntryPage.enterTimeEntry(AppConstants.TIMEENTRY_PAGE_PROJECT_NAME, AppConstants.TIMEENTRY_PAGE_ACTIVITY_NAME);
		timeEntryPage.saveTimeEntry();
	}

}
