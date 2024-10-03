package com.taras.article.services;

import com.taras.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    boolean isArticleExists(Article article);

    Article save(Article article);

    Optional<Article> findById(String id);

    List<Article> listArticls();

    void deleteArticleById(String id);
}
