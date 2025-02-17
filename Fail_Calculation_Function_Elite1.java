package Week01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
// import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Fail_Calculation_Function_Elite1 {
	public String BaseURL = "https://aceship.github.io/AN-EN-Tags/index.html";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.out.println("Chạy trình duyệt Chrome");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(BaseURL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void Menu_Calculator() throws InterruptedException {
		driver.findElement(By.id("regionDropdown")).click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Leveling")).click();
	}

	@Test(priority = 1)
	public void Form_Fill() {
		// Rarity
		WebElement Rarity = driver.findElement(By.id("star"));
		Select dropdown = new Select(Rarity);
		dropdown.selectByVisibleText("6");

		// Current Elite
		WebElement C_Elite = driver.findElement(By.id("current-evolve"));
		Select dropdown_CElite = new Select(C_Elite);
		dropdown_CElite.selectByVisibleText("0");

		// Target Elite
		WebElement T_Elite = driver.findElement(By.id("target-evolve"));
		Select dropdown_TElite = new Select(T_Elite);
		dropdown_TElite.selectByVisibleText("2");

		// Current Level
		WebElement C_Level = driver.findElement(By.id("current-level"));
		C_Level.clear();
		C_Level.sendKeys("1");

		// Target Level
		WebElement T_Level = driver.findElement(By.id("target-level"));
		T_Level.clear();
		T_Level.sendKeys("91");

		// Current EXP
		WebElement Current_EXP = driver.findElement(By.id("current-exp"));
		Current_EXP.clear();
		Current_EXP.sendKeys("10");

		// Current Resource_L
		WebElement LMD = driver.findElement(By.id("gold-asset"));
		LMD.clear();
		LMD.sendKeys("0");

		// Current Resource_G
		WebElement Green = driver.findElement(By.id("book-basic"));
		Green.clear();
		Green.sendKeys("0");

		// Current Resource_B
		WebElement Blue = driver.findElement(By.id("book-primary"));
		Blue.clear();
		Blue.sendKeys("0");

		// Current Resource_Y
		WebElement Yellow = driver.findElement(By.id("book-middle"));
		Yellow.clear();
		Yellow.sendKeys("0");

		// Current Resource_Go
		WebElement Gold = driver.findElement(By.id("book-advanced"));
		Gold.clear();
		Gold.sendKeys("0");

		// Button Calculate
		WebElement btn_Cal = driver.findElement(By.id("btn-calculate"));
		btn_Cal.click();
	}

	@Test(priority = 2)
	public void ResultComparing() {
		// Lấy giá trị thực đã tính
		WebElement totalSan = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr[1]/td/b"));
		String Sanity = totalSan.getText();
		int actualValue_S = Integer.parseInt(Sanity);

		// Set giá trị mong đợi
		int expectedValue_S = 9810;

		// Hiển thị giá trị thực
		System.out.println("Sanity: " + actualValue_S);

		// So sánh giá trị thực và giá trị mong đợi
		Assert.assertEquals(actualValue_S, expectedValue_S);
	}
}