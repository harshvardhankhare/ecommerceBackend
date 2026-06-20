package com.myFirstProject.myFirstProject.DTO;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String title;
    private String description;
    private String thumbnailImage;

    private String category;

    private Double price;
    private Double discountPercentage;

    private Integer stock;

    private String brand;
    private String sku;

    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private String returnPolicy;

    private String material;
    private String careInstructions;

    // JSON String
    private String tags;

    // JSON String
    private String imageUrls;

    // JSON String
    private String keyFeatures;
}