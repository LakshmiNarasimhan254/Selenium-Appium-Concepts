package com.mln.android;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Calculator {

	AndroidDriver androidDriver;
	DesiredCapabilities desiredCapabilities;
	URL url;

	@BeforeTest
	public void OpenCalc() throws MalformedURLException {
		desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "11");
		desiredCapabilities.setCapability("udid", "emulator-5554");
		// desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
		// desiredCapabilities.setCapability("appActivity", "com.google.android.calculator");
		desiredCapabilities.setCapability(MobileCapabilityType.APP,System.getProperty("user.dir")+"/Calculator-app.apk");
		url = new URL("http://localhost:4723/wd/hub/");
		androidDriver = new AndroidDriver(url,desiredCapabilities);

	}

	@Test
	public void getSum(){
		int iNum1=1;
		int iNum2=2;
		WebElement  firstNum = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"" + iNum1 + "\"]"));
		WebElement  secondNum = androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"" + iNum2 + "\"]"));
		WebElement  plusSign	= androidDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"plus\"]"));
		WebElement  outputValue = androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"No result\"]"));

		firstNum.click();
		plusSign.click();
		secondNum.click();
		System.out.println(outputValue.getText());
	}
	
	
	@AfterTest
	public void closeCalc(){
		androidDriver.quit();
	}
}
