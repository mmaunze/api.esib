package com.esib.esib.controller;

import com.esib.esib.model.Cargo;
import com.esib.esib.model.dto.CargoDTO;
import com.esib.esib.service.CargoService;
import java.net.URI;
import java.util.List;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor

public class CargoController {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(CargoController.class.getName());

    /**
     *
     */
    private final CargoService cargoService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<CargoDTO>> findAll() {
        try {
            var cargo = cargoService.findAll();
            var cargoDTO = cargo.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(cargoDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/cargo/{id}")
    public ResponseEntity<CargoDTO> findById(@PathVariable Long id) {
        try {
            var cargo = cargoService.findById(id);
            return cargo.map(c -> ok(convertToDTO(c)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param cargoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody CargoDTO cargoDTO) {
        try {
            var newAreaCientifica = cargoService.create(convertToEntity(cargoDTO));
            var newAreaCientificaDTO = convertToDTO(newAreaCientifica);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newAreaCientificaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param cargoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody CargoDTO cargoDTO, @PathVariable Long id) {
        try {
            cargoService.update(convertToEntity(cargoDTO));
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            cargoService.delete(id);
            return ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    /**
     *
     * @param cargo
     * @return
     */
    private CargoDTO convertToDTO(Cargo cargo) {

        var cargoDTO = new CargoDTO();
        cargoDTO.setId(cargo.getId());
        cargoDTO.setDescricao(cargo.getDescricao());

        return cargoDTO;
    }

    /**
     *
     * @param cargoDTO
     * @return
     */
    private Cargo convertToEntity(CargoDTO cargoDTO) {

        var cargo = new Cargo();
        cargo.setId(cargoDTO.getId());
        cargo.setDescricao(cargoDTO.getDescricao());

        return cargo;
    }

}
