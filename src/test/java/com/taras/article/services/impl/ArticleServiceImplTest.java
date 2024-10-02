package com.taras.article.services.impl;

import com.taras.article.domain.Article;
import com.taras.article.domain.ArticleEntity;
import com.taras.article.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.taras.article.TestData.testArticle;
import static com.taras.article.TestData.testArticleEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl underTest;

    @Test
    public void testThatArticleIsSaved() {
        final Article article = testArticle();

        final ArticleEntity articleEntity = testArticleEntity();

        when(articleRepository.save(eq(articleEntity))).thenReturn(articleEntity);

        final Article result = underTest.create(article);
        assertEquals(article, result);
    }
}
