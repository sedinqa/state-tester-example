package io.github.sedinqa.statetester.playwright.pages;

import io.github.sedinqa.statetester.playwright.WebPage;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/index.php?route=product/special")
public class SpecialPage extends AbstractEcommerceBasePage {
    static String header=".h3";
    public SpecialPage(PlaywrightContext playwrightContext) {
        super(playwrightContext);
    }
    public String getHeadingText() {
        return playwrightContext.page.locator(header).textContent().trim();
    }
}
