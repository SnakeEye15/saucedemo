package pages;

import org.openqa.selenium.support.PageFactory;
import base.BaseTest;
import Utils.WaitUtils;

public class BasePage {

    public BasePage() {
        // Automatically uses the driver belonging to the current thread
        PageFactory.initElements(BaseTest.getDriver(), this);
    }

    // Reusable wait method for all children pages
    protected WaitUtils waitOn() {
        return BaseTest.getWait();
    }
}