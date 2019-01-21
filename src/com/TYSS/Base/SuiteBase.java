package com.TYSS.Base;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.TYSS.Utils.ReportUtils;

public class SuiteBase extends BaseClass {

	@BeforeTest
	public void reports() {
		try {
			logs = Logger.getLogger(this.getClass().getSimpleName());
			logs.info("*****************Before test execution started*****************");
			logs.info("log4j working fine");

			ReportUtils ru = new ReportUtils();
			ru.createextentreport(testcaseName, new Object() {
			}.getClass().getEnclosingMethod().getName());
			logs.info("Extent report working fine");
			logs.info("------------BeforeTest execution finished------------");
		} catch (SecurityException e) {
			ReportUtils.extentlogs.log(Status.INFO, e.getMessage());
		}
	}

	@BeforeClass
	public void setup() throws FileNotFoundException, IOException {
		logs.info("------------Action before Class------------");
		initialize();
		// Here we are initializing the property files and excel files
		openBrowser();
		// Here we are opening the browser
		openUrl();
		// This method will open the url
		logs.info("------------BeforeClass execution finished------------");
	}
	
	@BeforeMethod
	public void checkTestSkip() {
		logs.info("------------Running before method------------");
		System.out.println(utilsTest.isTestCaseRunnable(excel, testcaseName));
		if(!utilsTest.isTestCaseRunnable(excel, testcaseName)) {
			closeBrowser(driver);
			throw new SkipException("Skipped due to testcase run mode set to no");
		}
	}
	
	@AfterMethod
	public void reportTest() {
		logs.info("------------Running after method------------");
		if(!isTestPass) {
			utilsTest.reportDAtaSetResult( Excel_reder.getCellRowNum("TCID", testcaseName), "Fail");
		}
		else {
			utilsTest.reportDAtaSetResult( Excel_reder.getCellRowNum("TCID", testcaseName), "Pass");
			System.out.println("data written");
		}
		logs.info("------------AfterMethod execution finished------------");
	}
	
	
	 @AfterSuite
		public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == result.FAILURE) {
			String path = ScreenShot.getSreenshot(result.getMethod().getMethodName());	
			System.out.println(path);
				ReportUtils.extentlog.addScreenCaptureFromPath(path);
			}
			closeBrowser(driver);
				
			}
	
	

}
