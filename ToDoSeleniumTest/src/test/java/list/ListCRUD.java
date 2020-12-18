package list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListCRUD {

	private static String URL = "http://localhost:9092/";
	private static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver",
				"src/test/resources/resource/chromedriver-87-4280/chromedriver.exe");
		ChromeOptions fOptions = new ChromeOptions();
		fOptions.setHeadless(false);
		driver = new ChromeDriver(fOptions);
		driver.manage().window().setSize(new Dimension(1366, 768));
	}

	@AfterClass
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void googleTitleTest() {
		driver.get(URL);
		assertEquals("Home", driver.getTitle());
	}

	// Checks the current driver URL
	@Test
	public void googleURLTest() {
		assertEquals("http://localhost:9092/", driver.getCurrentUrl());
	}

	// Creates 3 lists and asserts if 3 lists are present on the screen
	@Test
	public void createListTest() throws InterruptedException {
		driver.get(URL);
		// Locates the input box with id todoName
		WebElement input = driver.findElement(By.cssSelector("#todoName"));
		
		// Sends list names to input box
		input.sendKeys("Shopping");
		input.submit();
		
		input.clear();
		input.sendKeys("Work");
		input.submit();
		
		input.clear();
		input.sendKeys("Gym");
		input.submit();
		
		// Waits for the search result class to finish loading before attempting to locate
		new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.className("col-xl-4")));
		
		// Sets the row as result
		WebElement row = driver.findElement(By.className("row"));
		
		// Adds all elements with class name col-xl-4 to listResult
		List<WebElement> listResult = row.findElements(By.className("col-xl-4"));
		System.out.println(listResult.size());
		
		assertEquals(3, listResult.size());
	}
	
	// Deletes one list and asserts there is one less list on the screen
	@Test
	public void deleteListTest() throws InterruptedException {
		driver.get(URL);
		// Locates the btn with class deleteBtn
		WebElement btn = driver.findElement(By.className("deleteBtn"));
		
		btn.click();
		
		//accepts the alert that pops up
		driver.switchTo().alert().accept();
		
		// Sets the row as result
		WebElement row = driver.findElement(By.className("row"));
		
		// Adds all elements with class name col-xl-4 to listResult
		List<WebElement> listResult = row.findElements(By.className("col-xl-4"));
		System.out.println(listResult.size());
		
		assertEquals(2, listResult.size());
	
	}
}
