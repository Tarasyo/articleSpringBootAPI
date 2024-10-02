package com.taras.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

    private String id;

    private String description;

    private double weight;

    private int volume;

    private boolean active;
}
