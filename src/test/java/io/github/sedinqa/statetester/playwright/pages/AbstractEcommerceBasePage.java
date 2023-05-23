package io.github.sedinqa.statetester.playwright.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class AbstractEcommerceBasePage {
    PlaywrightContext playwrightContext;

    public PlaywrightContext getPlaywrightContext() {
        return playwrightContext;
    }

    String searchTextBox = "input[name=\"search\"]";
    String searchButton = "button.type-text";

    String specialMenu = "//ul[contains(@class,'horizontal')]//*[ contains (text(), 'Special' ) ]";

    public AbstractEcommerceBasePage(PlaywrightContext playwrightContext) {
        this.playwrightContext = playwrightContext;
    }
    public void close(){
        this.playwrightContext.page.close();
    }
    public ProductSearchResultsPage search(String searchText){
        playwrightContext.page.type(searchTextBox,searchText);
        playwrightContext.page.click(searchButton);
        return new ProductSearchResultsPage(this.playwrightContext,searchText);
    }
    public SpecialPage navigateToSpecial(){
        playwrightContext.page.click(specialMenu);
        return new SpecialPage(this.playwrightContext);
    }
    public Map<String, String> getUrlValues(String url) throws UnsupportedEncodingException {
        int i = url.indexOf("?");
        Map<String, String> paramsMap = new HashMap<>();
        if (i > -1) {
            String searchURL = url.substring(url.indexOf("?") + 1);
            String params[] = searchURL.split("&");

            for (String param : params) {
                String temp[] = param.split("=");
                paramsMap.put(temp[0], java.net.URLDecoder.decode(temp[1], "UTF-8"));
            }
        }

        return paramsMap;
    }
    public void waitForPageLoad(String locator){
        this.playwrightContext.page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
    }

    public String getTitle(){
        return playwrightContext.page.title();
    }
}
