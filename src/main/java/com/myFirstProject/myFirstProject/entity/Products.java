package com.myFirstProject.myFirstProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

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
    @Column(columnDefinition = "TEXT")
    private String tags;
    @Column(columnDefinition = "TEXT")
    private String imageUrls;
    @Column(columnDefinition = "TEXT")
    private String keyFeatures;

}
