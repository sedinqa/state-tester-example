package io.github.sedinqa.statetester.selenium.pages;

import io.github.sedinqa.statetester.selenium.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product/special")
public class SpecialPage extends AbstractEcommerceBasePage {

    @FindBy(css = ".h3")
    WebElement header;

    public SpecialPage(WebDriver driver) {
        super(driver);
    }

    public String getHeadingText() {
        return header.getText().trim();
    }
}
