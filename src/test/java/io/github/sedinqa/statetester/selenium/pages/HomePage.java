package io.github.sedinqa.statetester.selenium.pages;

import io.github.sedinqa.statetester.selenium.WebPage;
import org.openqa.selenium.WebDriver;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/")
public class HomePage extends AbstractEcommerceBasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

}
