package org.example.restexam.controller;

import lombok.RequiredArgsConstructor;
import org.example.restexam.domain.Product;
import org.example.restexam.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final List<Product> producst = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product addProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product updateProduct = productService.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
