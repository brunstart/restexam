package org.example.restexam.service;

import lombok.RequiredArgsConstructor;
import org.example.restexam.Repository.ProductRepository;
import org.example.restexam.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        try{
            return productRepository.save(product);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public Product updateProduct(Product product) {

        if (product.getId() == null) {
            throw new IllegalArgumentException("제품 ID 필수");
        }

        Product findProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getName() != null) {
            findProduct.setName(product.getName());
        }

        if (product.getPrice() != null) {
            findProduct.setPrice(product.getPrice());
        }

        return findProduct;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id))
            throw new RuntimeException("Product not found");

        productRepository.deleteById(id);
    }
}
