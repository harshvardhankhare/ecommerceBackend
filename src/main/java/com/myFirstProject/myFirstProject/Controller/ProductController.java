package com.myFirstProject.myFirstProject.Controller;
import com.myFirstProject.myFirstProject.DTO.ProductResponseDTO;
import com.myFirstProject.myFirstProject.DTO.RatingRequest;
import com.myFirstProject.myFirstProject.DTO.RatingResponse;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.Service.RatingService;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.entity.Rating;
import com.myFirstProject.myFirstProject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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
    public ProductResponseDTO getProductById(@PathVariable Long id){
        return productService.getProductDetailsById(id);
    }

    @GetMapping("/products")
        public List<Products> getAll(){
        return productService.getAllProducts();
        }

        @GetMapping("/search")
        public List<Products> search(@RequestParam("q") String query) {

            return productService.getSearchProduct(query);
        }
        @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
         productService.deleteProduct(id);
              System.out.println(" #33333333333333 Delete product  #####################");
        return "Product delete successfully";
          }
    @GetMapping("/new-arrivals")
    public ResponseEntity<List<Products>> getNewArrivals() {
        return ResponseEntity.ok(productService.getNewArrivals());
    }
    @PostMapping("/create-rating")
    public Rating createNewRating( @RequestBody RatingRequest request, @AuthenticationPrincipal Users user){
        System.out.println("REQUEST = " + request);
        System.out.println("USER = " + user);
      return ratingService.createRating(request,user);
    }
    @GetMapping("/{productId}/ratings")
    public List<RatingResponse> getRatings(@PathVariable Long productId) {
        return ratingService.getProductRatings(productId);
    }

}
