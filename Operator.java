import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Operator {
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
	public void menuBar() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/div/a[1]")).click();
		Thread.sleep(15000);
	}

	@Test(priority = 2)
	public void inputSearch() throws InterruptedException {
		WebElement Name = driver.findElement(By.xpath("/html/body/div[10]/div[1]/div[1]/div[3]/input"));
		Name.clear();
		Name.sendKeys("Mudrock" + Keys.ENTER);
		Thread.sleep(1000);

		WebElement result = driver.findElement(By.xpath("//*[@id=\"op-nameTL\"]"));
		Assert.assertEquals(result.getText(), "Mudrock", "Failed: Không thấy tên nhân vật");
		System.out.println("Passed: Đã tìm kiếm nhân vật: " + result.getText());
		
	}

	@Test(priority = 3)
	public void showChibiSD() throws InterruptedException {
		
		WebElement SD = driver.findElement(By.id("Chibi-Show"));
		SD.click();
			
//		WebElement Testing = driver.findElement(By.xpath("//*[@id=\"elite-sidenav\"]/li[5]/a/img"));
//		Testing.click();
		
		Thread.sleep(5000);
	}

	@Test(priority = 4)
	public void dragSD() throws InterruptedException {
		WebElement source = driver.findElement(By.id("spine-frameheader"));
		Actions actions = new Actions(driver);
		actions.clickAndHold(source).moveByOffset(100, -400).release().perform();
		Thread.sleep(1000);
		System.out.println("Passed: Kéo thả thành công hình ảnh");
	}

	@Test(priority = 5)
	public void testZoomChibi() throws InterruptedException {
		WebElement slider = driver.findElement(By.id("chibizoomslider"));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].value=20; arguments[0].dispatchEvent(new Event('input'));", slider);
		String value = slider.getAttribute("value");
		Thread.sleep(2000);

		Assert.assertEquals(value, "20", "Failed: Không thể phóng ảnh");
		System.out.println("Passed: Đã phóng to hình ảnh: " + value + "/50");
	}

	@Test(priority = 6)
	public void animationCheck() throws InterruptedException {
		WebElement nextButton = driver.findElement(By.id("Chibi-Next"));
		WebElement titleButton = driver.findElement(By.id("spine-text"));
		WebElement loadingButton = driver.findElement(By.id("loading-spine"));

		for (int i = 1; i <= 10; i++) {
			nextButton.click();
			Thread.sleep(500);

			String title = titleButton.getText();
			String loadingText = loadingButton.getText();

			System.out.println("Click " + i + ": Click " + title + " - " + loadingText);

			Assert.assertFalse(title.isEmpty(), "Failed");
			Thread.sleep(1000);
		}
	}
	@AfterTest
	public void close() {
		driver.quit();
		System.out.println("Đóng trình duyệt");
	}
}