package com.efecto.bar_efecto_backend.controller;

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
    public ResponseEntity< ProductDTO > saveProduct (@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ProductDTO> getProducts (){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO>  getProductById(@PathVariable long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProdcut (@RequestBody ProductDTO productDTO, @PathVariable long id){
        ProductDTO productResponse = productService.updateProduct(productDTO, id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct (@PathVariable long id){
        productService.removeProduct(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}
