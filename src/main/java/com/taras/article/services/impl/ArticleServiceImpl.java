package com.taras.article.services.impl;

import com.taras.article.domain.Article;
import com.taras.article.domain.ArticleEntity;
import com.taras.article.repositories.ArticleRepository;
import com.taras.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /*
    In service implemintetion we want to deal just with the article, not to leek the abstraction of entity
    */
    @Override
    public Article create(Article article) {
        final ArticleEntity articleEntity = articleToArticleEntity(article);
        final ArticleEntity savedArticleEntity = articleRepository.save(articleEntity);
        return articleEntityToArticle(savedArticleEntity);
    }

    private ArticleEntity articleToArticleEntity(Article article) {
        return ArticleEntity.builder()
                .id(article.getId())
                .description(article.getDescription())
                .weight(article.getWeight())
                .volume(article.getVolume())
                .active(article.isActive())
                .build();
    }

    private Article articleEntityToArticle(ArticleEntity articleEntity){
        return Article.builder()
                .id(articleEntity.getId())
                .description(articleEntity.getDescription())
                .weight(articleEntity.getWeight())
                .volume(articleEntity.getVolume())
                .active(articleEntity.isActive())
                .build();
    }

}
