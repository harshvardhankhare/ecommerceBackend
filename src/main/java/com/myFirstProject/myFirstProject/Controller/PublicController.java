package com.myFirstProject.myFirstProject.Controller;

import com.myFirstProject.myFirstProject.Service.CategoryService;
import com.myFirstProject.myFirstProject.Service.ProductService;
import com.myFirstProject.myFirstProject.entity.Category;
import com.myFirstProject.myFirstProject.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/getAll")
    public Object getAllProducts(){
        try {
            return productService.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/categories")
    public  List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @GetMapping("/test")
    public String test() {
        return "WORKING";
    }

}
