package com.bing.taf.po;

import com.bing.taf.utils.Driver;
import com.bing.taf.utils.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    public void clickAcceptCookiesButton() {
        String acceptCookiesButtonId = "bnp_btn_accept";
        new WebDriverWait(Driver.getWebDriver(), Duration.ofSeconds(Resources.SEC))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(acceptCookiesButtonId)))
                .click();
    }
}
