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

import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.modelo.dto.PagamentoMultaDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.MultaService;
import com.esib.esib.service.PagamentoMultaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/multas/pagamentos")
@RequiredArgsConstructor

public class PagamentoMultaController {
    private final PagamentoMultaService pagamentoPagamentoMultaService;
    private final BibliotecarioService bibliotecarioService;
    private final MultaService multaService;

    @GetMapping()
    public ResponseEntity<List<PagamentoMultaDTO>> findAll() {
        try {
            List<PagamentoMulta> pagamentos = pagamentoPagamentoMultaService.findAll();
            List<PagamentoMultaDTO> pagamentosDTO = pagamentos.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(pagamentosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagamento/{id}")
    public ResponseEntity<PagamentoMultaDTO> findById(@PathVariable Long id) {
        try {
            Optional<PagamentoMulta> pagamentos = pagamentoPagamentoMultaService.findById(id);
            return pagamentos.map(d -> ResponseEntity.ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PagamentoMultaDTO pagamentoDTO) {
        try {
            PagamentoMulta newPagamentoMulta = pagamentoPagamentoMultaService.create(convertToEntity(pagamentoDTO));
            PagamentoMultaDTO newPagamentoMultaDTO = convertToDTO(newPagamentoMulta);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newPagamentoMultaDTO.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody PagamentoMultaDTO pagamentoDTO, @PathVariable Long id) {
        try {
            pagamentoPagamentoMultaService.update(convertToEntity(pagamentoDTO));
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            pagamentoPagamentoMultaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO
    private PagamentoMultaDTO convertToDTO(PagamentoMulta pagamento) {

        PagamentoMultaDTO pagamentoDTO = new PagamentoMultaDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setMulta(pagamento.getMulta().getId());
        pagamentoDTO.setDataPagamento(pagamento.getDataPagamento());
        pagamentoDTO.setBibliotecario(pagamento.getBibliotecario().getUtilizador().getId());
        pagamentoDTO.setNomeBibliotecario(pagamento.getBibliotecario().getUtilizador().getNome());
        pagamentoDTO.setValorPago(pagamento.getValorPago());

        return pagamentoDTO;
    }

    private PagamentoMulta convertToEntity(PagamentoMultaDTO pagamentoDTO) {

        Optional<Multa> optionalMulta = multaService.findById(pagamentoDTO.getMulta());

        PagamentoMulta pagamento = new PagamentoMulta();

        pagamento.setId(pagamentoDTO.getId());
        if (optionalMulta.isPresent())
            pagamento.setMulta((multaService.findById(pagamentoDTO.getMulta()).get()));
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        if ((bibliotecarioService.findById(pagamentoDTO.getBibliotecario())).isPresent())
            pagamento.setBibliotecario(bibliotecarioService.findById(pagamentoDTO.getBibliotecario()).get());
        pagamento.setValorPago(pagamentoDTO.getValorPago());

        return pagamento;
    }
}
