package Week01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Standard_Calculation_Function {
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
		driver.findElement(By.id("regionDropdown")).click();
		Thread.sleep(2000);

		driver.findElement(By.linkText("Leveling")).click();
	}

	@Test(priority = 1)
	public void Form_Fill() throws InterruptedException {
	    // Chọn Rarity
	    selectDropdown("star", "6");

	    // Chọn Current Elite
	    selectDropdown("current-evolve", "0");

	    // Chọn Target Elite
	    selectDropdown("target-evolve", "2");

	    // Nhập giá trị sai để tạo lỗi
	    setInputValue("current-level", "1");
	    setInputValue("target-level", "90");

	    // Nhấn Calculate
	    driver.findElement(By.id("btn-calculate")).click();

	    // Kiểm tra phần tử lỗi
	    WebElement errorInfo = driver.findElement(By.id("error-info"));
	    if (errorInfo.isDisplayed()) {
	        System.out.println("Lỗi: " + errorInfo.getText());
	    } else {
	        System.out.println("Không xảy ra lỗi");
	    }
	}

	// Hàm kiểm tra và nhập giá trị vào input
	private void setInputValue(String id, String value) {
	    WebElement input = driver.findElement(By.id(id));
	    input.clear();
	    input.sendKeys(value);
	    System.out.println("Nhập thành công: " + id + " = " + value);
	}

	// Hàm chọn giá trị từ dropdown
	private void selectDropdown(String id, String value) {
	    WebElement dropdown = driver.findElement(By.id(id));
	    Select select = new Select(dropdown);
	    select.selectByVisibleText(value);
	    System.out.println("Chọn thành công: " + id + " = " + value);
	}


	@Test(priority = 2)
	public void ResultComparing() {
		// Lấy giá trị thực đã tính
		WebElement totalSan = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[2]/table/tbody/tr[1]/td/b"));
		WebElement totalExp = driver.findElement(By.xpath("//*[@id=\"tbody-result\"]/tr[2]/td/b"));
		WebElement totalCoin = driver.findElement(By.xpath("//*[@id=\"tbody-result\"]/tr[4]/td/b"));

		String Sanity = totalSan.getText();
		String Exp = totalExp.getText();
		String Coin = totalCoin.getText();

		int actualValue_S = Integer.parseInt(Sanity);
		int actualValue_E = Integer.parseInt(Exp);
		int actualValue_C = Integer.parseInt(Coin);

		// Set giá trị mong đợi
		int expectedValue_S = 9810;
		int expectedValue_E = 1111400;
		int expectedValue_C = 1334796;

		// Hiển thị giá trị thực
		System.out.println("Giá trị Sanity thực tế : " + actualValue_S);
		System.out.println("Giá trị Sanity mong đợi: " + expectedValue_S);

		System.out.println("Giá trị Exp thực tế : " + actualValue_E);
		System.out.println("Giá trị Exp mong đợi: " + expectedValue_E);

		System.out.println("Giá trị Coin thực tế : " + actualValue_C);
		System.out.println("Giá trị Coin mong đợi: " + expectedValue_C);

		// Kiểm tra nếu giá trị thực và mong đợi trùng nhau
		if (actualValue_S == expectedValue_S) {
			System.out.println("Sanity - Giá trị thực tế và giá trị mong đợi giống nhau");
		} else {
			System.out.println("Sanity - Giá trị thực tế và giá trị mong đợi không giống nhau");
		}

		if (actualValue_E == expectedValue_E) {
			System.out.println("Exp - Giá trị thực tế và giá trị mong đợi giống nhau");
		} else {
			System.out.println("Exp - Giá trị thực tế và giá trị mong đợi không giống nhau");
		}

		if (actualValue_C == expectedValue_C) {
			System.out.println("Coin - Giá trị thực tế và giá trị mong đợi giống nhau");
		} else {
			System.out.println("Coin - Giá trị thực tế và giá trị mong đợi không giống nhau");
		}

		Assert.assertEquals(actualValue_S, expectedValue_S);
		Assert.assertEquals(actualValue_E, expectedValue_E);
		Assert.assertEquals(actualValue_C, expectedValue_C);
	}
	@AfterTest
	public void close() {
		driver.quit();
		System.out.println("Đóng trình duyệt");
	}
}