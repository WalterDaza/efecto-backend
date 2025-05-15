package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getCategories ();

    CategoryDTO getCategoryById (long id);

    CategoryDTO updateCategory (CategoryDTO categoryDTO, long id);

    void removeCategory (long id);

}
