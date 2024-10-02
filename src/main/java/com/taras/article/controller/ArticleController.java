package com.taras.article.controller;

import com.taras.article.domain.Article;
import com.taras.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService){
        this.articleService = articleService;
    }

    @PutMapping(path = "/articles/{id}")
    public ResponseEntity<Article> createArticle(@PathVariable final String id, @RequestBody final Article article){
            article.setId(id);
            final Article savedArticle = articleService.create(article);
            final ResponseEntity<Article> response = new ResponseEntity<Article>(savedArticle, HttpStatus.CREATED);
            return response;
    }
}
