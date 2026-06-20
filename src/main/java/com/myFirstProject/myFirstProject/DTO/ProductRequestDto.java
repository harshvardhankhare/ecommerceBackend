package com.myFirstProject.myFirstProject.DTO;

import lombok.Data;

import java.util.List;
@Data
public class ProductRequestDto {
    private String name;
    private String desc;
    private Double price;
    private String category;
    private String slug;
    private Integer discount;
    private String thumbnailImage;
    private List<String> imgUrls;
}
