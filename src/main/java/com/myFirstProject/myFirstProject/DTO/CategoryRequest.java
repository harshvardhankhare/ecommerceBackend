package com.myFirstProject.myFirstProject.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CategoryRequest {
    private String name;
    private String slug;
    private String image;
    private String description;
    private List<String> subcategories;
    private String icon;
    private String color;
}
