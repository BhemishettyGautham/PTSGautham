package com.qa.pts.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pts.base.BaseTest;
import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ExcelUtil;

public class UsersPageTest extends BaseTest {
	
	@BeforeClass
	public void homePageSetup() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim());
		usersPage = homePage.goToUsersTab();
	}
	
	@Test(priority = 1)
	public void usersPageTitleTest() {
		String actTitle = usersPage.getUsersPageTitle();
		Assert.assertEquals(actTitle, AppConstants.USERS_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void usersPageUrlTest() {
		String actUrl = usersPage.getUsersPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.USERS_PAGE_URL_FRACTION_VALUE));
	}
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Gautham"},
			{"snigdha"},
			{"Shanmukha"},
			{"auto"}
		};
	}
	
	@Test(priority = 3,dataProvider = "getUserData")
	public void searchUserCountTest(String searchKey) {
		usersPage.performSearch(searchKey);
		Assert.assertTrue(usersPage.getSearchUsersCount()>0);
	}
	
	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][] {
			{"Gautham", "Gautham Two"},
			{"snigdha", "snigdha reddy D"},
			{"Shanmukha", "shanmukha Srinivas"},
			{"auto", "gautham auto six"}
		};
	}
	
	@Test(priority = 4,dataProvider = "getUserTestData")
	public void searchUserTest(String searchKey, String userName) {
		usersPage.performSearch(searchKey);
		if(usersPage.getSearchUsersCount()>0) {
			usersPage.selectSearchUser(userName);
			String actName = usersPage.getUserName();
			Assert.assertEquals(actName, userName);
		}
	}
	
	@DataProvider
	public Object[][] getUserCreationTestData() {
		Object userData[][] = ExcelUtil.getTestData(AppConstants.CREATE_USER_SHEET_NAME);
		return userData;
	}
	
	@Test(priority = 5, dataProvider = "getUserCreationTestData")
	public void createUserTest(String fName, String lName, String phNum, String emailId, String dobY, String dobM, String dobD, String dojY, String dojM, String dojD,
			String jobTitle, String dept, String role, String empCode) {
		usersPage.createUser(fName, lName, phNum, emailId, dobY, dobM, dobD, dojY, dojM, dojD,
				jobTitle, dept, role, empCode);
		Assert.assertTrue(usersPage.getSuccessMsg().contains(AppConstants.USERS_PAGE_PARTIAL_SUCCESS_MSG));
	}
	
	@DataProvider
	public Object[][] getUserUpdateTestData() {
		Map<String, String> updatedFields = new HashMap<>();
		updatedFields.put("First Name", "John");
		updatedFields.put("Last Name", "John");
		return new Object[][] { { updatedFields } };
		
	}
	
	@Test(priority = 6, dataProvider = "getUserUpdateTestData")
	public void editUserTest(Map<String, String> updatedFields) {
		usersPage.searchUser("gautham auto nine");
		usersPage.editBtnClick("gautham auto nine");
		usersPage.updateUser(updatedFields);
		Assert.assertTrue(usersPage.getSuccessMsg().contains(AppConstants.USERS_PAGE_PARTIAL_SUCCESS_MSG));
	}
	
	@Test(priority = 7)
	public void deleteUserTest() { 
		usersPage.searchUser("gauthamJohn auto nineJohn");
		usersPage.deleteUser("gauthamJohn auto nineJohn");
		Assert.assertTrue(usersPage.getSuccessMsg().contains(AppConstants.USERS_PAGE_PARTIAL_SUCCESS_MSG));
	}
	
}
