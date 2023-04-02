package Tests;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Test2 {
	public static WebDriver  driver;	
	@BeforeClass
	public void DriverSetup() {
			//code to invoke driver
		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
		driver =new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tripadvisor.in/VacationRentals-g294207-Reviews-Nairobi-Vacation_Rentals.html");
		
		}
	
	
	//To check whether user is able to select check-in and check-out dates from input calnedar.
	@Test
	public void test4() throws InterruptedException {
		//to select check-in and check-out dates
		WebElement enterdate=driver.findElement(By.xpath("//button[contains(@aria-label,'Enter the date range')]"));
		if (enterdate.isEnabled())
		{
			System.out.println("date selection is enabled");
			enterdate.click();
			Thread.sleep(3000);
			LocalDate tomorrow=LocalDate.now().plusDays(1);
			LocalDate After5days=tomorrow.plusDays(5);
	        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("d").appendPattern(" MMMM yyyy").toFormatter();
			WebElement check_in_date=driver.findElement(By.xpath("//div[@aria-label='"+formatter.format(tomorrow)+"']"));
			check_in_date.click();
			
			WebElement check_out_date=driver.findElement(By.xpath("//div[@aria-label='"+formatter.format(After5days) +"']"));
			
			check_out_date.click();
			System.out.println("check-in and check-out dates selected");
			
			Thread.sleep(3000);
			
			
			
			
			
		}
		else
		{
			System.out.println("date selection is not enabled");
		}
		 
		
		
		}
	
	
	
	
	//To check User is not able to select past date as Check-out date after selecting check-in date
	@Test
	public void test5() throws InterruptedException {
		
		//to select check-in and check-out dates
				WebElement enterdate=driver.findElement(By.xpath("//button[contains(@aria-label,'Enter the date range')]"));
				if (enterdate.isEnabled())
				{
					System.out.println("date selection is enabled");
					enterdate.click();
					Thread.sleep(3000);
					LocalDate tomorrow=LocalDate.now().plusDays(1);
					LocalDate future_day_for_check_in=tomorrow.plusDays(10);
					//check_out day less then check in day
					LocalDate future_day_for_check_out=tomorrow.plusDays(3);
			        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("d").appendPattern(" MMMM yyyy").toFormatter();
					WebElement check_in_date=driver.findElement(By.xpath("//div[@aria-label='"+formatter.format(future_day_for_check_in)+"']"));
					check_in_date.click();
					
					WebElement check_out_date=driver.findElement(By.xpath("//div[@aria-label='"+formatter.format(future_day_for_check_out) +"']"));
					
					check_out_date.click();
					if (check_in_date.isSelected()) {
					System.out.println("check-in and check-out dates both got selected this is a bug");
					}
					else {
						System.out.println("only one date is selected ");
					}
					Thread.sleep(3000);
					
					
					
					
					
				}
				else
				{
					System.out.println("date selection is not enabled");
				}
				 
				
				
				}
		
		
	

	
	
	
	
	//To check whether  user is able to select "Traveller rating" filter from "Tripadvisor sort" drop-down.
	@Test
	public void test6() throws InterruptedException
	{
		
	WebElement sort=driver.findElement(By.xpath("//button[@aria-label='Tripadvisor Sort: Tripadvisor Sort']"));
	if (sort.isEnabled())
	{
		System.out.println("trip advisor sort is enabled");
		sort.click();
		WebElement Trating=driver.findElement(By.xpath("//*[@id=\"menu-item-TRAVELERRATINGHIGH\"]"));
		Trating.click();
		System.out.println("Sort by Traveller rating is selected");
		Thread.sleep(3000);
			
	}
	else
	{
		System.out.println("Tripadvisor sort  is not enabled");
	}
	 
	
	
		
	}
	
	
	
	
	//To Check Whether user is able to Select the  required number of guests
	@Test
	public void test7() throws InterruptedException 
	{
		
		WebElement guestselector=driver.findElement(By.xpath("//*[@id=\"component_2\"]/div/div[3]/div/div/div[1]/div/div[3]/div/button"));
		if (guestselector.isEnabled())
		{
			System.out.println("Guests selection button   is  enabled");
			
			guestselector.click();
			
			String n_guests=driver.findElement(By.xpath("//div[text()='guests']/parent::div/following::div/div/span")).getText();
			int n=Integer.parseInt(n_guests);
			System.out.print(n);
			
 
	         if(n>4)
	         {
	        	 int d=n-4;
	        	 WebElement decrease=driver.findElement(By.xpath("//div[text()='guests']/parent::div/following::div/button[@title='decrease']"));
	        	 for (int i = 0;i<d;i++)
	        	 {
	        		 decrease.click();
	        	 }
	        	 
	        	 
	        	 
	         }
	         else if(n<4)
	         {
	        	 int d=4-n;
	        	 WebElement increase=driver.findElement(By.xpath("//div[text()='guests']/parent::div/following::div/button[@title='increase']"));
	        	 for(int i=0;i<d;i++)
	        	 {
	        		 increase.click();
	        	 }
	         }
	         WebElement applyGuests=driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[12]/div/div/div/div[2]/div/button"));
	         applyGuests.click();
	       
	         System.out.println("Guests number is set to 4");
	         
	         Thread.sleep(3000);
	        	
			
		}
		else
		{
			System.out.println("guests selection button   is  not enabled");
		}
	}
	
   @AfterMethod
	public void TakeScreenshot() throws IOException
	{
	    // Capture a screenshot
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    // Create a timestamped filename for the screenshot
	    LocalDateTime now = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	    String filename = System.getProperty("user.dir")+"\\screenshots\\screenshot-" + formatter.format(now) + ".png";

	    // Save the screenshot to a file
	    File dest = new File(filename);
	    org.apache.commons.io.FileUtils.copyFile(screenshot, dest);
	    System.out.print("done");
	}
	
	
	@AfterClass
	public void CloseDriver()
	{
		driver.close();
		driver.quit();
	}
}
