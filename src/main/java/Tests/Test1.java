package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1 {
	public static WebDriver  driver;	
	@BeforeClass
	public void DriverSetup() {
			//code to invoke driver
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
		driver =new EdgeDriver();
		driver.get("https://www.tripadvisor.in/");
		
		}
	@Test
	public void test1() {
		
	}

	@AfterClass
	public void CloseDriver()
	{
		driver.close();
		driver.quit();
	}
}
