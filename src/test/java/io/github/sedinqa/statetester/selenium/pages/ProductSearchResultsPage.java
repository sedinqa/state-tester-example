package io.github.sedinqa.statetester.selenium.pages;

import io.github.sedinqa.statetester.selenium.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.UnsupportedEncodingException;
import java.util.List;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search={searchTerm}")
public class ProductSearchResultsPage extends AbstractEcommerceBasePage {
    public String searchTerm;
    @FindBy(css = ".product-layout")
    List<WebElement> products;

    @FindBy(css = ".compare-total")
    WebElement productCompare;

    public ProductSearchResultsPage(WebDriver driver, String searchTerm) {
        super(driver);
        this.searchTerm=searchTerm;
    }
    public ProductPage selectNthProduct(int index) throws UnsupportedEncodingException {
        WebElement product=products.get(index);
        String productId=this.getUrlValues(product.findElement(By.cssSelector("a.slide")).getAttribute("href")).get("product_id");
        product.click();
        return new ProductPage(this.driver,productId);
    }
    public void waitForPageLoad() {
        super.waitForPageLoad(productCompare);
    }
}
