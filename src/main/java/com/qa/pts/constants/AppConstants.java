package com.qa.pts.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_LONG_TIME_OUT = 20;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "panna-auth.mroads.com";
	
	public static final String HOME_PAGE_TITLE_VALUE = "Dashboard";
	public static final String HOME_PAGE_URL_FRACTION_VALUE = "https://panna-auth.mroads.com/dashboard";
	public static final int HOME_PAGE_HEADERS_COUNT = 6;
	public static final List<String> HOME_PAGE_EXPECTED_HEADERS_LIST = Arrays.asList("Dashboard","Organizations","Users","Roles","Email Templates","Logout");
	
	public static final String TIMEENTRY_PAGE_TITLE_VALUE = "Time Entries";
	public static final String TIMEENTRY_PAGE_URL_FRACTION_VALUE = "https://devtimesheets.mroads.com/time-entries";
	public static final String TIMEENTRY_PAGE_PROJECT_NAME = "Gautham Test Project";
	public static final String TIMEENTRY_PAGE_ACTIVITY_NAME = "Testing";

}
