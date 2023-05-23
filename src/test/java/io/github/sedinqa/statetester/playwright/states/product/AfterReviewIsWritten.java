package io.github.sedinqa.statetester.playwright.states.product;

import io.github.sedinqa.statetester.playwright.pages.ProductPage;
import io.github.sedinqa.statetester.test.Check;
import io.github.sedinqa.statetester.test.Launcher;
import io.github.sedinqa.statetester.test.State;
import io.github.sedinqa.statetester.test.Teardown;
import org.junit.jupiter.api.Assertions;

@State
public class AfterReviewIsWritten {
    ProductPage productPage;

    public AfterReviewIsWritten(ProductPage productPage) {
        this.productPage = productPage;
    }

    @Launcher("User Writes review for current product")
    public static AfterReviewIsWritten userWritesReviewForCurrentProduct(OnParticularProductPage onParticularProduct) {
        return new AfterReviewIsWritten(onParticularProduct.productPage.writeReview("Atmaram","This is my Frank Review for the Systems"));
    }
    @Check("Check if Review was submitted")
    public void checkIfReviewWasSuccessful(){
        Assertions.assertEquals("Thank you for your review. It has been submitted to the webmaster for approval.",productPage.getSuccessMessage());
    }
    @Teardown
    public void close(){
        this.productPage.close();
    }
}
