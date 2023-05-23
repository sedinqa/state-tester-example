package io.github.sedinqa.statetester.playwright.states;


import io.github.sedinqa.statetester.playwright.WebPage;
import io.github.sedinqa.statetester.playwright.pages.HomePage;
import io.github.sedinqa.statetester.test.Check;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
import org.junit.jupiter.api.Assertions;

@State
public class OnHomePage {
    HomePage homePage;

    public OnHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    @Launcher("Navigate to home page")
    public static OnHomePage launch(PlayWrightInit playWrightInit){
        return new OnHomePage(WebPage.Navigator.navigate(page->new HomePage(playWrightInit.playwrightContext),playWrightInit.playwrightContext.page));
    }
    @Check("Check Title of Home Page")
    public void checkTitle(){
        Assertions.assertEquals("Your Store",homePage.getTitle());
    }

    @Teardown
    public void tearDown(){
        homePage.close();
    }
}
