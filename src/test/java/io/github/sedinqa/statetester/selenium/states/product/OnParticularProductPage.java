package io.github.sedinqa.statetester.selenium.states.product;

import io.github.sedinqa.statetester.selenium.WebPage;
import io.github.sedinqa.statetester.selenium.pages.ProductPage;
import io.github.sedinqa.statetester.selenium.states.OnProductSearchResultsPage;
import io.github.sedinqa.statetester.test.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@State
public class OnParticularProductPage {
    ProductPage productPage;

    public OnParticularProductPage(ProductPage productPage) {
        this.productPage = productPage;
    }

    @Launcher("Navigate to product with id {productId} by launching chrome")
    public static OnParticularProductPage navigateToProductWithId(@Data("productId") String productId) {
        WebDriver driver=new ChromeDriver();
        return new OnParticularProductPage(WebPage.Navigator.navigate((webDriver)->new ProductPage(webDriver,productId),driver));
    }

    @Launcher("Navigate to {n}th product from Search Results")
    public static OnParticularProductPage navigateToNthProductFromSearchResults(OnProductSearchResultsPage singleUserOnProductSearchResultsPage, @Data("n") int n) throws UnsupportedEncodingException {
        return new OnParticularProductPage(singleUserOnProductSearchResultsPage.productSearchResultsPage.selectNthProduct(n));
    }
    @Teardown
    public void close(){
        this.productPage.close();
    }
    @DataProvider
    public static List<Map<String,Object>> dataProvider(){
        Map<String,Object> dataRow=new HashMap<>();
        dataRow.put("productId","28");
        dataRow.put("n",0);
        return List.of(dataRow);
    }
}
