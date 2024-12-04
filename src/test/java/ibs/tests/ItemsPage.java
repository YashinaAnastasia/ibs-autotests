package ibs.tests;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

public class ItemsPage {

    private final WebDriver driver;
    private Connection connection;

    public ItemsPage(WebDriver driver, Connection connection){
        this.driver = driver;
        this.connection = connection;
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
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(path));

        WebElement table = driver.findElement(path);
        List<WebElement> rows = table.findElements(By.xpath("//tr"));

        boolean itemExists = false;

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() >= 3 &&
                    columns.get(0).getText().equals(name) &&
                    columns.get(1).getText().equals(type) &&
                    columns.get(2).getText().equals(exotic))
                {
                itemExists = true;
                break;
                }
        }
        if(itemExists){ System.out.println("Запись " + name + " с указанными параметрами найдена");}
        else {System.out.println("Запись " + name + " с указанными параметрами не найдена");}
        Assertions.assertTrue(itemExists, "Нет указанной записи в таблице");
    }
    public void searchNewItemDB(String name, String type, Integer exotic) throws SQLException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM FOOD WHERE FOOD_NAME='" + name + "'");
        resultSet.last();
        Assertions.assertEquals(
                name,
                resultSet.getString("FOOD_NAME"),
                "Значение поля 'Наименование' отличается");
        Assertions.assertEquals(
                type,
                resultSet.getString("FOOD_TYPE"),
                "Значение поля 'Тип' отличается");
        Assertions.assertEquals(
                exotic,
                resultSet.getInt("FOOD_EXOTIC"),
                "Значение поля 'Экзотический' отличается");
        System.out.println("Запись " + name + " с указанными параметрами найдена");
    }
    public boolean searchExistingItemDB(String name, String type, Integer exotic, Boolean exists) throws SQLException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM FOOD WHERE FOOD_NAME='" + name +
                        "' AND FOOD_TYPE='" + type +
                        "' AND FOOD_EXOTIC=" + exotic);
        boolean itemExists = false;
        while (resultSet.next()) {
            if(resultSet.getString("FOOD_NAME").equals(name) &&
                    resultSet.getString("FOOD_TYPE").equals(type) &&
                    resultSet.getInt("FOOD_EXOTIC")==exotic){
                itemExists=true;
            }
        }
        return itemExists == exists;
    }
    public void addingItem(String name, String type, Integer exotic) throws SQLException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
        Statement statement = connection.createStatement();
        ResultSet lastItem = statement.executeQuery("SELECT * FROM FOOD");
        lastItem.last();
        int idLast = lastItem.getInt("FOOD_ID");
        idLast++;
        String insert = "INSERT INTO FOOD VALUES (" + idLast + ",'" + name + "','" + type
                + "'," + exotic +")";
        statement.executeUpdate(insert);
        System.out.println("Добавлен товар с названием " + name);
    }
    public void countingItems(String name, String type, Integer exotic, Integer expectingCount ) throws SQLException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT * FROM FOOD WHERE FOOD_NAME='" + name +
                        "' AND FOOD_TYPE='" + type +
                        "' AND FOOD_EXOTIC=" + exotic);
        int count = 0;
        while (resultSet.next()) {
            if(resultSet.getString("FOOD_NAME").equals(name) &&
                    resultSet.getString("FOOD_TYPE").equals(type) &&
                    resultSet.getInt("FOOD_EXOTIC")==exotic){
                count++;
            }
        }
        Assertions.assertEquals(count, expectingCount, "Количество записей не соответсвует ожидаемому");
        System.out.println("Найдено " + expectingCount +" записи " + name + " с указанными параметрами");
    }
    public void deletingItem() throws SQLException {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));
        Statement statement = connection.createStatement();
        ResultSet lastItem = statement.executeQuery("SELECT * FROM FOOD");
        lastItem.last();
        String nameLast = lastItem.getString("FOOD_NAME");
        String insert = "DELETE FROM FOOD WHERE FOOD_NAME='"  + nameLast + "' ";
        statement.executeUpdate(insert);
    }

}
