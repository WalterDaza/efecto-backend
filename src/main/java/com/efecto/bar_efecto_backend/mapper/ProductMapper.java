package com.efecto.bar_efecto_backend.mapper;

import com.efecto.bar_efecto_backend.dto.ProductDTO;
import com.efecto.bar_efecto_backend.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // DTO → Entidad
    Product toEntity(ProductDTO dto);

    // Entidad → DTO
    ProductDTO toDTO(Product entity);
}
