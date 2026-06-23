package com.myFirstProject.myFirstProject.DTO;

import lombok.Data;

@Data
public class RatingRequest {
    private Long productId;
    private Integer rating;
    private String description;
}
