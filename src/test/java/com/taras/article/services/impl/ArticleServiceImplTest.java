package com.taras.article.services.impl;

import com.taras.article.domain.Article;
import com.taras.article.domain.ArticleEntity;
import com.taras.article.repositories.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.taras.article.TestData.testArticle;
import static com.taras.article.TestData.testArticleEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


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

        final Article result = underTest.save(article);
        assertEquals(article, result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoArticle() {
        final String id = "123123";
        when(articleRepository.findById(eq(id))).thenReturn(Optional.empty());
        final Optional<Article> result = underTest.findById(id);
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testThatFindByIdReturnsArticleWhenExists(){
        final Article article = testArticle();
        final ArticleEntity articleEntity = testArticleEntity();

        when(articleRepository.findById(eq(article.getId()))).thenReturn(Optional.of(articleEntity));

        final Optional<Article> result = underTest.findById(article.getId());
        assertEquals(Optional.of(article), result);
    }

    @Test
    public void testListArticlsReturnsEmptyListWhenNoArticlsExist() {
        when(articleRepository.findAll()).thenReturn(new ArrayList<ArticleEntity>());
        final List<Article> result = underTest.listArticls();
        assertEquals(0, result.size());
    }

    @Test
    public void testListArticlsReturnsEmptyListWhenArticlsExist() {
        final ArticleEntity articleEntity = testArticleEntity();
        when(articleRepository.findAll()).thenReturn(List.of(articleEntity));
        final List<Article> result = underTest.listArticls();
        assertEquals(1, result.size());
    }

    @Test
    public void testArticleExistsReturnFalseWhenArticleDoesntExist() {
        when(articleRepository.existsById(any())).thenReturn(false);
        final boolean result = underTest.isArticleExists(testArticle());
        assertEquals(false, result);
    }

    @Test
    public void testArticleExistsReturnTrueWhenArticleDoesExist() {
        when(articleRepository.existsById(any())).thenReturn(true);
        final boolean result = underTest.isArticleExists(testArticle());
        assertEquals(true, result);
    }

    @Test
    public void testDeleteArticleDeletesArticle() {
        final String id = "123123";
        underTest.deleteArticleById(id);
        verify(articleRepository, times(1)).deleteById(id);
    }

}
