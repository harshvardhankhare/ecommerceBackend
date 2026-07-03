package com.myFirstProject.myFirstProject.Controller;
import com.myFirstProject.myFirstProject.DTO.ProductResponseDTO;
import com.myFirstProject.myFirstProject.DTO.RatingRequest;
import com.myFirstProject.myFirstProject.DTO.RatingResponse;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.Service.RatingService;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Rating;
import com.myFirstProject.myFirstProject.entity.Users;
import com.myFirstProject.myFirstProject.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    RatingService ratingService;

    @PostMapping("/add")
    public  String addProduct(@RequestBody Products p){

        System.out.println(p.toString());
        return "Product inserted Successfully";
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(
            @PathVariable Long id){

        ProductResponseDTO product = productService.getProductDetailsById(id);

        ApiResponse<ProductResponseDTO> response =
                ApiResponse.<ProductResponseDTO>builder()
                        .success(true)
                        .message("Product fetched successfully")
                        .data(product)
                        .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAll(){

        List<ProductResponseDTO> products = productService.getAllProducts();

        return ResponseEntity.ok(
                ApiResponse.<List<ProductResponseDTO>>builder()
                        .success(true)
                        .message("Products fetched successfully")
                        .data(products)
                        .build()
        );
    }

        @GetMapping("/search")
        public List<Products> search(@RequestParam("q") String query) {

            return productService.getSearchProduct(query);
        }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Product deleted successfully")
                        .build()
        );
    }
    @GetMapping("/new-arrivals")
    public ResponseEntity<List<Products>> getNewArrivals() {
        return ResponseEntity.ok(productService.getNewArrivals());
    }
    @PostMapping("/create-rating")
    public ResponseEntity<ApiResponse<RatingResponse>> createRating(
            @RequestBody RatingRequest request,
            @AuthenticationPrincipal Users user){

        RatingResponse rating = ratingService.createRating(request,user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<RatingResponse>builder()
                                .success(true)
                                .message("Rating created successfully")
                                .data(rating)
                                .build()
                );
    }
    @GetMapping("/{productId}/ratings")
    public List<RatingResponse> getRatings(@PathVariable Long productId) {
        return ratingService.getProductRatings(productId);
    }

}
