package com.taras.article;

import com.taras.article.domain.Article;
import com.taras.article.domain.ArticleEntity;

public final class TestData {

    private TestData(){
    }

    public static Article testArticle(){
        return Article.builder()
                .id("1234543")
                .description("Spring Boot practice")
                .weight(2.5)
                .volume(1)
                .active(true)
                .build();
    }

    public static ArticleEntity testArticleEntity(){
        return ArticleEntity.builder()
                .id("1234543")
                .description("Spring Boot practice")
                .weight(2.5)
                .volume(1)
                .active(true)
                .build();
    }
}
