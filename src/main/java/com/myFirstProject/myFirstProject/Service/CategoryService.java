package com.myFirstProject.myFirstProject.Service;

import com.myFirstProject.myFirstProject.DTO.CategoryRequest;
import com.myFirstProject.myFirstProject.Repository.CategoryRepository;
import com.myFirstProject.myFirstProject.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category createCategory(CategoryRequest request) {

        Category category = new Category();

        category.setName(request.getName());
        category.setSlug(request.getSlug());
        category.setImage(request.getImage());
        category.setDescription(request.getDescription());
        category.setSubcategories(request.getSubcategories());
        category.setIcon(request.getIcon());
        category.setColor(request.getColor());

        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
