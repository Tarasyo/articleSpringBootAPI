package com.taras.article.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Will be used by JPA working with DB
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    private String id;

    private String description;

    private double weight;

    private int volume;

    private boolean active;
}
