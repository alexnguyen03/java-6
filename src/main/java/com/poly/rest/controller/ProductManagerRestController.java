package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.model.Product;
import com.poly.repository.ProductDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/product")
public class ProductManagerRestController {
    @Autowired
    ProductDAO productDAO;

    @GetMapping
    public List<Product> getAllList() {
        return productDAO.findAll();
    }

    // GET a single product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer id) {
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST create a new product
    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productDAO.save(product);
    }

    // PUT update an existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Integer id,
            @RequestBody Product productDetails) {
        Optional<Product> product = productDAO.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(productDetails.getName());
            existingProduct.setImage(productDetails.getImage());
            existingProduct.setPrice(productDetails.getPrice());
            existingProduct.setQuantity(productDetails.getQuantity());
            existingProduct.setCategory(productDetails.getCategory());
            // Update other fields and relationships as needed

            return ResponseEntity.ok().body(productDAO.save(existingProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
