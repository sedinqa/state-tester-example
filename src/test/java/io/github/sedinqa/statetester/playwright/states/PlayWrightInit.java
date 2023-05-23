package io.github.sedinqa.statetester.playwright.states;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.github.sedinqa.statetester.playwright.pages.PlaywrightContext;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;

@State
public class PlayWrightInit {
    public PlaywrightContext playwrightContext;

    public PlayWrightInit(PlaywrightContext playwrightContext) {
        this.playwrightContext=playwrightContext;
    }

    @Launcher("Launch Browser and open new page")
    public static PlayWrightInit launchNewBrowserAndOpenPage(){
        Playwright playwright=Playwright.create();
        BrowserType.LaunchOptions launchOptions=new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        Browser browser=playwright.chromium().launch(launchOptions);
        Page page=browser.newPage();
        return new PlayWrightInit(new PlaywrightContext(playwright,browser,page));
    }
}
