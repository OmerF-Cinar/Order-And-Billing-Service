package com.webtech.ordernbilling.controller;

import com.webtech.ordernbilling.entity.Product;
import com.webtech.ordernbilling.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path="/add")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping(path="/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteProduct(@PathVariable Integer id) {
            productService.deleteProduct(id);
    };

    @Transactional
    @PutMapping(path="/update/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product reqProduct = productService.updateProduct(id,product);
        return reqProduct;

    }

    @PutMapping(path="/{id}/updateprice/{amount}")
    public Product changeProductPrice(@PathVariable Integer id, @PathVariable int amount) {
        Optional<Product> reqProduct = productService.changeProductPrice(id,amount);
        return reqProduct.orElse(null);
    }


}