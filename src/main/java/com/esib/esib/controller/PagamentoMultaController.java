package com.esib.esib.controller;

import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.PagamentoMulta;
import com.esib.esib.modelo.dto.PagamentoMultaDTO;
import com.esib.esib.service.BibliotecarioService;
import com.esib.esib.service.MultaService;
import com.esib.esib.service.PagamentoMultaService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
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
@RequestMapping("/multas/pagamentos")
@RequiredArgsConstructor

public class PagamentoMultaController {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(PagamentoMultaController.class.getName());

    /**
     *
     */
    private final PagamentoMultaService pagamentoPagamentoMultaService;

    /**
     *
     */
    private final BibliotecarioService bibliotecarioService;

    /**
     *
     */
    private final MultaService multaService;

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<PagamentoMultaDTO>> findAll() {
        try {
            var pagamentos = pagamentoPagamentoMultaService.findAll();
            var pagamentosDTO = pagamentos.stream()
                    .map(this::convertToDTO)
                    .collect(toList());
            return new ResponseEntity<>(pagamentosDTO, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/pagamento/{id}")
    public ResponseEntity<PagamentoMultaDTO> findById(@PathVariable Long id) {
        try {
            var pagamentos = pagamentoPagamentoMultaService.findById(id);
            return pagamentos.map(d -> ok(convertToDTO(d)))
                    .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param pagamentoDTO
     * @return
     */
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody PagamentoMultaDTO pagamentoDTO) {
        try {
            var newPagamentoMulta = pagamentoPagamentoMultaService.create(convertToEntity(pagamentoDTO));
            var newPagamentoMultaDTO = convertToDTO(newPagamentoMulta);
            URI location = fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newPagamentoMultaDTO.getId())
                    .toUri();

            return created(location).build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param pagamentoDTO
     * @param id
     * @return
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> update(@RequestBody PagamentoMultaDTO pagamentoDTO, @PathVariable Long id) {
        try {
            pagamentoPagamentoMultaService.update(convertToEntity(pagamentoDTO));
            return noContent().build();
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
            pagamentoPagamentoMultaService.delete(id);
            return noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares para conversão entre Entidade e DTO

    /**
     *
     * @param pagamento
     * @return
     */
    private PagamentoMultaDTO convertToDTO(PagamentoMulta pagamento) {

        var pagamentoDTO = new PagamentoMultaDTO();
        pagamentoDTO.setId(pagamento.getId());
        pagamentoDTO.setMulta(pagamento.getMulta().getId());
        pagamentoDTO.setDataPagamento(pagamento.getDataPagamento());
        pagamentoDTO.setBibliotecario(pagamento.getBibliotecario().getUtilizador().getId());
        pagamentoDTO.setNomeBibliotecario(pagamento.getBibliotecario().getUtilizador().getNome());
        pagamentoDTO.setValorPago(pagamento.getValorPago());

        return pagamentoDTO;
    }

    /**
     *
     * @param pagamentoDTO
     * @return
     */
    private PagamentoMulta convertToEntity(PagamentoMultaDTO pagamentoDTO) {

        Optional<Multa> optionalMulta = multaService.findById(pagamentoDTO.getMulta());

        var pagamento = new PagamentoMulta();

        pagamento.setId(pagamentoDTO.getId());
        if (optionalMulta.isPresent()) {
            pagamento.setMulta((multaService.findById(pagamentoDTO.getMulta()).get()));
        }
        pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
        if ((bibliotecarioService.findById(pagamentoDTO.getBibliotecario())).isPresent()) {
            pagamento.setBibliotecario(bibliotecarioService.findById(pagamentoDTO.getBibliotecario()).get());
        }
        pagamento.setValorPago(pagamentoDTO.getValorPago());

        return pagamento;
    }

}
