package org.example.restexam.service;

import org.example.restexam.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product getProduct(Long id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(Long id);
}
