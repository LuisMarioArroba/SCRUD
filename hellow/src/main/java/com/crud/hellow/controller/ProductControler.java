package com.crud.hellow.controller;

import com.crud.hellow.entity.Product;
import com.crud.hellow.service.ProductService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductControler {
    private final ProductService productService;
    
    @GetMapping
    public Iterable<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("{id}")
     public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        try{
            Product updatedProduct = productService.update(id, product);
            return ResponseEntity.ok(updatedProduct);
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try{
            productService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
}
