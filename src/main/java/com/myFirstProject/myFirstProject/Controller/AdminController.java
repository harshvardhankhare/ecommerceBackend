package com.myFirstProject.myFirstProject.Controller;
import com.myFirstProject.myFirstProject.DTO.CategoryRequest;
import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Service.CategoryService;
import com.myFirstProject.myFirstProject.Service.OrderService;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Category;
import com.myFirstProject.myFirstProject.entity.Order;
import com.myFirstProject.myFirstProject.entity.Products;
import com.myFirstProject.myFirstProject.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

private final ProductService productService;
private final OrderService orderService;
private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Products>> addProduct(
            @RequestBody ProductRequestDto pr) {

        Products product = productService.insertProduct(pr);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Product created successfully",
                                product
                        )
                );
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable Long id){

        productService.deleteProduct(id);
        return "Product Deleted SuccessFully";
    }
    @PostMapping("/categories")
    public Category createCategory(
            @RequestBody CategoryRequest request) {

        return categoryService.createCategory(request);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);
        return "Category Deleted Successfully";
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse<Products>> updateProduct(
            @RequestBody ProductRequestDto reqProduct,
            @PathVariable Long id) {

        Products updatedProduct =
                productService.updateProduct(reqProduct, id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product updated successfully",
                        updatedProduct
                )
        );
    }
}
