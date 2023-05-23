package io.github.sedinqa.statetester.selenium.states;

import io.github.sedinqa.statetester.selenium.WebPage;
import io.github.sedinqa.statetester.selenium.pages.SpecialPage;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@State
public class OnSpecialWebPagePage {
    SpecialPage specialPage;

    public OnSpecialWebPagePage(SpecialPage specialPage) {
        this.specialPage = specialPage;
    }

    @Launcher("Navigate to Special page by launching chrome")
    public static OnSpecialWebPagePage navigateToSpecialPageByLaunchingChrome() {
        WebDriver driver=new ChromeDriver();
        return new OnSpecialWebPagePage(WebPage.Navigator.navigate(SpecialPage::new,driver));
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
