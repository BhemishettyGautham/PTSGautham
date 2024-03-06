package com.qa.pts.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pts.base.BaseTest;
import com.qa.pts.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 100: design login for PTS app")
@Story("Login - 101: design login page features for PTS")
public class LoginPageTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test(priority = 3)
	public void proceedBtnExistsTest() {
		Assert.assertTrue(loginPage.isProceedBtnLinkExist());
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("...checking user is able to login to app with correct username.....")
	@Test(priority = 4)
	public void doLoginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim());
		Assert.assertTrue(homePage.isLogoutLinkExists());
	}

}
