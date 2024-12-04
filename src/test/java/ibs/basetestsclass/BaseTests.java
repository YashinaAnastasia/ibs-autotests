package ibs.basetestsclass;

import ibs.tests.ItemsPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class BaseTests {

    protected static WebDriver driver = new ChromeDriver();
    protected static Connection connection;
    public ItemsPage page = new ItemsPage(driver, connection);


    @BeforeAll
    public static void beforeAll() throws SQLException {
        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/");
        System.out.println("Запущен драйвер");
        connection = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user",
                "pass");
    }

    @BeforeEach
    public void beforeEach() {
        page.clickElement(By.id("navbarDropdown"));
        page.clickElement(By.linkText("Товары"));
        System.out.println("Перешли на вкладку Товары");
    }

    @AfterEach
    public void afterEach() throws SQLException {
        page.deletingItem();
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.id("reset")).click();
        System.out.println("Созданная запись о товаре удалена");
    }

    @AfterAll
   public static void afterAll() throws SQLException {
        connection.close();
        if(connection.isClosed()){
            System.out.println("Отключение от БД");
        };
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Выход из драйвера");
        }
    }
}
