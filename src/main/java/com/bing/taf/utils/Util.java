package com.bing.taf.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Util {
    public static Element getHtmlElementByXpath(String responseBody, String xPathLocator) {
        Document document = Jsoup.parse(responseBody);
        Element bodyElement = document.body();
        return bodyElement.selectXpath(xPathLocator).first();
    }
}
