package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Util {
    public static String getNowStr() {
        LocalDateTime now = LocalDateTime.now();
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return formatedNow;
    }

    public static Article fu1(List<Article> articles, int id) {
        Article foundArticle = null;

        for (Article article : Main.articles) {
            if (article.getId() == id) {
                foundArticle = article;
                break;
            }
        }
        return foundArticle;
    }
}

