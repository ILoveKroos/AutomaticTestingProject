import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class displayTesting {
	public String BaseURL = "https://puppiizsunniiz.github.io/AN-EN-Tags/index.html";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(BaseURL);
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void checkMainTabs() {
		List<WebElement> mainTabs = driver.findElements(By.xpath("//ul[@class='navbar-nav']/li[a[contains(@class,'nav-link')]]"));

		// In số lượng tab đã tìm
		System.out.println("Số tab tìm thấy: " + mainTabs.size());

		for (WebElement mainTab : mainTabs) {
			// Lấy và in ra
			String mainTabName = mainTab.getText().replace("\n", " ").trim();
			System.out.println("-" + mainTabName);
		}
	}

	@Test(priority = 2)
	public void checkCalculatorDropdown() throws InterruptedException {
		// Tìm Calculator Menu
		WebElement calculatorMenu = driver.findElement(By.xpath("//a[div[contains(text(),'Calculator')]]"));
		// Tìm dropdown
		WebElement dropdown = driver.findElement(By.xpath("//div[contains(@class,'dropdown-menu') and @aria-labelledby='navbarDropdown']"));

		// Dùng JavaScript để click
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", calculatorMenu);
		Thread.sleep(1000);
		Assert.assertTrue(dropdown.isDisplayed(), "Failed: Dropdown không mở");

		// Lấy danh sách trong droplist
		List<WebElement> subTabs = dropdown.findElements(By.xpath(".//a[contains(@class,'dropdown-item')]"));

		// In danh sách
		System.out.println("Passed: Danh sách chức năng trong Calculator Menu:");
		for (WebElement subTab : subTabs) {
			System.out.println("- " + subTab.getText());
		}

		// Kiểm tra số lượng
		Assert.assertEquals(subTabs.size(), 4, "Failed");
		Thread.sleep(1000);
	}

	@Test(priority = 3)
	public void checkInformationDropdown() throws InterruptedException {
		// Tìm Information Menu
		WebElement informationMenu = driver.findElement(By.xpath("//a[div[contains(text(),'Information')]]"));
		// Tìm dropdown
		WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"navbarNav\"]/ul[1]/li[3]/div"));
		
		// Dùng Action để click
		Actions actions = new Actions(driver);
		actions.moveToElement(informationMenu).click().perform();

		Thread.sleep(500);
		// Kiểm tra dropdown
		Assert.assertTrue(dropdown.isDisplayed(), "Failed: Dropdown chưa mở");

		// Lấy danh sách trong droplist
		List<WebElement> subTabs = driver.findElements(By.xpath(
				"//div[contains(@class,'dropdown-menu') and contains(@class,'show')]//a[contains(@class, 'dropdown-item')]"));

		System.out.println("Passed: Danh sách chức năng trong Information Menu");

		for (WebElement tab : subTabs) {
			System.out.println("- " + tab.getText());
		}

		// Kiểm tra số lượng
		Assert.assertEquals(subTabs.size(), 9, "Failed");
	}

	@Test(priority = 4)
	public void checkExtraDropdown() throws InterruptedException {
		// Tìm Extra Menu
		WebElement extraMenu = driver.findElement(By.xpath("//a[div[contains(text(),'Extra')]]"));

		// Dùng Actions Class để hover vào Extra Menu trước
		Actions actions = new Actions(driver);
		actions.moveToElement(extraMenu).perform();
		Thread.sleep(500);

		// Sau khi hover, click vào Extra Menu để mở dropdown
		actions.moveToElement(extraMenu).click().perform();
		Thread.sleep(1000);

		// Tìm dropdown menu
		WebElement dropdown = driver
				.findElement(By.xpath("//div[contains(@class,'dropdown-menu') and contains(@class,'show')]"));

		// Kiểm tra dropdown
		Assert.assertTrue(dropdown.isDisplayed(), "Failed: Dropdown chưa mở");

		// Lấy danh sách trong dropdown
		List<WebElement> subTabs = dropdown.findElements(By.xpath(".//a[contains(@class, 'dropdown-item')]"));

		System.out.println("Passed: Danh sách chức năng trong Extra Menu:");
		
		// In danh sách
		for (WebElement tab : subTabs) {
			System.out.println("- " + tab.getText());
		}

		// Kiểm tra số lượng
		Assert.assertEquals(subTabs.size(), 2, "Failed");
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
		System.out.println("Đóng trình duyệt");
	}
}