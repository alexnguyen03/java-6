package com.poly.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.poly.model.Product;
import com.poly.repository.ProductDAO;
import com.poly.service.ProductService;
import com.poly.service.impl.ProductServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/products")
public class ProductManagerRestController {
    @Autowired
    ProductServiceImpl productService;

    // @GetMapping
    // public List<Product> getAllList() {
    // return productService.findAll();
    // }

    // // GET a single product by ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Product> getProductById(@PathVariable(value = "id")
    // Integer id) {
    // Product product = productService.findById(id);
    // if (product != null) {
    // return ResponseEntity.ok().body(product);
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    // // POST create a new product
    // @PostMapping
    // @ResponseBody
    // public Product createProduct(@RequestBody Product product) {
    // System.out.println("Hello");
    // System.out.println(product);
    // return productService.save(product);
    // }

    // // PUT update an existing product
    // @PutMapping("/update/{id}")
    // public ResponseEntity<Product> updateProduct(
    // @PathVariable(value = "id") Integer id,
    // @RequestBody Product productDetails) {
    // Product product = productService.findById(id);
    // if (product != null) {
    // Product existingProduct = product;
    // existingProduct.setName(productDetails.getName());
    // existingProduct.setImage(productDetails.getImage());
    // existingProduct.setPrice(productDetails.getPrice());
    // existingProduct.setQuantity(productDetails.getQuantity());
    // existingProduct.setCategory(productDetails.getCategory());
    // // Update other fields and relationships as needed

    // return ResponseEntity.ok().body(productService.save(existingProduct));
    // } else {
    // return ResponseEntity.notFound().build();
    // }
    // }

    @GetMapping("")
    @ResponseBody
    public List<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping(value = "/create",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<Product> add(@RequestBody Product product,
            @RequestParam MultipartFile img) {
        System.out.println(img);
        System.out.println(product.getImage());
        return ResponseEntity.ok(new Product());
        // return ResponseEntity.ok(productService.save(product));
    }

}
