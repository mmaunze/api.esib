package com.esib.esib.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.esib.esib.modelo.Cargo;
import com.esib.esib.modelo.dto.CargoDTO;
import com.esib.esib.service.CargoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor

public class CargoController {
    private final CargoService cargoService;

    @GetMapping()
    public ResponseEntity<List<CargoDTO>> findAll() {
        try {
            List<Cargo> cargo = cargoService.findAll();
            List<CargoDTO> cargoDTO = cargo.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(cargoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cargo/{id}")
    public ResponseEntity<CargoDTO> findById(@PathVariable Long id) {
        try {
            Optional<Cargo> cargo = cargoService.findById(id);
            return cargo.map(c -> ResponseEntity.ok(convertToDTO(c)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CargoDTO cargoDTO) {
        try {
            Cargo newAreaCientifica = cargoService.create(convertToEntity(cargoDTO));
            CargoDTO newAreaCientificaDTO = convertToDTO(newAreaCientifica);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newAreaCientificaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CargoDTO cargoDTO, @PathVariable Long id) {
        try {
            cargoService.update(convertToEntity(cargoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            cargoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private CargoDTO convertToDTO(Cargo cargo) {

        CargoDTO cargoDTO = new CargoDTO();
        cargoDTO.setId(cargo.getId());
        cargoDTO.setDescricao(cargo.getDescricao());

        return cargoDTO;
    }

    private Cargo convertToEntity(CargoDTO cargoDTO) {

        Cargo cargo = new Cargo();
        cargo.setId(cargoDTO.getId());
        cargo.setDescricao(cargoDTO.getDescricao());

        return cargo;
    }
}
