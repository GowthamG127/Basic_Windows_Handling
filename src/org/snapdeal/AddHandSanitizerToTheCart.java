package org.snapdeal;

// Search Hand Sanitizer using windows handling and takes screenshot of that cart page.
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddHandSanitizerToTheCart {
	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='Search products & brands']")).sendKeys("Hand Sanitizer");
		driver.findElement(By.xpath("//button[@class='searchformButton col-xs-4 rippleGrey']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//p[contains(text(),' Hand Sanitizer ')])[3]")).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> childWindows = driver.getWindowHandles();
		for (String secondary : childWindows) {
			if (!secondary.equals(mainWindow)) {
				driver.switchTo().window(secondary);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//span[contains(text(),'to cart')]")).click();
				TakesScreenshot screenshot = (TakesScreenshot) driver;
				File image = screenshot.getScreenshotAs(OutputType.FILE);
				File file = new File(
						"/home/g/eclipse-workspace/WindowsHandellingAndScreenshot/Screenshots/Add-To-cart-screenshot.jpeg");
				FileUtils.copyFile(image, file);
			}
		}
	}
}