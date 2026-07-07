package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

    @Test(description = "user login and validate the count of products")
    public void verifyProductlist(){
        pages().getLoginPage().logIn(JsonReader.get("validCredentials.username"),JsonReader.get("validCredentials.password"));
        int noOfProducts = pages().getProductPage().getNoOfProducts();
        Assert.assertEquals(noOfProducts, Integer.parseInt(JsonReader.get("pageInformation.noOfProducts")));
    }
}
