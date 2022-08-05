package com.mln.android;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class Selendroid {

	AndroidDriver androidDriver;
	DesiredCapabilities desiredCapabilities;
	URL url;


	@Test
	public void registerUser() throws InterruptedException {


		Thread.sleep(3000);

		androidDriver.findElement(By.id("android:id/button1")).click();;
		Thread.sleep(3000);

		WebElement btnUserRegisteration = androidDriver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration"));
		btnUserRegisteration.click();
		Thread.sleep(3000);

		WebElement txtbxUserName = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputUsername"));
		txtbxUserName.sendKeys("UserName");
		
		
		WebElement txtbxEmail = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputEmail"));	
		txtbxEmail.sendKeys("UserName@Email.com");
		
		WebElement txtbxPassword = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputPassword"));	
		txtbxPassword.sendKeys("Password");
		
		WebElement txtbxName = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputName"));
		txtbxName.sendKeys("Name");
		
		WebElement selectProgramingLanguage = androidDriver.findElement(By
				.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage"));		
		selectProgramingLanguage.click();
		
		//androidDriver.navigate().back();
		
		Thread.sleep(3000);
		
		WebElement rdbtnProgramingLanguage = androidDriver.findElement(By.xpath("//*[@text='Java']"));
		rdbtnProgramingLanguage.click();
		
		androidDriver.navigate().back();
		

		WebElement chkbxAdds = androidDriver.findElement(By
				.id("io.selendroid.testapp:id/input_adds"));
		chkbxAdds.click();

		WebElement btnRegisterUserVerify = androidDriver.findElement(By.id("io.selendroid.testapp:id/btnRegisterUser"));
		btnRegisterUserVerify.click();

		Thread.sleep(3000);
		
		WebElement txtName = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_name_data"));
		WebElement txtUserName = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_username_data"));
		WebElement txtPassword = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_password_data"));
		WebElement txtEmail = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_email_data"));
		WebElement txtPrefferedProgramingLanguage = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_preferedProgrammingLanguage_data"));
		WebElement txtAcceptAdd = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_acceptAdds_data"));
		WebElement txtbtnRegisterUser = androidDriver.findElement(By.id("io.selendroid.testapp:id/buttonRegisterUser"));


		System.out.println(txtName.getText());
		System.out.println(txtUserName.getText());
		System.out.println(txtPassword.getText());
		System.out.println(txtEmail.getText());
		System.out.println(txtPrefferedProgramingLanguage.getText());
		System.out.println(txtAcceptAdd.getText());


		txtbtnRegisterUser.click();



	}
	@BeforeTest
	public void beforeTest() throws MalformedURLException, InterruptedException {
		desiredCapabilities = new DesiredCapabilities();

		desiredCapabilities
		.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities
		.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		desiredCapabilities
		.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		desiredCapabilities
		.setCapability("appPackage", "io.selendroid.testapp");
		desiredCapabilities
		.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");


		url = new URL("http://localhost:4723/wd/hub/");
		androidDriver = new AndroidDriver(url,desiredCapabilities);

	}

	@AfterTest
	public void afterTest() {
		androidDriver.quit();
	}

}
