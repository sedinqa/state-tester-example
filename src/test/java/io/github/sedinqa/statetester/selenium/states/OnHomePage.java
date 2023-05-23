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
public class OnHomePage {
    HomePage homePage;

    public OnHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    @Launcher("Navigate to home page by launching chrome")
    public static OnHomePage navigateTOHomePageByLaunchingChrome(){
        WebDriver driver=new ChromeDriver();
        return new OnHomePage(WebPage.Navigator.navigate(HomePage::new,driver));
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
