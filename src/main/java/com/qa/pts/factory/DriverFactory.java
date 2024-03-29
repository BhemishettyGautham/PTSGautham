package com.qa.pts.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
	
public WebDriver driver;
public Properties prop;
public OptionsManager optionsManager;

public static String highlight;

public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	

/**
 * this method is initializing the driver on the basis of given browser name
 * @param browserName
 * @return this returns the driver
 */
	public WebDriver initDriver(Properties prop) {
		
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser name is: " + browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\SeleniumJars\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		else {
			System.out.println("Pls pass the correct browser....."+ browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	/**
	 * get the local thread copy of the driver
	 * @return 
	 */
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * this method is reading the properties from .properties file
	 * @return
	 */
	public Properties initProp() {
		
		//mvn clean install -Denv="qa"
		//mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);
		try {
		if(envName==null) {
			System.out.println("no env is passed...Running test cases on QA env...");
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;

			default:
				System.out.println("wrong env is passed... no need to run test cases....");
				break;
			}
		}
		}
		catch(FileNotFoundException e) {
			
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	/**
	 * take screenshot
	 * @return 
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
