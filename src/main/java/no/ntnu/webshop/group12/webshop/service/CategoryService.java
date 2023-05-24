package no.ntnu.webshop.group12.webshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import no.ntnu.webshop.group12.webshop.exception.NotFoundException;
import no.ntnu.webshop.group12.webshop.models.product.Category;
import no.ntnu.webshop.group12.webshop.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        return category.get();
    }

    public long getCategoryCount() {
        return categoryRepository.count();
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public List<Category> getCategoriesByFilter(Predicate predicate, Pageable pageable) {
        return categoryRepository.findAll(predicate, pageable).getContent();
    }

    public Category updateCategory(int id, Category category) {
        Category getCategory = getCategory(id);
        Optional.of(category.getName()).ifPresent(getCategory::setName);        
        return getCategory;
    }
}
