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
	public void Form_Fill() {
	    // Rarity
	    WebElement Rarity = driver.findElement(By.id("star"));
	    Select dropdown = new Select(Rarity);
	    dropdown.selectByVisibleText("6");
	    String rarity = dropdown.getFirstSelectedOption().getText();
	    System.out.println("Giá trị Rarity: " + rarity);

	    // Current Elite
	    WebElement C_Elite = driver.findElement(By.id("current-evolve"));
	    Select dropdown_CElite = new Select(C_Elite);
	    dropdown_CElite.selectByVisibleText("0");
	    String celite = dropdown_CElite.getFirstSelectedOption().getText();
	    System.out.println("Giá trị Current Elite: " + celite);

	    // Target Elite
	    WebElement T_Elite = driver.findElement(By.id("target-evolve"));
	    Select dropdown_TElite = new Select(T_Elite);
	    dropdown_TElite.selectByVisibleText("2");
	    String telite = dropdown_TElite.getFirstSelectedOption().getText();
	    System.out.println("Giá trị Target Elite: " + telite);

	    // Current Level
	    WebElement C_Level = driver.findElement(By.id("current-level"));
	    C_Level.clear();
	    C_Level.sendKeys("1");
	    String clevel = C_Level.getAttribute("value");
	    System.out.println("Giá trị Current Level: " + clevel);

	    // Target Level
	    WebElement T_Level = driver.findElement(By.id("target-level"));
	    T_Level.clear();
	    T_Level.sendKeys("90");
	    String tlevel = T_Level.getAttribute("value");
	    System.out.println("Giá trị Target Level: " + tlevel);

	    // Current EXP
	    WebElement Current_EXP = driver.findElement(By.id("current-exp"));
	    Current_EXP.clear();
	    Current_EXP.sendKeys("10");
	    String exp = Current_EXP.getAttribute("value");
	    System.out.println("Giá trị Current EXP: " + exp);

	    // Current Resource_L
	    WebElement LMD = driver.findElement(By.id("gold-asset"));
	    LMD.clear();
	    LMD.sendKeys("0");
	    String lmd = LMD.getAttribute("value");
	    System.out.println("Giá trị Current Resource_LMD: " + lmd);

	    // Current Resource_G (book-basic)
	    WebElement Green = driver.findElement(By.id("book-basic"));
	    Green.clear();
	    Green.sendKeys("0");
	    String green = Green.getAttribute("value");
	    System.out.println("Giá trị Current Resource_G: " + green);

	    // Current Resource_B (book-primary)
	    WebElement Blue = driver.findElement(By.id("book-primary"));
	    Blue.clear();
	    Blue.sendKeys("0");
	    String blue = Blue.getAttribute("value");
	    System.out.println("Giá trị Current Resource_B: " + blue);

	    // Current Resource_Y (book-middle)
	    WebElement Yellow = driver.findElement(By.id("book-middle"));
	    Yellow.clear();
	    Yellow.sendKeys("0");
	    String yellow = Yellow.getAttribute("value");
	    System.out.println("Giá trị Current Resource_Y: " + yellow);

	    // Current Resource_Go (book-advanced)
	    WebElement Gold = driver.findElement(By.id("book-advanced"));
	    Gold.clear();
	    Gold.sendKeys("0");
	    String gold = Gold.getAttribute("value");
	    System.out.println("Giá trị Current Resource_Go: " + gold);

	    // Button Calculate
	    WebElement btn_Cal = driver.findElement(By.id("btn-calculate"));
	    btn_Cal.click();
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
		int expectedValue_E = 1111390;
		int expectedValue_C = 1334793;

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

		if (actualValue_E == expectedValue_E) {
			System.out.println("Coin - Giá trị thực tế và giá trị mong đợi giống nhau");
		} else {
			System.out.println("Coin - Giá trị thực tế và giá trị mong đợi không giống nhau");
		}

		Assert.assertEquals(actualValue_S, expectedValue_S);
		Assert.assertEquals(actualValue_E, expectedValue_E);
		Assert.assertEquals(actualValue_C, expectedValue_C);
	}
}
