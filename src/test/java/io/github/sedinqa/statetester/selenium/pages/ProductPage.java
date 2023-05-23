package io.github.sedinqa.statetester.selenium.pages;

import io.github.sedinqa.statetester.selenium.WebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product/product&product_id={productId}")
public class ProductPage extends AbstractEcommerceBasePage {
    public String productId;

    @FindBy(id = "input-name")
    WebElement nameTextBox;

    @FindBy(id = "input-review")
    WebElement reviewTextArea;

    @FindBy(id = "button-review")
    WebElement submitReviewButton;

    @FindBy(xpath = "//label[contains(@for,'rating-4')]")
    WebElement rating4;

    @FindBy(css = ".alert-success")
    WebElement successMessage;

    public ProductPage(WebDriver driver, String productId) {
        super(driver);
        this.productId=productId;
    }

    public ProductPage writeReview(String name, String review) {
        rating4.click();
        nameTextBox.sendKeys(name);
        reviewTextArea.sendKeys(review);
        submitReviewButton.click();
        WebDriverWait wait =new WebDriverWait(this.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return this;
    }
    public String getSuccessMessage(){
        return this.successMessage.getText().trim();
    }
}
