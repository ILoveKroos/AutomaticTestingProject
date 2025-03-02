package Week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChComparing {
	public String BaseURL = "https://puppiizsunniiz.github.io/AN-EN-Tags/index.html";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(BaseURL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void Menu_Calculator() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Comparing (WIP)")).click();

	}

	@Test(priority = 1)
	public void Char1() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("addoperatorbtn")).click();
		Thread.sleep(2000);

		// Lấy tất cả danh sách class
		List<WebElement> navItems = driver.findElements(By.cssSelector("#classlist .nav-item .nav-link"));

		// Lấy một class ngẫu nhiên
		Random rand = new Random();
		int randomIndex = rand.nextInt(navItems.size());
		WebElement randomNavItem = navItems.get(randomIndex);
		randomNavItem.click();

		Thread.sleep(2000);

		// Lấy tất cả các nhân vật trong class
		List<WebElement> characters = driver.findElements(By.cssSelector("#selectedopclass"));

		// Chọn một nhân vật ngẫu nhiên
		int randomCharIndex = rand.nextInt(characters.size());
		WebElement randomCharacter = characters.get(randomCharIndex);
		randomCharacter.click();

		// In ra tên nhân vật đã click
		String characterOnClick = randomCharacter.getAttribute("id");
		System.out.println("Nhân vật được chọn: " + characterOnClick);
	}

	@Test(priority = 2)
	public void Char2() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("addoperatorbtn")).click();
		Thread.sleep(2000);

		List<WebElement> navItems = driver.findElements(By.cssSelector("#classlist .nav-item .nav-link"));

		// Lấy một ngẫu nhiên
		Random rand = new Random();
		int randomIndex = rand.nextInt(navItems.size());

		// Chọn class ngẫu nhiên
		WebElement randomNavItem = navItems.get(randomIndex);
		randomNavItem.click();

		Thread.sleep(2000);

		// Lấy tất cả các nhân vật trong class
		List<WebElement> characters = driver.findElements(By.cssSelector("#selectedopclass"));

		// Chọn một nhân vật ngẫu nhiên
		int randomCharIndex = rand.nextInt(characters.size());
		WebElement randomCharacter = characters.get(randomCharIndex);

		// Click vào nhân vật đã chọn
		randomCharacter.click();

		// In ra tên nhân vật đã click
		String characterOnClick = randomCharacter.getAttribute("id");
		System.out.println("Nhân vật được chọn: " + characterOnClick);
	}
	// Button Trust
	@Test(priority = 3)
	public void Trust() throws InterruptedException {
		WebElement TrustBar = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[1]/div[1]/div/div[1]/label"));
		TrustBar.click();
		Thread.sleep(2000);
	}
	
	// Button Stats
	@Test(priority = 4)
	public void Stats() throws InterruptedException {
		WebElement Stats = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div/div[1]/label"));
		Stats.click();
		Thread.sleep(2000);
	}
	
	@Test(priority = 5)
	// Elite Select
	public void Elite() throws InterruptedException {
		WebElement Elite = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[2]/ul/li[3]/a"));
		Elite.click();
		Thread.sleep(2000);
	}
	
	// Level 
	@Test(priority = 6)
	public void Level() throws InterruptedException {
		WebElement slider = driver.findElement(By.id("levelSlider"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 0, 68).perform();
		Thread.sleep(2000);
	}
}
