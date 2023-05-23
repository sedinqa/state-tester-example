package io.github.sedinqa.statetester.playwright.pages;


import io.github.sedinqa.statetester.playwright.WebPage;

@WebPage(url = "https://ecommerce-playground.lambdatest.io/")
public class HomePage extends AbstractEcommerceBasePage {
    public HomePage(PlaywrightContext playwrightContext) {
        super(playwrightContext);
    }
}
