package com.bing.taf.po;

import com.bing.taf.utils.Driver;
import com.bing.taf.utils.Resources;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ResultPage {

    public ResultPage clickSearchButton() {
        String searchButtonId = "search_icon";
        new WebDriverWait(Driver.getWebDriver(), Duration.ofSeconds(Resources.SEC))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(searchButtonId)))
                .click();
        return this;
    }

    public ResultPage typeTextSearchInput() {
        String searchInputId = "sb_form_q";
        new WebDriverWait(Driver.getWebDriver(), Duration.ofSeconds(Resources.SEC))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(searchInputId)))
                .sendKeys(Resources.SEARCH_TEXT);
        return this;
    }

    public List<WebElement> getResultHeaderList() {
        String resultListXPath = "//*[@id='b_results']//h2/a";
        return new WebDriverWait(Driver.getWebDriver(), Duration.ofSeconds(Resources.SEC))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(resultListXPath)));
    }
}
