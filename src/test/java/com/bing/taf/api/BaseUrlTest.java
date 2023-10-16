package com.bing.taf.api;

import com.bing.taf.utils.Resources;
import com.bing.taf.utils.Util;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BaseUrlTest {

    @DisplayName("Test home page")
    @Test
    public void testBaseUrl() throws IOException {
        HttpUriRequest request = new HttpGet("https://www.bing.com/");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
    }

    @DisplayName("Test search")
    @Test
    public void testBaseSearch() throws IOException {
        String xPathLocator
                = "//*[@id='b_results']/li[1]/div[4]/h2/a";
        String URI
                = Resources.HOME_URL
                + "search?q="
                + Resources.SEARCH_TEXT;

        HttpUriRequest request = new HttpGet(URI);
        request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7");
        request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        Assertions.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
        String responseBody = EntityUtils.toString(response.getEntity());

        System.out.println(URI);
        System.out.println(responseBody);

        Element htmlElement = Jsoup.parse(responseBody).body().selectXpath(xPathLocator).first();

        Assertions.assertEquals(Resources.EXPECTED_LINK, htmlElement.attr("href"));
        Assertions.assertEquals(Resources.EXPECTED_HEADER, htmlElement.text());
    }
}
