package Week03;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class characterEmpty {
	public String BaseURL = "https://puppiizsunniiz.github.io/AN-EN-Tags/akmatuses.html";
	public WebDriver driver;

	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(BaseURL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void selectMat() throws Exception {
		// Đọc dữ liệu từ file JSON
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("D:\\material.json"));
		JSONArray materialsArray = (JSONArray) jsonObject.get("materials");

		// Kiểm tra nếu file JSON rỗng
		if (materialsArray == null || materialsArray.isEmpty()) {
			throw new Exception("Danh sách vật liệu trống");
		}

		// Chuyển JSONArray thành List<String>
		List<String> materials = new ArrayList<>();
		for (Object obj : materialsArray) {
			materials.add(obj.toString().trim());
		}

		System.out.println("Vật liệu trong file JSON: " + materials);

		// Tìm tất cả các button trên trang
		List<WebElement> buttons = driver.findElements(By.cssSelector(".button-tag"));
		Assert.assertTrue(buttons.size() > 0, "Không tìm thấy button");

		int count = 0;
		List<String> notFoundMaterials = new ArrayList<>(); // Danh sách vật liệu không tìm thấy

		for (String material : materials) {
			boolean found = false;
			for (WebElement button : buttons) {
				String buttonTitle = button.getAttribute("data-original-title");

				// Kiểm tra nếu buttonTitle không bị null và khớp với material
				if (buttonTitle != null && buttonTitle.equalsIgnoreCase(material)) {
					System.out.println("Đã chọn: " + material);
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
					Thread.sleep(1000);
					count++;
					found = true;
					break;
				}
			}
			if (!found) {
				notFoundMaterials.add(material); // Ghi nhận vật liệu không tìm thấy
			}
		}

		// In cảnh báo nếu có vật liệu không tìm thấy
		if (!notFoundMaterials.isEmpty()) {
			System.out.println("Không tìm thấy các vật liệu: " + notFoundMaterials);
		}

		System.out.println("Tổng số vật liệu đã chọn: " + count);
	}

	@Test(priority = 1)
	public void selectRarity() throws InterruptedException {
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[9]/div[2]/button[3]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[9]/div[2]/button[4]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[9]/div[2]/button[5]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[9]/div[2]/button[6]")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[9]/div[2]/button[7]")).click();

		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void checkCharacters() throws Exception {
		// Đọc danh sách nhân vật từ JSON
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("D:\\characterEmpty.json"));
		JSONArray charactersArray = (JSONArray) jsonObject.get("characters");

		// Kiểm tra nếu file JSON rỗng
		if (charactersArray == null || charactersArray.isEmpty()) {
			System.out.println("Không có dữ liệu trong file nhân vật");
			return; // Dừng testcase
		}

		// Chuyển dữ liệu JSON thành Set<String>
		Set<String> expectedCharacters = new HashSet<>();
		for (Object obj : charactersArray) {
			expectedCharacters.add(obj.toString().trim());
		}

		// Lấy danh sách nhân vật trên trang
		Set<String> displayedCharacters = new HashSet<>();
		List<WebElement> characterButtons = driver.findElements(By.cssSelector(".btn-char"));
		for (WebElement button : characterButtons) {
			WebElement nameElement = button.findElement(By.xpath(".//div[1]"));
			String characterName = nameElement.getText().trim();
			displayedCharacters.add(characterName);
		}

		// So sánh
		Set<String> missingCharacters = new HashSet<>(expectedCharacters);
		missingCharacters.removeAll(displayedCharacters);

		System.out.println("Danh sách nhân vật trong file JSON: " + expectedCharacters);
		System.out.println("Nhân vật được hiển thị trên trang: " + displayedCharacters);

		if (missingCharacters.isEmpty()) {
			System.out.println("Tất cả nhân vật đều có trên trang");
		} else {
			System.out.println("Nhân vật bị thiếu: " + missingCharacters);
		}
	}

	@AfterTest
	public void close() {
		driver.quit();
		System.out.println("Đóng trình duyệt");
	}
}
