package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.dto.BarTableDTO;
import com.efecto.bar_efecto_backend.mapper.BarTableMapper;
import com.efecto.bar_efecto_backend.serviceImpl.BarTableServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class BarTableController {

    @Autowired
    private BarTableServiceImpl barTableService;

    @GetMapping
    public List<BarTableDTO> getBarTables () {
        return barTableService.getBarTables();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarTableDTO> getBarTableById (@PathVariable long id ) {
        return ResponseEntity.ok(barTableService.getBarTableById(id));
    }

    @PostMapping
    public ResponseEntity<BarTableDTO> createBarTable (@RequestBody BarTableDTO barTableDTO) {
        return new ResponseEntity<>(barTableService.createBarTable(barTableDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarTableDTO> updateBarTable (@RequestBody BarTableDTO barTableDTO, @PathVariable long id){
        return new ResponseEntity<>(barTableService.updateBarTable(barTableDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBartable (@PathVariable long id){
        barTableService.deleteBarTable(id);
        return new ResponseEntity<>("Mesa eliminada", HttpStatus.OK);
    }

}
