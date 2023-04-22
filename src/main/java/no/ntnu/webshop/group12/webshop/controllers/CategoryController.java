package no.ntnu.webshop.group12.webshop.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.service.CategoryService;

@RestController
@Tag(name = "Category", description = "Category API")
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

    @GetMapping(path = "/count", produces = "application/json")
    @Operation(summary = "Get number of categories")
    public ResponseEntity<Long> getCategoryCount() {
        return ResponseEntity.ok(categoryService.getCategoryCount());
    }

    @GetMapping("/filter")
    @Operation(summary = "Get categories by filter")
    public List<Category> getCategoriesByFilter(
            @ParameterObject @PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable,
            @ParameterObject @QuerydslPredicate(root = Category.class) Predicate predicate) {
        return categoryService.getCategoriesByFilter(predicate, pageable);
    }

    @PostMapping("")
    @Operation(summary = "Create a new category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        ResponseEntity<Category> response = null;
        try {
            Category newCategory = categoryService.createCategory(category);
            response = ResponseEntity.ok().body(newCategory);
        } catch (ConstraintViolationException e) {
            response = ResponseEntity.badRequest().build();
        }
        return response;
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
