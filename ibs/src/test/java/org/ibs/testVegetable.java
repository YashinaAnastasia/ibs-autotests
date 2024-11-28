package org.ibs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class testVegetable {
    @Test
    void test(){
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");

        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        driver.get("http://localhost:8080/food");
        WebElement addButton = driver.findElement(By.xpath(
                "//button[@data-toggle='modal']"));

        addButton.click();
        WebElement modalTitle = driver.findElement(By.xpath(
                "//h5[.='Список товаров']"));
        Assertions.assertEquals(
                "Список товаров", modalTitle.getText(),
                "Не открылось модальное окно добваления товара");

        WebElement saveButton = addButton.findElement(By.xpath(
                "//button[@data-toggle='modal']/../..//button[@id='save']"));

        saveButton.click();




    }

}
