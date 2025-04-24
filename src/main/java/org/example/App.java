package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {

        try(PrintWriter writer = new PrintWriter(new FileWriter("books.csv"))) {
            Document doc = Jsoup.connect("http://books.toscrape.com")
                    .userAgent("Mozilla/5.0")
                    .timeout(5000) // 5 sec
                    .get();

            Elements books = doc.select(".product_pod");

            writer.println("Title, Price, Link");

            for (Element book : books) {
                String title = book.select("h3 a").attr("title");
                String domain = "http://books.toscrape.com/";
                String relative = book.select("h3 a").attr("href");
                String link = domain + relative;
                String price = book.select(".price_color").text();

                writer.printf("%s, %s, %s\n", title, price, link);
            }

            System.out.println("Data saved to books.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
