package com.example.howtimeflies.activity.ui.similarity;

import lombok.Data;

/**
 * 相似的人
 */
@Data
public class SimilarityPerson {
    private Long id;
    private String imageUrl;
    private Integer similarity;

    public SimilarityPerson(String imageUrl, Integer similarity) {
        this.imageUrl = imageUrl;
        this.similarity = similarity;
    }
}
