package com.efecto.bar_efecto_backend.mapper;

import com.efecto.bar_efecto_backend.dto.CategoryDTO;
import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.model.Category;
import com.efecto.bar_efecto_backend.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // DTO → Entidad
    Category toEntity(CategoryDTO dto);

    // Entidad → DTO
    CategoryDTO toDTO(Category entity);
}
