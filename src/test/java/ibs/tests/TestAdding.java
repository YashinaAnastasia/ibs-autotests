package ibs.tests;


import ibs.basetestsclass.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.sql.SQLException;

public class TestAdding extends BaseTests {

    public ItemsPage page = new ItemsPage(driver, connection);

    @Test
    void testExoFruit(){
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Манго");
        page.selectOption("Фрукт");
        page.clickElement(By.id("exotic"));
        page.clickElement(By.id("save"));
        page.checkTable(By.xpath("//table"), "Манго", "Фрукт", "true");
    }

    @Test
    void testFruit(){
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Груша");
        page.selectOption("Фрукт");
        page.clickElement(By.id("save"));
        page.checkTable(By.xpath("//table"), "Груша", "Фрукт", "false");
    }
    @Test
    void testExoVegetable(){
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Артишок");
        page.selectOption("Овощ");
        page.clickElement(By.id("exotic"));
        page.clickElement(By.id("save"));
        page.checkTable(By.xpath("//table"), "Артишок", "Овощ", "true");
    }

    @Test
    void testVegetable(){
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Кабачок");
        page.selectOption("Овощ");
        page.clickElement(By.id("save"));
        page.checkTable(By.xpath("//table"), "Кабачок", "Овощ", "false");
    }

    @Test
    void addingNewItem() throws SQLException {
        if(page.searchExistingItemDB("Артишок", "VEGETABLE", 1, true)){
            Assertions.fail("Запись с такими тестовыми значениями уже существует");
        }
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Артишок");
        page.selectOption("Овощ");
        page.clickElement(By.id("exotic"));
        page.clickElement(By.id("save"));
        page.searchNewItemDB("Артишок", "VEGETABLE", 1);
    }

    @Test
    void addingExistingItem() throws SQLException {
        if(page.searchExistingItemDB("Груша", "FRUIT", 0, false)){
            page.addingItem("Груша", "FRUIT", 0);
        }
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Груша");
        page.selectOption("Фрукт");
        page.clickElement(By.id("save"));
        page.searchNewItemDB("Груша", "FRUIT", 0);
        page.countingItems("Груша", "FRUIT", 0,2);
    }
}
