package com.taras.article.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taras.article.TestData;
import com.taras.article.domain.Article;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//Added integration test so when we hit the create endpoint that we actually get something created
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ArticleControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testThatArticleIsCreated() throws Exception {
        final Article article = TestData.testArticle();
        final ObjectMapper objectMapper = new ObjectMapper();
        final String articleJson = objectMapper.writeValueAsString(article);

        mockMvc.perform(MockMvcRequestBuilders.put("/articles/" + article.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(articleJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(article.getId()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(article.getDescription()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.weight").value(article.getWeight()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.volume").value(article.getVolume()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(article.isActive()));
    }
}
