package com.esib.esib.controller;

import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Meldo Maunze
 */
@RestController
@RequestMapping("/")
public class EsibController {


    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(EsibController.class.getName());
    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>("Bem vindo a prefaculdade", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

}
