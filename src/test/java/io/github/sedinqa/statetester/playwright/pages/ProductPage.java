package io.github.sedinqa.statetester.playwright.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.github.sedinqa.statetester.playwright.WebPage;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product/product&product_id={productId}")
public class ProductPage extends AbstractEcommerceBasePage {
    public String productId;

    static String nameTextBox="#input-name";

    static String reviewTextArea= "#input-review";

    static String submitReviewButton= "#button-review";

    static String rating4 = "//label[contains(@for,'rating-4')]";

    static String successMessage= ".alert-success";

    public ProductPage(PlaywrightContext playwrightContext,String productId) {
        super(playwrightContext);
        this.productId=productId;
    }
    public ProductPage writeReview(String name, String review) {
        playwrightContext.page.click(rating4);
        playwrightContext.page.type(nameTextBox,name);
        playwrightContext.page.type(reviewTextArea,review);
        playwrightContext.page.click(submitReviewButton);
        playwrightContext.page.waitForSelector(successMessage, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        return this;
    }
    public String getSuccessMessage(){
        return playwrightContext.page.locator(successMessage).textContent().trim();
    }
}
