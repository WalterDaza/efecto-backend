package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;
import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getCategories (){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity< CategoryDTO > saveCategory (@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory (@RequestBody CategoryDTO categoryDTO, @PathVariable long id){
        CategoryDTO categoryResponse = categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct (@PathVariable long id){
        categoryService.removeCategory(id);
        return new ResponseEntity<>("Categoria eliminada", HttpStatus.OK);
    }
}
