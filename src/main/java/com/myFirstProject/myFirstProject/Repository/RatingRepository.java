package com.myFirstProject.myFirstProject.Repository;

import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Rating;
import com.myFirstProject.myFirstProject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    boolean existsByUserAndProduct(Users user, Products product);

    List<Rating> findByProductProductId(Long productId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.product.id = :productId")
    Double getAverageRating(Long productId);
}
