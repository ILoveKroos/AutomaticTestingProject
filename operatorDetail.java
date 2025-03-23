import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Operator {
	public String BaseURL = "https://puppiizsunniiz.github.io/AN-EN-Tags/index.html";
	String driverPath = "C:\\chromedriver.exe";

	public WebDriver driver;
	public WebDriverWait wait;

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10); // Không có Duration.ofSeconds()
		wait.until(
				driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(BaseURL);
		driver.manage().window().maximize();
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void menuBar() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/div/a[1]")).click();
		Thread.sleep(15000);
	}

	@Test(priority = 2)
	public void input() throws InterruptedException {
		// textBox search
		WebElement Name = driver.findElement(By.xpath("/html/body/div[10]/div[1]/div[1]/div[3]/input"));
		Name.click();
		// Clear data
		Name.clear();
		// Nhập dữ liệu
		Name.sendKeys("Mudrock" + Keys.ENTER);
		Thread.sleep(1000);
	}

	@Test(priority = 3)
	public void menuSD() throws InterruptedException {
		WebElement SD = driver.findElement(By.xpath("//*[@id=\"Chibi-Show\"]"));
		SD.click();
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void dragSD() {
		WebElement source = driver.findElement(By.id("spine-frameheader"));

		Actions actions = new Actions(driver);
		actions.clickAndHold(source).moveByOffset(100, -400).release().build().perform();

		System.out.println("Kéo thả thành công hình ảnh");
	}

	@Test(priority = 5)
	public void animationCheck() {

	}

	@Test(priority = 6)
	public void loadFail() {

	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}
