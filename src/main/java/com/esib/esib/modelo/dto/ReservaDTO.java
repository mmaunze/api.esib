package com.esib.esib.modelo.dto;

import java.util.Date;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author Meldo Maunze
 */
@Data

public class ReservaDTO {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(ReservaDTO.class.getName());

    /**
     *
     */
    private Long id;

    /**
     *
     */
    private Integer atraso;

    /**
     *
     */
    private Date dataReserva;

    /**
     *
     */
    private String estado;

    /**
     *
     */
    private Long obra;

    /**
     *
     */
    private String tituloObra;

    /**
     *
     */
    private Long utilizador;

    /**
     *
     */
    private String utlizadorNome;

}
