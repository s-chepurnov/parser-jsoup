package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class App {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        try {
            Document doc = Jsoup.connect("http://books.toscrape.com").get();
            Elements elements = doc.select(".product_pod h3 a");

            for (Element a : elements) {
                System.out.println(a.attr("title"));
                System.out.println("http://books.toscrape.com/" + a.attr("href"));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
