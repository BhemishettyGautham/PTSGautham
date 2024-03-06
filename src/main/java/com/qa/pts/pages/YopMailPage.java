package com.qa.pts.pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.pts.constants.AppConstants;
import com.qa.pts.utils.ElementUtil;

public class YopMailPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailID = By.xpath("//input[@id=\"login\"]");
	private By proceedBtn = By.xpath("//button[@title=\"Check Inbox @yopmail.com\"]");
	private By loginLink = By.xpath("//span[text()=\"Login\"]");
	
	public YopMailPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public void mailLogin(String userName) {
		String loginTab = driver.getWindowHandle();
		driver.switchTo().newWindow(WindowType.TAB);
		String yopmailTab = driver.getWindowHandle();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.get("https://yopmail.com/");
		eleUtil.waitForElementPresence(emailID, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(userName);
		eleUtil.doClick(proceedBtn);
		eleUtil.waitForFrameAndSwitchToItByIDOrName(AppConstants.DEFAULT_MEDIUM_TIME_OUT, "ifmail");
		eleUtil.doClick(loginLink);
//		
//		Set<String> handles = driver.getWindowHandles();
//		Iterator<String> it = handles.iterator();
		driver.switchTo().window(loginTab).close();
		driver.switchTo().window(yopmailTab);
		

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		if(it.hasNext()) {
			it.next();
			String newTab = it.next();
			driver.switchTo().window(newTab);
			String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIME_OUT, AppConstants.HOME_PAGE_TITLE_VALUE);
			System.out.println("Title is: " + title);
		
		}
	}

}
