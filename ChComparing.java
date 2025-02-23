package Week2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
// import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChComparing {
	public String BaseURL = "https://puppiizsunniiz.github.io/AN-EN-Tags/index.html";
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
		driver.findElement(By.xpath("/html/body/nav/div/ul[1]/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Comparing (WIP)")).click();
		
	}
	
	@Test(priority = 1)
	public void Input() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("addoperatorbtn")).click();
		Thread.sleep(2000);
		
		// Lấy tất cả các phần tử <a> trong <ul> có id là "classlist"
        List<WebElement> navItems = driver.findElements(By.cssSelector("#classlist .nav-item .nav-link"));
        
        // Kiểm tra nếu có ít nhất một phần tử trong danh sách
        if (navItems.size() > 0) {
            // Lấy một chỉ số ngẫu nhiên
            Random rand = new Random();
            int randomIndex = rand.nextInt(navItems.size());
            
            // Chọn tab ngẫu nhiên (class như "Caster", "Warrior", "Medic", "Sniper")
            WebElement randomNavItem = navItems.get(randomIndex);
            randomNavItem.click(); // Click vào tab đã chọn

            // Đợi 2 giây để phần tử tải (sử dụng Thread.sleep)
            try {
                Thread.sleep(2000); // Đợi 2 giây để phần tử tải xong
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Lấy tất cả các nhân vật trong class
            List<WebElement> characters = driver.findElements(By.cssSelector("#selectedopclass"));
            
            // Kiểm tra nếu có ít nhất một nhân vật
            if (characters.size() > 0) {
                // Chọn một nhân vật ngẫu nhiên
                int randomCharIndex = rand.nextInt(characters.size());
                WebElement randomCharacter = characters.get(randomCharIndex);
                
                // Click vào nhân vật đã chọn
                randomCharacter.click();

                // In ra tên nhân vật đã click (từ thuộc tính onclick)
                String characterOnClick = randomCharacter.getAttribute("onclick");
                System.out.println("Clicked on: " + characterOnClick);
            } else {
                System.out.println("Không tìm thấy nhân vật nào trong class đã chọn.");
            }
        } else {
            System.out.println("Không tìm thấy tab trong danh sách.");
        }
//        String randomClass2;
//     // Đảm bảo rằng class2 khác với class1
//        do {
//            randomClass2 = classNames.get(random.nextInt(classNames.size()));
//        } while (randomClass1.equals(randomClass2));
	}
}