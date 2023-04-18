package no.ntnu.webshop.group12.webshop.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    @Parameter(name = "id", description = "Category id", required = true)
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        ResponseEntity<Category> response = ResponseEntity.notFound().build();
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isPresent()) {
            response = ResponseEntity.ok(category.get());
        }
        return response;
    }

    @GetMapping("/count")
    @Operation(summary = "Get number of categories")
    public ResponseEntity<Long> getCategoryCount() {
        return ResponseEntity.ok(categoryService.getCategoryCount());
    }

    @PostMapping()
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    @Parameter(name = "id", description = "Category id", required = true)
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        ResponseEntity<Category> response = ResponseEntity.notFound().build();
        Optional<Category> category = categoryService.getCategory(id);
        if (category.isPresent()) {
            categoryService.deleteCategory(category.get());
            response = ResponseEntity.ok().build();
        }
        return response;
    }

}
