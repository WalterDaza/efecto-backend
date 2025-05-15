package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.BarTableDTO;
import com.efecto.bar_efecto_backend.exceptions.ResourceNotFoundException;
import com.efecto.bar_efecto_backend.mapper.BarTableMapper;
import com.efecto.bar_efecto_backend.model.BarTable;
import com.efecto.bar_efecto_backend.repository.BarTableRepository;
import com.efecto.bar_efecto_backend.service.BarTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarTableServiceImpl implements BarTableService {

    @Autowired
    private BarTableRepository barTableRepository;

    @Autowired
    private BarTableMapper barTableMapper;

    @Override
    public BarTableDTO createBarTable(BarTableDTO barTableDTO) {

        BarTable barTableRequest = barTableMapper.toEntity(barTableDTO); //Se convierte a Entidad
        BarTable newBarTable = barTableRepository.save(barTableRequest); //Se guarda en bd
        BarTableDTO barTableResponse = barTableMapper.toDTO(newBarTable); //Se convierte a DTO

        return barTableResponse;
    }

    @Override
    public List<BarTableDTO> getBarTables() {
        List<BarTable> barTables = barTableRepository.findAll();
        return barTables.stream().map(barTable -> barTableMapper.toDTO(barTable)).collect(Collectors.toList());
    }

    @Override
    public BarTableDTO getBarTableById(long id) {

        BarTable barTable = barTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa", "id", id));

        return barTableMapper.toDTO(barTable);
    }

    @Override
    public BarTableDTO updateBarTable(BarTableDTO barTableDTO, long id) {
        BarTable barTable = barTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa", "id", id));

        barTable.setType(barTableDTO.getType());
        barTable.setNumberTable(barTableDTO.getNumberTable());

        BarTable saveBarTable = barTableRepository.save(barTable);

        return barTableMapper.toDTO(saveBarTable);
    }

    @Override
    public void deleteBarTable(long id) {
        BarTable barTable = barTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa", "id", id));
        barTableRepository.delete(barTable);
    }

}
