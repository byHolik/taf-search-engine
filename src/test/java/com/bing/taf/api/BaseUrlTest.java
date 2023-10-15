package com.bing.taf.api;

import com.bing.taf.utils.Resources;
import com.bing.taf.utils.Util;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class BaseUrlTest {

    @DisplayName("Test home page")
    @Test
    public void testBaseUrl() throws IOException {
        HttpUriRequest request = new HttpGet("https://www.bing.com/");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }
    @Disabled
    @DisplayName("Test search")
    @Test
    public void testBaseSearch() throws IOException {
        String xPathLocator
                = "//*[@href='"
                + Resources.EXPECTED_LINK
                + "' and text()='"
                + Resources.EXPECTED_HEADER + "']";
        String URI
                = Resources.HOME_URL
                + "search?q="
                + Resources.SEARCH_TEXT;

        HttpUriRequest request = new HttpGet(URI);
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        String responseBody = EntityUtils.toString(response.getEntity());

        Element htmlElement = Util.getHtmlElementByXpath(responseBody, xPathLocator);

        Assertions.assertEquals(Resources.EXPECTED_LINK, htmlElement.attr("href"));
        Assertions.assertEquals(Resources.EXPECTED_HEADER, htmlElement.text());
    }
}
