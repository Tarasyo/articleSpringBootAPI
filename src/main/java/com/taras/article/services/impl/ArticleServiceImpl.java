package com.taras.article.services.impl;

import com.taras.article.domain.Article;
import com.taras.article.domain.ArticleEntity;
import com.taras.article.repositories.ArticleRepository;
import com.taras.article.services.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean isArticleExists(Article article) {
        return articleRepository.existsById(article.getId());
    }

    /*
        In service implemintetion we want to deal just with the article, not to leek the abstraction of entity
        */
    @Override
    public Article save(Article article) {
        final ArticleEntity articleEntity = articleToArticleEntity(article);
        final ArticleEntity savedArticleEntity = articleRepository.save(articleEntity);
        return articleEntityToArticle(savedArticleEntity);
    }

    @Override
    public Optional<Article> findById(String id) {
        final Optional<ArticleEntity> foundArticle = articleRepository.findById(id);
        return foundArticle.map(article -> articleEntityToArticle(article));
    }

    @Override
    public List<Article> listArticls() {
        final List<ArticleEntity> foundArticls = articleRepository.findAll();
        return foundArticls.stream().map(article -> articleEntityToArticle(article)).collect(Collectors.toList());
    }

    @Override
    public void deleteArticleById(String id) {
        try {
            articleRepository.deleteById(id);
        }catch (final EmptyResultDataAccessException ex){
            log.debug("Attemt to delete not existing article", ex);
        }
    }

    //    Where the article entity exists it will convert the article entity in to an article,
//    where it doesn't exist it will be optionally empty
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
