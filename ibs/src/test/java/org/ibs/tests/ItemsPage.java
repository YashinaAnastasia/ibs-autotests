package org.ibs.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ItemsPage {

    private final WebDriver driver;

    public ItemsPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickElement(By path){
        driver.findElement(path).click();
    }
    public void selectOption(String option){
        driver.findElement(By.id("type")).click();
        new Select(driver.findElement(By.id("type"))).selectByVisibleText(option);
    }
    public void sendKeys(By path, String keys){
        driver.findElement(path).sendKeys(keys);
    }
    public void checkTable(By path, String name, String type, String exotic){
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(path));

        WebElement table = driver.findElement(path);
        List<WebElement> rows = table.findElements(By.xpath("//tr"));

        boolean itemExists = false;

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() >= 3 &&
                    columns.get(0).getText().equals(name) &&
                    columns.get(1).getText().equals(type) &&
                    columns.get(2).getText().equals(exotic)) {
                itemExists = true;
                System.out.println("Запись " + name + " с указанными параметрами найдена");
                break;
            }
        }
        Assertions.assertTrue(itemExists, "Нет указанной записи в таблице");
    }

}
