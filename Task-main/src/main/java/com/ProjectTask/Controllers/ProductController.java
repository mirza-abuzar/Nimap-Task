package com.ProjectTask.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ProjectTask.Entity.Category;
import com.ProjectTask.Entity.Product;
import com.ProjectTask.Services.CategoryService;
import com.ProjectTask.Services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Map<String, Object> body) {
        // Extract "name", "description", and "category" from the body
        String name = (String) body.get("name");
        String description = (String) body.get("description");

        // Handle "category" as an object or ID
        Map<String, Object> categoryMap = (Map<String, Object>) body.get("category");
        Long categoryId = categoryMap != null ? ((Number) categoryMap.get("id")).longValue() : null;

        // Create a new Product object
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);

        // Fetch the Category entity from the database if categoryId is provided
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId); // Assume this method exists
            product.setCategory(category);
        }

        // Save the product using the service
        return productService.createProduct(product);
    }

//    @PostMapping
//    public Product createProduct(@RequestBody @Validated Product product) {
//        return productService.createProduct(product);
//    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Validated Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
