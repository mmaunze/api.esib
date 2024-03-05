package com.esib.esib.model.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data
public class PagamentoMultaDTO {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(PagamentoMultaDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Long multa;

    /**
     *
     */
    private Date dataPagamento;

    /**
     *
     */
    private Long bibliotecario;

    /**
     *
     */
    private String nomeBibliotecario;

    /**
     *
     */
    private Double valorPago;

}
