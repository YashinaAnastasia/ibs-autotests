package org.ibs.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemsPage {
    @FindBy(xpath = "//button[@data-toggle='modal']")
    private WebElement addButton;

    @FindBy(xpath = "//select[@id='type']")
    private WebElement itemType;

    @FindBy(xpath = "//select[@id='type']/option[@value='FRUIT']")
    private WebElement typeOption;

    @FindBy(xpath = "//input[@id='exotic']")
    private WebElement exoCheckbox;

    @FindBy(xpath = "//button[@data-toggle='modal']/../..//button[@id='save']")
    private WebElement saveButton;


}
