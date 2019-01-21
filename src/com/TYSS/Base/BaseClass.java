package com.TYSS.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract 
class BaseClass {

	public static WebDriver driver = null;
	public static Properties objects;
	public static Properties config;
	public static Properties xlsProperties;
	public static Properties email;
	public static Logger logs = null;
	public static String testcaseName = null;

	public static void initialize() throws FileNotFoundException, IOException {
		try {
			objects = new Properties();
			objects.load(new FileInputStream(
					"C:\\Users\\TYSS\\Desktop\\Shiv\\Casinova\\src\\com\\TYSS\\Properties\\objects.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error while loading object property file");
		}

		try {
			config = new Properties();
			config.load(new FileInputStream(
					"C:\\Users\\TYSS\\Desktop\\Shiv\\Casinova\\src\\com\\TYSS\\Properties\\config.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error while loading config property file");
		}

		try {
			xlsProperties = new Properties();
			xlsProperties.load(new FileInputStream(
					"C:\\Users\\TYSS\\Desktop\\Shiv\\Casinova\\src\\com\\TYSS\\Properties\\xlsProperties.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error while loading xlsProperties property file");
			
		}

		try {
			email = new Properties();
			email.load(new FileInputStream(
					"C:\\Users\\TYSS\\Desktop\\Shiv\\Casinova\\src\\com\\TYSS\\Properties\\email.properties"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("error while loading error property file");
		}
	}

	public static WebElement getElement(WebDriver driver, String locator) {
		String[] objects = locator.split("-", 2);
		String locatortype = objects[0];
		String locatorvalue = objects[1];

		WebElement element = null;
		By by = null;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (locatortype.equals("className")) {
			try {
				by = By.className(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("cssSel")) {
			try {
				by = By.cssSelector(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("ide")) {
			try {
				by = By.id(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("name")) {
			try {
				by = By.name(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("tagName")) {
			try {
				by = By.tagName(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("linkText")) {
			try {
				by = By.linkText(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("PartialLinkText")) {
			try {
				by = By.partialLinkText(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("xpath")) {
			try {
				by = By.xpath(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;	
			}
		} else {
			System.out.println("element not identified with the given locator value " + locatorvalue);
		}

		if (driver.findElements(by).size() > 0) {
			element = driver.findElement(by);
		}
		return element;
	}
	
	public static List<WebElement> getElements(WebDriver driver, String locator) {
		String[] objects = locator.split("-", 2);
		String locatortype = objects[0];
		String locatorvalue = objects[1];

		List<WebElement> elements = null;
		By by = null;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (locatortype.equals("className")) {
			try {
				by = By.className(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("cssSel")) {
			try {
				by = By.cssSelector(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("ide")) {
			try {
				by = By.id(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("name")) {
			try {
				by = By.name(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("tagName")) {
			try {
				by = By.tagName(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("linkText")) {
			try {
				by = By.linkText(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("PartialLinkText")) {
			try {
				by = By.partialLinkText(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;
			}
		}

		else if (locatortype.equals("xpath")) {
			try {
				by = By.xpath(locatorvalue);
			} catch (Throwable e) {
				System.out.println("Element not found " + locatorvalue);
				return null;	
			}
		} else {
			System.out.println("element not identified with the given locator value " + locatorvalue);
		}

		if (driver.findElements(by).size() > 0) {
			elements = driver.findElements(by);
		}
		return elements;
	}
	
	public static void openBrowser() {
		System.out.println(config.get("browser"));
		if ((config.getProperty("browser")).equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if ((config.getProperty("browser")).equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if ((config.getProperty("browser")).equals("iedriver")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		}

	}

	public static void openUrl() {
		driver.get(config.getProperty("url"));
	}

	public static void closeBrowser(WebDriver driver) {
		driver.quit();
	}
	
	
}
