package org.ibs.basetestsclass;

import org.ibs.tests.ItemsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected static WebDriver driver = new ChromeDriver();
    public ItemsPage page = new ItemsPage(driver);

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
        System.out.println("Запущен драйвер");
    }

    @BeforeEach
    public void beforeEach() {
        page.clickElement(By.id("navbarDropdown"));
        page.clickElement(By.linkText("Товары"));
        System.out.println("Перешли на вкладку Товары");
    }

    @AfterEach
    public void afterEach() {
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.id("reset")).click();
        System.out.println("Созданная запись о товаре удалена");
    }

    @AfterAll
   public static void afterAll(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        System.out.println("Выход из драйвера");
    }
}
