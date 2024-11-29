package org.ibs.basetestsclass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected static WebDriver driver = new ChromeDriver();
    @BeforeAll
    public static void beforeAll() {

        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/food");

    }

    @BeforeEach
    public void beforeEach() {

    }

    @AfterEach
    public void afterEach() {
        driver.findElement(By.xpath("//a[@id='navbarDropdown']")).click();
        driver.findElement(By.xpath("//a[@id='reset']")).click();
    }

    @AfterAll
   public static void afterAll(){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
