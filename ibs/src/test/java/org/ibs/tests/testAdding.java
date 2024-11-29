package org.ibs.tests;

import org.ibs.basetestsclass.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class testAdding extends BaseTests {

    public void clickMethod(String path){
        driver.findElement(By.xpath(
                path)).click();
    }
    public void sendMethod(String path, String keys){
        driver.findElement(By.xpath(
                path)).sendKeys(keys);
    }

    @Test
    void testFruit(){

        clickMethod("//button[@data-toggle='modal']");
        sendMethod("//input[@id='name']","Груша");
        clickMethod("//select[@id='type']");
        clickMethod("//select[@id='type']/option[@value='FRUIT']");
        clickMethod("//input[@id='exotic']");
        clickMethod("//button[@data-toggle='modal']/../..//button[@id='save']");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement table = driver.findElement(By.xpath("//table"));

        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        boolean entryExists = false;

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() >= 3 &&
                    columns.get(0).getText().equals("Груша") &&
                    columns.get(1).getText().equals("Фрукт") &&
                    columns.get(2).getText().equals("true")) {
                entryExists = true;
                break;
            }
        }

        Assertions.assertTrue(entryExists, "Запись не была добавлена в таблицу");
    }
//    @Test
//    void testVegetable(){
//        WebDriver driver = new ChromeDriver();
//        System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
//
//        driver.manage().window().maximize();
//
//        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//
//        driver.get("http://localhost:8080/food");
//        WebElement addButton = driver.findElement(By.xpath(
//                "//button[@data-toggle='modal']"));
//
//        addButton.click();
//
//        WebElement modalTitle = driver.findElement(By.xpath(
//                "//h5[.='Список товаров']"));
//        Assertions.assertEquals(
//                "Список товаров", modalTitle.getText(),
//                "Не открылось модальное окно добваления товара");
//
//        WebElement nameInput = driver.findElement(By.xpath(
//                "//input[@id='name']"));
//        nameInput.sendKeys("Огурец");
//
//        WebElement typeInput = driver.findElement(By.xpath(
//                "//select[@id='type']"));
//        typeInput.click();
//
//        WebElement vegInput = driver.findElement(By.xpath(
//                "//select[@id='type']/option[@value='VEGETABLE']"));
//        vegInput.click();
//
//        WebElement exoCheckbox = driver.findElement(By.xpath(
//                "//input[@id='exotic']"));
//        exoCheckbox.click();
//
//        WebElement saveButton = addButton.findElement(By.xpath(
//                "//button[@data-toggle='modal']/../..//button[@id='save']"));
//
//        saveButton.click();
//        Assertions.assertEquals(
//                "Список товаров", modalTitle.getText(),
//                "Не открылось модальное окно добваления товара");
//    }
}
