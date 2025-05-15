package com.efecto.bar_efecto_backend.mapper;

import com.efecto.bar_efecto_backend.dto.BarTableDTO;
import com.efecto.bar_efecto_backend.dto.PaymentMethodDTO;
import com.efecto.bar_efecto_backend.model.BarTable;
import com.efecto.bar_efecto_backend.model.PaymentMethod;
import org.mapstruct.Mapper;
    @Mapper(componentModel = "spring")
    public interface PaymentMethodMapper {

        PaymentMethod toEntity(PaymentMethodDTO dto);

        // Entidad → DTO
        PaymentMethodDTO toDTO(PaymentMethod entity);

    }
