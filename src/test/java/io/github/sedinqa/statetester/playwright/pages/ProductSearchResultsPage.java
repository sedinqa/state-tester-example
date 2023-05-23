package io.github.sedinqa.statetester.playwright.pages;

import com.microsoft.playwright.Locator;
import io.github.sedinqa.statetester.playwright.WebPage;

import java.io.UnsupportedEncodingException;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product%2Fsearch&search={searchTerm}")
public class ProductSearchResultsPage extends AbstractEcommerceBasePage {
    public String searchTerm;
    static String products=".product-layout";

    static String productCompare=".compare-total";
    public ProductSearchResultsPage(PlaywrightContext playwrightContext,String searchTerm) {
        super(playwrightContext);
        this.searchTerm=searchTerm;
    }
    public ProductPage selectNthProduct(int index) throws UnsupportedEncodingException {
        Locator product=playwrightContext.page.locator(products).nth(index);
        String productId=this.getUrlValues(product.locator("a.slide").getAttribute("href")).get("product_id");
        product.click();
        return new ProductPage(this.playwrightContext,productId);
    }
    public void waitForPageLoad() {
        super.waitForPageLoad(productCompare);
    }
}
