package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;
import com.efecto.bar_efecto_backend.dto.ProductDTO;

import java.util.List;

public interface CategoryService {

    public CategoryDTO createCategory(CategoryDTO categoryDTO);

    public List<CategoryDTO> getCategories ();

    public CategoryDTO getCategoryById (long id);

    public CategoryDTO updateCategory (CategoryDTO categoryDTO, long id);

    public void removeCategory (long id);

}
