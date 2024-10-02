package com.taras.article.services;

import com.taras.article.domain.Article;

import java.util.Optional;

public interface ArticleService {

    Article create(Article article);

    Optional<Article> findArticle(String id);
}
