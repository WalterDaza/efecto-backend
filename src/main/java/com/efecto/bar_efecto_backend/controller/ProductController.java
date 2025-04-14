package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;
import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping
    public List<ProductDTO> getProducts () {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById (@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/category/{id}")
    public List <ProductDTO> getProductsByCategoryId(@PathVariable Long id){
        return productService.getProductByCategoryId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct (@PathVariable Long id, @RequestBody ProductDTO productDTO){
        ProductDTO productResponse = productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct (@PathVariable Long id) {
        productService.removeProduct(id);

        return new ResponseEntity<>("Producto Eliminado", HttpStatus.OK);
    }

}
