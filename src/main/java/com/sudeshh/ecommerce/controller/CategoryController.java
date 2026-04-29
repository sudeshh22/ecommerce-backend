package com.sudeshh.ecommerce.controller;

import com.sudeshh.ecommerce.model.Category;
import com.sudeshh.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/admin")
    public ResponseEntity<Category> createCategory(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.createCategory(name));
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}