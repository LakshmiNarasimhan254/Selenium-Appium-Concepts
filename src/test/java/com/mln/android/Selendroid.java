package com.mln.android;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class Selendroid {

	AndroidDriver androidDriver;
	DesiredCapabilities desiredCapabilities;
	URL url;
	@Test
	public void testToastmsg() throws InterruptedException{
		Thread.sleep(3000);
		
		androidDriver.findElement(By.id("android:id/button1")).click();;

		WebElement btnToast= androidDriver.findElement(By.id("io.selendroid.testapp:id/showToastButton"));
		btnToast.click();		
		WebElement txtMsgToast =androidDriver.findElement(By.xpath("/hierarchy/android.widget.Toast"));
				System.out.println(txtMsgToast.getText());
	}

	@Test(dataProvider = "registrationData_exceldata", enabled =false)
	public void registerUser(String UserName,String Email,String Password,String Name ,String ProgramingLanguage,String Adds) throws InterruptedException {
		
		Thread.sleep(3000);
		
		androidDriver.findElement(By.id("android:id/button1")).click();;

		WebElement btnUserRegisteration = androidDriver.findElement(By.id("io.selendroid.testapp:id/startUserRegistration"));
		btnUserRegisteration.click();
		
		Thread.sleep(3000);

		WebElement txtbxUserName = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputUsername"));
		txtbxUserName.sendKeys(UserName);


		WebElement txtbxEmail = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputEmail"));	
		txtbxEmail.sendKeys(Email);

		WebElement txtbxPassword = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputPassword"));	
		txtbxPassword.sendKeys(Password);

		WebElement txtbxName = androidDriver.findElement(By.id("io.selendroid.testapp:id/inputName"));
		txtbxName.sendKeys(Name);

		WebElement selectProgramingLanguage = androidDriver.findElement(By
				.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage"));		
		selectProgramingLanguage.click();
		Thread.sleep(3000);

		WebElement rdbtnProgramingLanguage = androidDriver.findElement(By.xpath("//*[@text='"+ ProgramingLanguage+ "']"));
		rdbtnProgramingLanguage.click();
		
		Thread.sleep(3000);

		androidDriver.navigate().back();


		WebElement chkbxAdds = androidDriver.findElement(By
				.id("io.selendroid.testapp:id/input_adds"));

		if (Adds.equalsIgnoreCase("TRUE")){
			chkbxAdds.click();
		}
		
		

		WebElement btnRegisterUserVerify = androidDriver.findElement(By.id("io.selendroid.testapp:id/btnRegisterUser"));
		btnRegisterUserVerify.click();
		Thread.sleep(3000);

		if(verifyUserBeforeRegisteration( UserName, Email, Password, Name , ProgramingLanguage, Adds));{
			WebElement txtbtnRegisterUser = androidDriver.findElement(By.id("io.selendroid.testapp:id/buttonRegisterUser"));
			txtbtnRegisterUser.click();
		}



	}

	private boolean verifyUserBeforeRegisteration(String UserName,String Email,String Password,String Name ,String ProgramingLanguage,String Adds) {
		WebElement txtName = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_name_data"));
		WebElement txtUserName = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_username_data"));
		WebElement txtPassword = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_password_data"));
		WebElement txtEmail = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_email_data"));
		WebElement txtPrefferedProgramingLanguage = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_preferedProgrammingLanguage_data"));
		WebElement txtAcceptAdd = androidDriver.findElement(By.id("io.selendroid.testapp:id/label_acceptAdds_data"));


		boolean result = false;

		result= Name.equalsIgnoreCase(txtName.getText())? true:false;
		result= UserName.equalsIgnoreCase(txtUserName.getText())? true:false;
		result= Password.equalsIgnoreCase(txtPassword.getText())? true:false;
		result= Email.equalsIgnoreCase(txtEmail.getText())? true:false;
		result= ProgramingLanguage.equalsIgnoreCase(txtPrefferedProgramingLanguage.getText())? true:false;
		result= Adds.equalsIgnoreCase(txtAcceptAdd.getText())? true:false;

		return result;

	}

	@DataProvider(name = "registrationData_exceldata")
	private String[][] registrationData_exceldata() throws IOException{

		return gcellData();

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

	//Methods
	private String[][] gcellData() throws IOException{

		FileInputStream excelFile = new FileInputStream(System.getProperty("user.dir")+
				"/src/test/resources/Registration_Data.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(excelFile);
		XSSFSheet sheet = wb.getSheet("Data");
		int iRowCount=	sheet.getLastRowNum();
		int iColCount = sheet.getRow(iRowCount).getLastCellNum();

		Iterator<Row> rowIterator = sheet.rowIterator();		
		String[][] ExcelData = new String[iRowCount][iColCount];
		int iRowNo =0;
		while(rowIterator.hasNext()){
			Row RowValue = rowIterator.next();
			Iterator<Cell> cellIterator= RowValue.iterator();

			int iColNo =0;
			if (iRowNo >0){
				while(cellIterator.hasNext()){
					ExcelData[iRowNo-1][iColNo] = cellIterator.next().toString();				
					iColNo++;
				}
			}
			iRowNo++;
		}


		return ExcelData;

	}


}
