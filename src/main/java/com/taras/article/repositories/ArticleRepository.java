package com.taras.article.repositories;

import com.taras.article.domain.ArticleEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Abstraction to safe and retrive from DB which will be in memory DB, H2
@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
}
