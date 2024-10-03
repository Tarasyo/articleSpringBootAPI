package com.taras.article.controller;

import com.taras.article.domain.Article;
import com.taras.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(final ArticleService articleService){
        this.articleService = articleService;
    }

    @PutMapping(path = "/articls/{id}")
    public ResponseEntity<Article> createUpdateArticle(@PathVariable final String id, @RequestBody final Article article){
            article.setId(id);
            final boolean isArticleExists = articleService.isArticleExists(article);
            final Article savedArticle = articleService.save(article);

            if(isArticleExists){
                return new ResponseEntity<Article>(savedArticle, HttpStatus.OK);
            }else {
                return new ResponseEntity<Article>(savedArticle, HttpStatus.CREATED);
            }
    }

    @GetMapping(path = "/articls/{id}")
    public ResponseEntity<Article> retriveArticle(@PathVariable final String id){
        final Optional<Article> foundArticle = articleService.findById(id);
        return foundArticle.map(article -> new ResponseEntity<Article>(article, HttpStatus.OK))
                .orElse(new ResponseEntity<Article>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/articls")
    public ResponseEntity<List<Article>> listArticls() {
        return new ResponseEntity<List<Article>>(articleService.listArticls(), HttpStatus.OK);
    }
}
