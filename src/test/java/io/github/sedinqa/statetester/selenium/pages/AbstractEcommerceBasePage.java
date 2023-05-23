package io.github.sedinqa.statetester.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEcommerceBasePage {
    WebDriver driver;

    @FindBy(xpath = "//ul[contains(@class,'horizontal')]//*[ contains (text(), 'Special' ) ]")
    WebElement specialMenu;

    @FindBy(css = "input[name=\"search\"]")
    WebElement searchTextBox;

    @FindBy(css = "button.type-text")
    WebElement searchButton;

    public AbstractEcommerceBasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }
    public ProductSearchResultsPage search(String searchText){
        searchTextBox.sendKeys(searchText);
        searchButton.click();
        return new ProductSearchResultsPage(this.driver,searchText);
    }
    public SpecialPage navigateToSpecial(){
        specialMenu.click();
        return new SpecialPage(this.driver);
    }
    public String getTitle(){
        return driver.getTitle();
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
    public void waitForPageLoad(WebElement element){
        WebDriverWait wait =new WebDriverWait(this.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void close(){
        this.driver.quit();
    }
}
