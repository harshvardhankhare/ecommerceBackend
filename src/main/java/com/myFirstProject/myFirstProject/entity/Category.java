package com.myFirstProject.myFirstProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    private String slug;

    private String image;

    private String description;

    @ElementCollection
    private List<String> subcategories;

    private String icon;

    private String color;

}
