package com.myFirstProject.myFirstProject.Controller;
import com.myFirstProject.myFirstProject.DTO.CategoryRequest;
import com.myFirstProject.myFirstProject.DTO.ProductRequestDto;
import com.myFirstProject.myFirstProject.Service.CategoryService;
import com.myFirstProject.myFirstProject.Service.OrderService;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Category;
import com.myFirstProject.myFirstProject.entity.Order;
import lombok.RequiredArgsConstructor;
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
    public String addProduct(@RequestBody ProductRequestDto pr){
        productService.insertProduct(pr);
        return "Product Inserted SuccessFully";
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/product/{id}")
    public String deleteProduct(@PathVariable int id){

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
}
