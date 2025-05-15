package com.efecto.bar_efecto_backend.mapper;

import com.efecto.bar_efecto_backend.dto.BarTableDTO;
import com.efecto.bar_efecto_backend.model.BarTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarTableMapper {
    // DTO → Entidad
    BarTable toEntity(BarTableDTO dto);

    // Entidad → DTO
    BarTableDTO toDTO(BarTable entity);
}
