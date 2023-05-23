package io.github.sedinqa.statetester.selenium.states;

import io.github.sedinqa.statetester.selenium.WebPage;
import io.github.sedinqa.statetester.selenium.pages.ProductSearchResultsPage;
import io.github.sedinqa.statetester.test.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@State
public class OnProductSearchResultsPage {
    public ProductSearchResultsPage productSearchResultsPage;

    public OnProductSearchResultsPage(ProductSearchResultsPage productSearchResultsPage) {
        this.productSearchResultsPage = productSearchResultsPage;
    }

    @Launcher("Navigate to search results for search term {searchTerm}")
    public static OnProductSearchResultsPage navigateTOSearchResultsForSearchTerm(@Data("searchTerm") String searchTerm) {
        WebDriver driver=new ChromeDriver();
        ProductSearchResultsPage productSearchResults=WebPage.Navigator.navigate(webDriver -> new ProductSearchResultsPage(webDriver,searchTerm),driver);
        productSearchResults.waitForPageLoad();
        return new OnProductSearchResultsPage(productSearchResults);
    }
    @Launcher("Serach for {searchTerm} on Home page")
    public static OnProductSearchResultsPage searchForSearchTermOnHomePage(OnHomePage singleUserOnHomePage, @Data("searchTerm") String searchTerm) {
        ProductSearchResultsPage productSearchResults=singleUserOnHomePage.homePage.search(searchTerm);
        productSearchResults.waitForPageLoad();
        return new OnProductSearchResultsPage(productSearchResults);
    }
    @Teardown
    public void close(){
        this.productSearchResultsPage.close();
    }

    @DataProvider
    public static List<Map<String,Object>> dataProvider(){
        Map<String,Object> dataRow1=new HashMap<>();
        dataRow1.put("searchTerm","Palm");
        Map<String,Object> dataRow2=new HashMap<>();
        dataRow2.put("searchTerm","HTC");
        return List.of(dataRow1,dataRow2);
    }
}
