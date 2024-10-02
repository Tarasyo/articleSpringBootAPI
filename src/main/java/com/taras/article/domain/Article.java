package com.taras.article.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Don't want to have leaks bettwen the layers so better to have classes in each of the layer if future changes will needed
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
