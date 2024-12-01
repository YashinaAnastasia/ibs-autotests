package org.ibs.tests;

import org.ibs.basetestsclass.BaseTests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.sql.SQLException;

public class TestAdding extends BaseTests {

    public ItemsPage page = new ItemsPage(driver);

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
    void addingNew() throws SQLException {
        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
        page.sendKeys(By.id("name"),"Кабачок");
        page.selectOption("Овощ");
        page.clickElement(By.id("save"));
        page.checkDB("SELECT * FROM FOOD WHERE FOOD_NAME='Груша'", "Кабачок", "Овощ", 0);
    }
//    @Test
//    void addingExisting(){
//        page.clickElement(By.xpath("//button[@data-toggle='modal']"));
//        page.sendKeys(By.id("name"),"Кабачок");
//        page.selectOption("Овощ");
//        page.clickElement(By.id("save"));
//        page.checkTable(By.xpath("//table"), "Кабачок", "Овощ", "false");
//    }
}
