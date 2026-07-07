package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private By heading =By.cssSelector("[data-test='title']");
    private By productList = By.className("inventory_item_label");
    public ProductPage(WebDriver driver){
        super(driver);
    }

    public String getHeading() {
        return getText(heading);
    }
    public int getNoOfProducts(){
        return getElements(productList).size();
    }
}
