package com.esib.esib.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esib.esib.service.MovimentoService;

import lombok.RequiredArgsConstructor;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor

public class EsibController {

    private final MovimentoService movimentoService;

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(EsibController.class.getName());

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>("Bem vindo a prefaculdade", HttpStatus.OK);
        } catch (Exception e) {
            logger.info("erro" + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/relatorios")
    public ResponseEntity<?> gerarRelatorio() {
        try {
            var relatorios = movimentoService.gerarRelatorio();

            return new ResponseEntity<>(relatorios, OK);
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

}
