package com.myFirstProject.myFirstProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponseDTO {

    private Long cartItemId;
    private Long productId;
    private String title;
    private String thumbnailImage;
    private Double price;
    private Integer quantity;
    private Double subtotal;
}
