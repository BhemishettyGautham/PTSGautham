package com.qa.pts.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	private By updateBtn = By.xpath("//button[text()=\"Update\"]");
	private By resetBtn = By.xpath("//button[text()=\"Reset\"]");
	private By cancelBtn = By.xpath("//button[text()=\"Cancel\"]");
	private By calenderSwitchYear = By.xpath("//button[@aria-label='calendar view is open, switch to year view']");
	private By nextMonthBtn = By.xpath("//button[@title='Next month']");
	private By getDate = By.xpath("(//div[@class=\"justify-center w-full\"])[position()=1]");
	private By okBtn = By.xpath("//button[text()=\"OK\"]");
	private By cnfmPromptOkBtn = By.xpath("//button[text()=\"Ok\"]");
	private By successMsg = By.xpath("//div//h2");
	
	private By body = By.tagName("body");
	
	private By search = By.xpath("//input[@placeholder='Search Employee']");
	private By searchUserResult = By.cssSelector("div.text-sm");
	private By userName = By.xpath("//tbody//td[2]");
	
	private By paginationNext = By.xpath("//*[local-name()='svg' and @class='-rotate-90 cursor-pointer']");
	
	
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
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	public String getUserName() {
		String value = eleUtil.doElementGetText(userName);
		System.out.println("User Full Name: " + value);
		driver.navigate().refresh();
		return value;
	}
	
	/**
	 * This Method is used to Create a New User
	 * @param fName
	 * @param lName
	 * @param phNum
	 * @param emailId
	 * @param dobY
	 * @param dobM
	 * @param dobD
	 * @param dojY
	 * @param dojM
	 * @param dojD
	 * @param jobTitle
	 * @param dept
	 * @param role
	 * @param empCode
	 */
	public void createUser(String fName, String lName, String phNum, String emailId, String dobY, String dobM, String dobD, String dojY, String dojM, String dojD,
			String jobTitle, String dept, String role, String empCode) {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, createNewUserBtn).click();
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName).sendKeys(fName);
		eleUtil.doSendKeys(lastName, lName);
		eleUtil.doSendKeys(phoneNumber, phNum);
		eleUtil.doSendKeys(email, emailId);
		eleUtil.doClick(dobBtn);
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
		eleUtil.doClick(roleSelection);
		eleUtil.doClick(body);
		eleUtil.doSendKeys(empID, empCode);
		eleUtil.doClick(createBtn);
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, cnfmPromptOkBtn).click();
		
	}
	
	public String getSuccessMsg() {
		String text = eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, successMsg).getText();
		System.out.println(text);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	 * This method is used to select date
	 * Ex: Year as 1992; Month as April; Day as 15 --(1992-April-15)
	 * @param year
	 * @param month
	 * @param day
	 */
	private void dateSelection(String year, String month, String day) {
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, calenderSwitchYear).click();
		By selectYear = By.xpath("//button[text()='"+year+"']");
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
		String text = eleUtil.doElementGetText(getDate);
		System.out.println(text);	
	}
	
	public boolean isPageNextExists() {
		return eleUtil.waitForElementVisible(paginationNext, AppConstants.DEFAULT_SHORT_TIME_OUT).isDisplayed();
	}
	

	public void editBtnClick(String username) {
		Actions act = new Actions(driver);
		By actElement = By.xpath("//tbody//td[text()='"+username+"']/parent::tr//td[6]");
		WebElement actionElement = eleUtil.getElement(actElement);
        //WebElement actionElement = driver.findElement(By.xpath("//tbody//td[text()='"+username+"']/parent::tr//td[6]"));
        act.moveToElement(actionElement).build().perform();
		By editBtn = By.xpath("(//tbody//td[text()='"+username+"']/parent::tr//*[local-name()='svg'])[position()=1]");
		eleUtil.doClick(editBtn);
	}
	
	public void deleteUser(String username) {
		Actions act = new Actions(driver);
		By actElement = By.xpath("//tbody//td[text()='"+username+"']/parent::tr//td[6]");
		WebElement actionElement = eleUtil.getElement(actElement);
        //WebElement actionElement = driver.findElement(By.xpath(""));
        act.moveToElement(actionElement).build().perform();
		By delBtn = By.xpath("(//tbody//td[text()='"+username+"']/parent::tr//*[local-name()='svg'])[position()=2]");
		eleUtil.doClick(delBtn);
		eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, okBtn).click();
	}
	
	/**
	 * This method is used to search user by using pagination controls and matching with employee name
	 * @param username
	 */
//	public void searchUser(String username) {
//	    boolean found = false;
//
//	    while (isPageNextExists() && !found) {
//	        List<WebElement> elements = eleUtil.getElements(userName);
//	        for (WebElement element : elements) {
//	            if (element.getText().contains(username)) {
//	                found = true;
//	                break;
//	            }
//	        }
//	        if (!found) {
//	            eleUtil.doClick(paginationNext);
//	        }
//	    }
//	}
	
	public void searchUser(String username) {
	    boolean found = false;

	    do {
	        List<WebElement> elements = eleUtil.getElements(userName);
	        for (WebElement element : elements) {
	            if (element.getText().contains(username)) {
	                found = true;
	                break;
	            }
	        }
	        if (!found && isPageNextExists()) {
	            eleUtil.doClick(paginationNext);
	        }
	    } while (!found && isPageNextExists());
	}
	
	/**
	 * this method is used to update any field to existing user details
	 * @param updatedFields
	 */
	public void updateUser(Map<String, String> updatedFields) {
	    Actions actions = new Actions(driver);

	    // Update specific fields
	    for (Map.Entry<String, String> entry : updatedFields.entrySet()) {
	        String field = entry.getKey();
	        String value = entry.getValue();
	        switch (field) {
	            case "First Name":
	            	eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName).clear();
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, firstName).sendKeys(value);
	                break;
	            case "Last Name":
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, lastName).clear();
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, lastName).sendKeys(value);
	                break;
	            case "Phone Number":
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, phoneNumber).clear();
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, phoneNumber).sendKeys(value);
	                break;
	            case "Email":
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, email).clear();
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, email).sendKeys(value);
	                break;
	            case "Date of Birth":
	                // Assuming value is in format YYYY-MM-DD
	                String[] dob = value.split("-");
	                dateSelection(dob[0], dob[1], dob[2]);
	                break;
	            case "Date of Joining":
	                // Assuming value is in format YYYY-MM-DD
	                String[] doj = value.split("-");
	                dateSelection(doj[0], doj[1], doj[2]);
	                break;
	            case "Job Title":
	            	eleUtil.doClick(jobTitleBtn);
	        		By jobTitleSelect = By.xpath("//ul[@role=\"listbox\"]/li[text()='"+value+"']");
	        		eleUtil.doClick(jobTitleSelect);
	                break;
	            case "Department":
	            	By deptSelect = By.xpath("//ul[@role=\"listbox\"]/li[text()='"+value+"']");
	        		eleUtil.doClick(deptBtn);
	        		eleUtil.doClick(deptSelect);
	                break;
	            case "Role":
	            	eleUtil.doClick(roleIdBtn);
	        		By roleSelection = By.xpath("//span[text()='"+value+"']/ancestor::li");
	        		eleUtil.doClick(roleSelection);
	                break;
	            case "Employee Code":
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, empID).clear();
	                eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_MEDIUM_TIME_OUT, empID).sendKeys(value);
	                break;
	            default:
	                System.out.println("Invalid field provided: " + field);
	                break;
	        }
	    }

	    eleUtil.doClick(updateBtn);
	    eleUtil.waitForElementToBeClickable(AppConstants.DEFAULT_SHORT_TIME_OUT, cnfmPromptOkBtn).click();
		
	}

}
