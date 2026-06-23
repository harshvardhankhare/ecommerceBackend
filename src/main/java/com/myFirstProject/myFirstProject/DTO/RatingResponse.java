package com.myFirstProject.myFirstProject.DTO;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RatingResponse {
    private Long id;
    private Integer rating;
    private String description;

    private Long userId;
    private String userName;

    private Long productId;

    private LocalDateTime createdAt;
}
