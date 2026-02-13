package com.webtech.ordernbilling.service;

import com.webtech.ordernbilling.repository.ProductRepository;

import com.webtech.ordernbilling.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct (Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setAmount(productDetails.getAmount());
                    existingProduct.setName(productDetails.getName());
                    existingProduct.setPrice(productDetails.getPrice());

                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " had not been found."));
    }

    public Optional<Product> changeProductPrice(Integer id, int amount) {
        if (amount < 0) {
            throw new RuntimeException("Product price can't be negative.");
        }
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setPrice(amount);

                    return productRepository.save(existingProduct);
                });
    }

    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id " + id + " had not been found.");
        }

        productRepository.deleteById(id);
    }

}
