package com.myFirstProject.myFirstProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // INTERNAL relation only — never serialized
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    // SNAPSHOT of product data
    private Long productId;
    private String productName;
    private String productImage;
    private Double productPrice;

    private int quantity;
}


