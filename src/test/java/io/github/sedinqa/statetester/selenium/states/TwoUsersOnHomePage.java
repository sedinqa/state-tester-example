package io.github.sedinqa.statetester.selenium.states;

import io.github.sedinqa.statetester.selenium.WebPage;
import io.github.sedinqa.statetester.selenium.pages.HomePage;
import io.github.sedinqa.statetester.test.Check;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        WebDriver driver=new ChromeDriver();
        return new TwoUsersOnHomePage(singleUserOnHomePage.homePage, WebPage.Navigator.navigate(HomePage::new,driver));
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
