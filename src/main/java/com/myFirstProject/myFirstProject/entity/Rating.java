package com.myFirstProject.myFirstProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;
    @Column(length = 1000)
    private String description;

    @JsonIgnore
    @ManyToOne
    private Users user;
    @JsonIgnore
    @ManyToOne
    private Products product;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
