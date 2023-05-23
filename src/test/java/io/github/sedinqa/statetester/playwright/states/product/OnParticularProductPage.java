package io.github.sedinqa.statetester.playwright.states.product;

import io.github.sedinqa.statetester.playwright.WebPage;
import io.github.sedinqa.statetester.playwright.pages.ProductPage;
import io.github.sedinqa.statetester.playwright.states.OnProductSearchResultsPage;
import io.github.sedinqa.statetester.playwright.states.PlayWrightInit;
import io.github.sedinqa.statetester.test.*;

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
    public static OnParticularProductPage navigateToProductWithId(PlayWrightInit playWrightInit, @Data("productId") String productId) {
        return new OnParticularProductPage(WebPage.Navigator.navigate((page)->new ProductPage(playWrightInit.playwrightContext,productId),playWrightInit.playwrightContext.page));
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

