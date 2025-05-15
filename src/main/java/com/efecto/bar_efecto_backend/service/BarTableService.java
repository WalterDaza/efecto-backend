package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.BarTableDTO;

import java.util.List;

public interface BarTableService {

    BarTableDTO createBarTable (BarTableDTO barTableDTO);

    List<BarTableDTO> getBarTables ();

    BarTableDTO getBarTableById (long id);

    BarTableDTO updateBarTable (BarTableDTO barTableDTO, long id);

    void deleteBarTable(long id);

}
