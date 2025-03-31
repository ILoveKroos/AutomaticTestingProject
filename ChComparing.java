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
	}

	@Test(priority = 2)
	public void Char2() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("addoperatorbtn")).click();
		Thread.sleep(2000);

		List<WebElement> navItems = driver.findElements(By.cssSelector("#classlist .nav-item .nav-link"));

		// Lấy một class ngẫu nhiên
		Random rand = new Random();
		int randomIndex = rand.nextInt(navItems.size());
		WebElement randomNavItem = navItems.get(randomIndex);
		randomNavItem.click();

		Thread.sleep(1000);

		// Lấy tất cả các nhân vật trong class
		List<WebElement> characters = driver.findElements(By.cssSelector("#selectedopclass"));

		// Chọn một nhân vật ngẫu nhiên
		int randomCharIndex = rand.nextInt(characters.size());
		WebElement randomCharacter = characters.get(randomCharIndex);
		randomCharacter.click();
	}

	// Button Trust
	@Test(priority = 3)
	public void Trust() throws InterruptedException {
		WebElement TrustBar = driver
				.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[1]/div[1]/div/div[1]/label"));
		TrustBar.click();
		System.out.println("Đã click vào nút Trust");
		Thread.sleep(2000);
	}

	// Button Stats
	@Test(priority = 4)
	public void Stats() throws InterruptedException {
		WebElement Stats = driver
				.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div/div[1]/label"));
		Stats.click();
		System.out.println("Đã click vào nút Stats");
		Thread.sleep(2000);
	}

	// Elite Select
	@Test(priority = 5)
	public void Elite() throws InterruptedException {
		WebElement Elite = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div[1]/div[2]/ul/li[3]/a"));
		Elite.click();
		System.out.println("Đã click vào Elite 2");
		Thread.sleep(2000);
	}

	// Level
	@Test(priority = 6)
	public void Level() throws InterruptedException {
		WebElement slider = driver.findElement(By.id("levelSlider"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(slider, 0, 68).perform();
		System.out.println("Kéo slider level lên mức 68");
		Thread.sleep(2000);
	}

	@Test(priority = 7)
	public void getCharacterInfo() throws InterruptedException {
		Thread.sleep(2000); // Chờ trang load

		// Lấy danh sách nhân vật
		List<WebElement> characterElements = driver.findElements(By.cssSelector(".opname.ak-font-novecento.lp-row"));

		if (characterElements.isEmpty()) {
			System.out.println("Không có nhân vật nào xuất hiện trên trang");
			return;
		}

		System.out.println("Tìm thấy " + characterElements.size() + " nhân vật.");

		/// Duyệt tối đa 2 nhân vật, nhưng vẫn cố tình vượt giới hạn 1 đơn vị
		int numberOfCharactersToTest = Math.min(2, characterElements.size()) + 1;

		for (int i = 0; i < numberOfCharactersToTest; i++) {
		    getCharacterDetails(i);
		}
	}

	public void getCharacterDetails(int slot) throws InterruptedException {
		try {
			Thread.sleep(1000); // Chờ một chút để dữ liệu hiển thị

			// Lấy danh sách nhân vật trên trang
			List<WebElement> characterElements = driver
					.findElements(By.cssSelector(".opname.ak-font-novecento.lp-row"));

			if (slot >= characterElements.size()) {
				throw new Exception("Slot " + slot + " không tồn tại trên trang.");
			}

			// Lấy tên nhân vật
			WebElement characterNameElement = characterElements.get(slot);
			String characterName = characterNameElement.getText().trim();

			// Lấy thông tin Elite, Level, Max HP, Attack
			String elite = driver.findElement(By.id("slot-" + slot + "-elite")).getText().trim();
			String level = driver.findElement(By.id("slot-" + slot + "-level")).getText().trim();
			String maxHP = driver.findElement(By.id("slot-" + slot + "-maxHP")).getText().trim();
			String attack = driver.findElement(By.id("slot-" + slot + "-atk")).getText().trim();

			// Kiểm tra dữ liệu không rỗng
			List<String> errors = new ArrayList<>();
			if (characterName.isEmpty())
				errors.add("Tên nhân vật");
			if (elite.isEmpty())
				errors.add("Elite");
			if (level.isEmpty())
				errors.add("Level");
			if (maxHP.isEmpty())
				errors.add("Max HP");
			if (attack.isEmpty())
				errors.add("Attack");

			if (!errors.isEmpty()) {
				throw new Exception("Lỗi thiếu dữ liệu: " + String.join(", ", errors));
			}

			// Xuất thông tin nhân vật nếu hợp lệ
			System.out.println(characterName + " | Elite: " + elite + " | Level: " + level + " | Max HP: "
					+ maxHP + " | Attack: " + attack);

		} catch (Exception e) {
			System.out.println("Lỗi ở slot-" + slot + ": " + e.getMessage());
		}	
	}
	@AfterTest
	public void close() {
		driver.quit();
		System.out.println("Đóng trình duyệt");
	}
}