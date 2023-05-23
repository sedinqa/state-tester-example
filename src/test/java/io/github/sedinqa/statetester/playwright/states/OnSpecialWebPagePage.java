package io.github.sedinqa.statetester.playwright.states;

import io.github.sedinqa.statetester.playwright.WebPage;
import io.github.sedinqa.statetester.playwright.pages.SpecialPage;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
@State
public class OnSpecialWebPagePage {
    SpecialPage specialPage;

    public OnSpecialWebPagePage(SpecialPage specialPage) {
        this.specialPage = specialPage;
    }

    @Launcher("Navigate to Special page by launching chrome")
    public static OnSpecialWebPagePage navigateToSpecialPageByLaunchingChrome(PlayWrightInit playWrightInit) {
        return new OnSpecialWebPagePage(WebPage.Navigator.navigate((page)->new SpecialPage(playWrightInit.playwrightContext),playWrightInit.playwrightContext.page));
    }

    @Launcher("Navigate to Special page from Home page")
    public static OnSpecialWebPagePage navigateToSpecialPageFromHomePage(OnHomePage singleUserOnHomePage) {
        return new OnSpecialWebPagePage(singleUserOnHomePage.homePage.navigateToSpecial());
    }
    @Teardown
    public void close(){
        this.specialPage.close();
    }
}
