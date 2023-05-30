package no.ntnu.webshop.group12.webshop.controllers.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.service.CategoryService;

/**
 * Rest controller for category
 * 
 * @version 1.0
 * @since 2023-04-25
 */
@RestController
@Tag(name = "Category", description = "Category API")
@RequestMapping("/api/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    @Parameter(name = "id", description = "Category id", required = true)
    public Category getCategory(@PathVariable int id) throws NotFoundException {
        return categoryService.getCategory(id);
    }

    @GetMapping(path = "/count", produces = "application/json")
    @Operation(summary = "Get number of categories")
    public Long getCategoryCount() {
        return categoryService.getCategoryCount();
    }

    @GetMapping
    @Operation(summary = "Get categories by filter")
    public List<Category> getCategoriesByFilter(
            @ParameterObject @PageableDefault(size = 5, direction = Sort.Direction.ASC) Pageable pageable,
            @ParameterObject @QuerydslPredicate(root = Category.class) Predicate predicate) {
        return categoryService.getCategoriesByFilter(predicate, pageable);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    @Parameter(name = "id", description = "Category id", required = true)
    public Category deleteCategory(@PathVariable int id) {
        Category category = categoryService.getCategory(id);
        categoryService.deleteCategory(category);
        return category;
    }

}
