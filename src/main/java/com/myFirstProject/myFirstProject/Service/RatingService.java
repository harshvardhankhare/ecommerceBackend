package com.myFirstProject.myFirstProject.Service;
import com.myFirstProject.myFirstProject.DTO.RatingRequest;
import com.myFirstProject.myFirstProject.DTO.RatingResponse;
import com.myFirstProject.myFirstProject.Repository.ProductRepository;
import com.myFirstProject.myFirstProject.Repository.RatingRepository;
import com.myFirstProject.myFirstProject.Repository.UserRepository;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Rating;
import com.myFirstProject.myFirstProject.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Rating createRating(RatingRequest request, Users user) {

        Products product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean alreadyRated =
                ratingRepository.existsByUserAndProduct(user, product);

        if (alreadyRated) {
            throw new RuntimeException("You have already rated this product");
        }

        Rating rating = new Rating();

        rating.setRating(request.getRating());
        rating.setDescription(request.getDescription());
        rating.setUser(user);
        rating.setProduct(product);

        return ratingRepository.save(rating);
    }

    public List<RatingResponse> getProductRatings(Long productId) {
        List<Rating> ratings = ratingRepository.findByProductProductId(productId);

        return ratings.stream().map(rating -> {

            RatingResponse response = new RatingResponse();

            response.setId(rating.getId());
            response.setRating(rating.getRating());
            response.setDescription(rating.getDescription());

            response.setUserId(rating.getUser().getId());
            response.setUserName(rating.getUser().getName());

            response.setProductId(rating.getProduct().getProductId());

            response.setCreatedAt(rating.getCreatedAt());

            return response;

        }).toList();
    }

    public Double getAverageRating(Long productId) {

        Double avg = ratingRepository.getAverageRating(productId);

        return avg == null ? 0.0 : avg;
    }
}
