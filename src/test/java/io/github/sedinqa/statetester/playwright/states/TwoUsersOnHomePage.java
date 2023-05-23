package io.github.sedinqa.statetester.playwright.states;

import io.github.sedinqa.statetester.playwright.WebPage;
import io.github.sedinqa.statetester.playwright.pages.HomePage;
import io.github.sedinqa.statetester.playwright.pages.PlaywrightContext;
import io.github.sedinqa.statetester.test.Check;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
import org.junit.jupiter.api.Assertions;

@State
public class TwoUsersOnHomePage {
    HomePage firstUserHomePage;
    HomePage secondUserHomePage;

    public TwoUsersOnHomePage(HomePage firstUserHomePage, HomePage secondUserHomePage) {
        this.firstUserHomePage = firstUserHomePage;
        this.secondUserHomePage = secondUserHomePage;
    }

    @Launcher("Launch chrome and navigate to Home page for additional user")
    public static TwoUsersOnHomePage launchChromeAndNavigateToHomepageForAdditionalUser(OnHomePage singleUserOnHomePage){
        PlaywrightContext newContext=singleUserOnHomePage.homePage.getPlaywrightContext().newContextFromNewPage();
        return new TwoUsersOnHomePage(singleUserOnHomePage.homePage, WebPage.Navigator.navigate(page->new HomePage(newContext),newContext.page));
    }
    @Teardown
    public void tearDown(){
        firstUserHomePage.close();
        secondUserHomePage.close();
    }
    @Check("Check Title of Home Page for Second User")
    public void checkTitle(){
        Assertions.assertEquals("Your Store",secondUserHomePage.getTitle());
    }
    @Teardown
    public void close(){
        this.firstUserHomePage.close();
        this.secondUserHomePage.close();
    }
}
