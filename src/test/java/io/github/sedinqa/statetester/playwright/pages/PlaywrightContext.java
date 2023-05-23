package io.github.sedinqa.statetester.playwright.pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightContext {
    Playwright playwright;
    Browser browser;
    public Page page;


    public PlaywrightContext(Playwright playwright, Browser browser, Page page) {
        this.playwright = playwright;
        this.browser = browser;
        this.page = page;
    }

    public PlaywrightContext newContextFromNewPage(){
        return new PlaywrightContext(playwright,browser, browser.newPage());
    }
}
