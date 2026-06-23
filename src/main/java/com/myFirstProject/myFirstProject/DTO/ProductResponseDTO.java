package com.myFirstProject.myFirstProject.DTO;

import com.myFirstProject.myFirstProject.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Products product;
    private Double averageRating;
    private Integer totalRatings;
}
