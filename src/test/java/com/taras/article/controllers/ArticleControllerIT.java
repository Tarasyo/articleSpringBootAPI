package com.taras.article.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taras.article.TestData;
import com.taras.article.domain.Article;
import com.taras.article.services.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//Added integration test so when we hit the endpoint that we actually get something created
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ArticleControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleService;

    @Test
    public void testThatArticleIsCreated() throws Exception {
        final Article article = TestData.testArticle();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String articleJson = objectMapper.writeValueAsString(article);

        mockMvc.perform(MockMvcRequestBuilders.put("/articls/" + article.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(articleJson))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(article.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(article.getDescription()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value(article.getWeight()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.volume").value(article.getVolume()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(article.isActive()));
    }

    @Test
    public void testThatArticleIsUpdated() throws Exception {
        final Article article = TestData.testArticle();
        articleService.save(article);

        article.setDescription("New Description");

        final ObjectMapper objectMapper = new ObjectMapper();
        final String articleJson = objectMapper.writeValueAsString(article);

        mockMvc.perform(MockMvcRequestBuilders.put("/articls/" + article.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(articleJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(article.getId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(article.getDescription()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value(article.getWeight()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.volume").value(article.getVolume()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(article.isActive()));
    }

    @Test
    public void testThatRetrieveArticleReturns404WhenArticleNotFound() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/articls/123123"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void testThatRetrieveArticleReturnsHttp200WhenArticleExists() throws Exception{
        final Article article = TestData.testArticle();
        articleService.save(article);

        mockMvc.perform(MockMvcRequestBuilders.get("/articls/" + article.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(article.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value(article.getWeight()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.volume").value(article.getVolume()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(article.isActive()));;

    }

    @Test
    public void testThatListArticlsReturnHttp200EmptyListWhenNoArticlsExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/articls"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    public void testThatListArticlsReturnHttp200EmptyListWhenArticlsExists() throws Exception {
        final Article article = TestData.testArticle();
        articleService.save(article);

        mockMvc.perform(MockMvcRequestBuilders.get("/articls"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(article.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description").value(article.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].weight").value(article.getWeight()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].volume").value(article.getVolume()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].active").value(article.isActive()));
    }
}
