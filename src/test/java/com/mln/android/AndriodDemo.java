package com.mln.android;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class AndriodDemo {
	AndroidDriver androidDriver;
	DesiredCapabilities desiredCapabilities;
	URL url;
  @Test
  public void f() throws InterruptedException {
	  
	  Thread.sleep(3000);
	WebElement expListPeopleNames = androidDriver.findElement(By.xpath ("//*[@text ='People Names']"));
	expListPeopleNames.click();
	
	List<WebElement> listofNames = androidDriver //
						.findElements(By.xpath("//*[@class ='android.widget.TextView']"));
	
	
	for(WebElement we : listofNames){
		if (!we.getText().equalsIgnoreCase("Dog Names")){
			System.out.println(we.getText());
		}else
		{
			break;
		}
		
	}
	
	//System.out.println(listofNames);
  
  }
  
  
  		
  
  
  @BeforeTest
  public void beforeTest() throws MalformedURLException {
	    desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities
		.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities
		.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		desiredCapabilities
		.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		desiredCapabilities
		.setCapability("appPackage", "io.appium.android.apis");
		desiredCapabilities
		.setCapability("appActivity", "io.appium.android.apis.view.ExpandableList1");


		url = new URL("http://localhost:4723/wd/hub/");
		androidDriver = new AndroidDriver(url,desiredCapabilities);

  }

  @AfterTest
  public void afterTest() {
	  androidDriver.quit();
  }

}
