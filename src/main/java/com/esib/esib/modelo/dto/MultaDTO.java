package com.esib.esib.modelo.dto;

import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class MultaDTO {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(MultaDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Double valor;

    /**
     *
     */
    private Long emprestimo;

    /**
     *
     */
    private String estado;

}
